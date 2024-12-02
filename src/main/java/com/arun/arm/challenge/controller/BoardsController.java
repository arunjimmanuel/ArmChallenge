package com.arun.arm.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arun.arm.challenge.service.ArmChallengeService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/boards")
public class BoardsController {

	@Autowired
	private ArmChallengeService armChallengeComponent;

	@PostMapping
	public ObjectNode getBoardsFromDirectory(@RequestParam("directory") String directoryPath) {
		String[] args = { "--directory=" + directoryPath };
		DefaultApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
		return armChallengeComponent.run(applicationArguments);
	}
}