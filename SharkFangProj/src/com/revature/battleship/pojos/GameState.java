package com.revature.battleship.pojos;

public class GameState {
	private Long gameStateId;
	private Long playerOneId;
	private Long playerTwoId;
	private String playerOneBoard;
	private String playerTwoBoard;
	private int boardLength;
	public GameState(Long gameStateId, Long playerOneId, Long playerTwoId, String playerOneBoard, String playerTwoBoard,
			int boardLength) {
		super();
		this.gameStateId = gameStateId;
		this.playerOneId = playerOneId;
		this.playerTwoId = playerTwoId;
		this.playerOneBoard = playerOneBoard;
		this.playerTwoBoard = playerTwoBoard;
		this.boardLength = boardLength;
	}
	public Long getGameStateId() {
		return gameStateId;
	}
	public void setGameStateId(Long gameStateId) {
		this.gameStateId = gameStateId;
	}
	public Long getPlayerOneId() {
		return playerOneId;
	}
	public void setPlayerOneId(Long playerOneId) {
		this.playerOneId = playerOneId;
	}
	public Long getPlayerTwoId() {
		return playerTwoId;
	}
	public void setPlayerTwoId(Long playerTwoId) {
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
