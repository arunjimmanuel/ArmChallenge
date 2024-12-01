package com.arm.challenge.commandline.pojo;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoardsWrapper implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
    @JsonProperty(required = true)
	private List<Board> boards;

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	@Override
	public String toString() {
		return "BoardsWrapper [boards=" + boards + "]";
	}
}
