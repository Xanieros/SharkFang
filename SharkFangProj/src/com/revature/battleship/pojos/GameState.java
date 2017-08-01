package com.revature.battleship.pojos;

import org.apache.commons.net.ntp.TimeStamp;

public class GameState {
	private int gameStateId;
	private int playerOneId;
	private int playerTwoId;
	private String playerOneBoard;
	private String playerTwoBoard;
	private int boardLength;
	private int active;
	private TimeStamp timeStamp;
	
	public GameState(int gameStateId, int playerOneId, int playerTwoId, String playerOneBoard, String playerTwoBoard,
			int boardLength, int active, TimeStamp timeStamp) {
		super();
		this.gameStateId = gameStateId;
		this.playerOneId = playerOneId;
		this.playerTwoId = playerTwoId;
		this.playerOneBoard = playerOneBoard;
		this.playerTwoBoard = playerTwoBoard;
		this.boardLength = boardLength;
		this.active = active;
		this.timeStamp = timeStamp;
	}
	public GameState() {
	}
	public int getGameStateId() {
		return gameStateId;
	}
	public void setGameStateId(int gameStateId) {
		this.gameStateId = gameStateId;
	}
	public int getPlayerOneId() {
		return playerOneId;
	}
	public void setPlayerOneId(int playerOneId) {
		this.playerOneId = playerOneId;
	}
	public int getPlayerTwoId() {
		return playerTwoId;
	}
	public void setPlayerTwoId(int playerTwoId) {
		this.playerTwoId = playerTwoId;
	}
	public String getPlayerOneBoard() {
		return playerOneBoard;
	}
	public void setPlayerOneBoard(String playerOneBoard) {
		this.playerOneBoard = playerOneBoard;
	}
	public String getPlayerTwoBoard() {
		return playerTwoBoard;
	}
	public void setPlayerTwoBoard(String playerTwoBoard) {
		this.playerTwoBoard = playerTwoBoard;
	}
	public int getBoardLength() {
		return boardLength;
	}
	public void setBoardLength(int boardLength) {
		this.boardLength = boardLength;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public TimeStamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(TimeStamp timeStamp) {
		this.timeStamp = timeStamp;
	}
}