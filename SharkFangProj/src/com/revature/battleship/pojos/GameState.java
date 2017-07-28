package com.revature.battleship.pojos;

public class GameState {
	private int gameStateId;
	private int playerOneId;
	private int playerTwoId;
	private String playerOneBoard;
	private String playerTwoBoard;
	private int boardLength;
	public GameState(int gameStateId, int playerOneId, int playerTwoId, String playerOneBoard, String playerTwoBoard,
			int boardLength) {
		super();
		this.gameStateId = gameStateId;
		this.playerOneId = playerOneId;
		this.playerTwoId = playerTwoId;
		this.playerOneBoard = playerOneBoard;
		this.playerTwoBoard = playerTwoBoard;
		this.boardLength = boardLength;
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
}