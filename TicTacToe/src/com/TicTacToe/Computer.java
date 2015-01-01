/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Computer.java, Apr 5, 2014, 10:54:07 PM	
 *  TicTacToe, com.TicTacToe
 *
 */
package com.TicTacToe;

/*
 * Computer class, extends the player abstract class
 * defines implementation for the computer player
 */
public class Computer extends Player {

	public Computer(int player) {
		super(player);
		System.out.println("Computer player created");
	}

	@Override
	public void play(Board board) {
		positionOnBoard(board);
		board.setPosition(locationOnBoard, player);
	}

	@Override
	public void positionOnBoard(Board board) {
		int[][] currentBoard = board.getBoard();
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (currentBoard[row][column] == 0) {
					locationOnBoard[0] = row;
					locationOnBoard[1] = column;
				}
			}
		}
	}
}