package com.arun.arm.challenge.components;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import com.arm.challenge.commandline.exception.NoJsonFilesFoundException;
import com.arm.challenge.commandline.pojo.Board;
import com.arm.challenge.commandline.pojo.BoardsWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ArmChallengeComponent {

	private static final Logger logger = LoggerFactory.getLogger(ArmChallengeComponent.class);

	public ObjectNode run(ApplicationArguments args) {
		if (!args.containsOption("directory")) {
			throw new IllegalArgumentException("Missing required argument: --directory=<path>");
		}
		String directoryString = args.getOptionValues("directory").get(0);

		File directory = new File(directoryString);
		if (!directory.exists() || !directory.isDirectory()) {
			throw new IllegalArgumentException("Invalid directory: " + directoryString);
		}
		ObjectMapper objectMapper = new ObjectMapper();

		File[] jsonFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));

		if (jsonFiles == null || jsonFiles.length == 0) {
			throw new NoJsonFilesFoundException("No JSON files found in directory: " + directoryString);
		}

		List<Board> allBoards = new ArrayList<>();
		for (File jsonFile : jsonFiles) {
			try {
				BoardsWrapper boardsWrapper = objectMapper.readValue(jsonFile, BoardsWrapper.class);

				logger.debug("Parsed object from file " + jsonFile.getName() + ":");
				for (Board board : boardsWrapper.getBoards()) {
					allBoards.add(board);
				}

			} catch (IOException e) {
				logger.error("Failed to process file: " + jsonFile.getName());
				e.printStackTrace();
			}
		}

		// Sort the boards by vendor and name
		List<Board> sortedBoards = allBoards.stream()
				.sorted(Comparator.comparing(Board::getVendor).thenComparing(Board::getName))
				.collect(Collectors.toList());

		// Calculate the total number of unique vendors
		long uniqueVendors = allBoards.stream().map(Board::getVendor).distinct().count();
		
		ObjectNode rootNode = objectMapper.createObjectNode();
		rootNode.putPOJO("boards", sortedBoards);
		ObjectNode metadataNode = objectMapper.createObjectNode();
		metadataNode.put("total_vendors", uniqueVendors);
		metadataNode.put("total_boards", allBoards.size());
		rootNode.set("_metadata", metadataNode);
		
		return rootNode;

	}
}
