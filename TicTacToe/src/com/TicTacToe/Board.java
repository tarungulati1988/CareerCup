/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Board.java, Apr 5, 2014, 10:52:39 PM	
 *  TicTacToe, com.TicTacToe
 *
 */
package com.TicTacToe;

public class Board {
	private int[][] Board = new int[3][3];

	public Board() {
		clearBoard();
	}

	public void clearBoard() {
		for (int row = 0; row < 3; row++)
			for (int column = 0; column < 3; column++)
				Board[row][column] = 0;
	}

	public void showBoard() {
		System.out.println();
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {

				if (Board[row][column] == -1) {
					System.out.print("X");
				}
				if (Board[row][column] == 1) {
					System.out.print("O");
				}
				if (Board[row][column] == 0) {
					System.out.print("  ");
				}

				if (column == 0 || column == 1)
					System.out.print("_|_");
			}
			System.out.println();
		}

	}

	public int getPosition(int[] attempt) {
		return Board[attempt[0]][attempt[1]];
	}

	public int[][] getBoard() {
		return Board;
	}

	public void setPosition(int[] attempt, int player) {
		if (player == 1)
			Board[attempt[0]][attempt[1]] = -1;
		else
			Board[attempt[0]][attempt[1]] = 1;
	}

	public int checkRowColumnAndDiagonal() {
		// check if any columns or rows or the diagonals are filled for a win
		for (int i = 0; i < 3; i++) {

			if ((Board[0][i] + Board[1][i] + Board[2][i]) == -3
					|| (Board[i][0] + Board[i][1] + Board[i][2]) == -3
					|| (Board[0][0] + Board[1][1] + Board[2][2]) == -3
					|| (Board[0][2] + Board[1][1] + Board[2][0]) == -3)
				return -1;
			if ((Board[0][i] + Board[1][i] + Board[2][i]) == 3
					|| (Board[i][0] + Board[i][1] + Board[i][2]) == 3
					|| (Board[0][0] + Board[1][1] + Board[2][2]) == 3
					|| (Board[0][2] + Board[1][1] + Board[2][0]) == 3)
				return 1;
		}
		return 0;
	}

	public boolean fullBoard() {
		for (int line = 0; line < 3; line++)
			for (int column = 0; column < 3; column++)
				if (Board[line][column] == 0)
					return false;
		return true;
	}
}
