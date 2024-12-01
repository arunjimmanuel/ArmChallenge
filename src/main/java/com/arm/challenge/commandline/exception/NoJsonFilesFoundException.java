package com.arm.challenge.commandline.exception;
public class NoJsonFilesFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public NoJsonFilesFoundException(String message) {
        super(message);
    }
}
