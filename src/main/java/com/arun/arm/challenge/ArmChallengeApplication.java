package com.arun.arm.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arun.arm.challenge.components.ArmChallengeComponent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@SpringBootApplication
public class ArmChallengeApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(ArmChallengeApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ArmChallengeApplication.class);
		ApplicationArguments arguments = new DefaultApplicationArguments(args);
		// Check for the `--mode` argument
        String mode = arguments.containsOption("mode") ? arguments.getOptionValues("mode").get(0) : "web";

        if ("cli".equalsIgnoreCase(mode)) {
            logger.info("Running in CLI mode...");
            ObjectNode returnJson = new ArmChallengeComponent().run(arguments);
            if(returnJson != null) {
            	 ObjectMapper objectMapper = new ObjectMapper();
            	try {
					String resultJsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnJson);
					logger.info(resultJsonString);
				} catch (JsonProcessingException e) {
					logger.error(e.getLocalizedMessage());
				}
            }
            System.exit(0);
        } else {
        	logger.info("Running in Web mode...");
            app.run(args);
        }
	}

}
