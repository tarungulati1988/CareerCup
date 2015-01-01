/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Game.java, Apr 5, 2014, 10:53:04 PM	
 *  TicTacToe, com.TicTacToe
 *
 */
package com.TicTacToe;

import java.util.Scanner;

/*
 * Game class, implementation for the core business logic for the game
 */
public class Game {
	private Board boardObj;
	private int turnCount = 1, whichPlayer = 1;
	private Player player1;
	private Player player2;
	public Scanner input = new Scanner(System.in);

	public Game() {
		boardObj = new Board();
		choosePlayers();
		// loop too play the game till it returns false(game over!!)
		while (play())
			;
	}

	/*
	 * choosePlayers() ask user to choose the players for the game sets player1
	 * and player2 to either human or computer
	 */
	public void choosePlayers() {
		System.out.println("Player 1 ?");
		if (choosePlayer() == 1)
			this.player1 = new Human(1);
		else
			this.player1 = new Computer(1);

		System.out.println("Player 2 ?");

		if (choosePlayer() == 1)
			this.player2 = new Human(2);
		else
			this.player2 = new Computer(2);

	}

	public int choosePlayer() {
		int option = 0;
		do {
			System.out.println("Human --> 1, Computer --> 2");
			System.out.print("Option: ");
			option = input.nextInt();

			if (option != 1 && option != 2)
				System.out.println("Invalid Option! Try again");
		} while (option != 1 && option != 2);
		return option;
	}

	/*
	 * boolean play() return: boolean --> true is game is in session false -->
	 * false if game has been won or drawn update the number of turns taken
	 * update the player count which would tell which user's turn it is
	 */
	public boolean play() {
		boardObj.showBoard();
		if (whoWon() == 0) {
			System.out.println("******************************************");
			System.out.println("Player  " + whichPlayerPlays() + " turn");

			if (whichPlayerPlays() == 1)
				player1.play(boardObj);
			else
				player2.play(boardObj);

			if (boardObj.fullBoard()) {
				System.out.println("Game drawn!!");
				return false;
			}
			whichPlayer++;
			turnCount++;
			return true;
		} else {
			if (whoWon() == -1)
				System.out.println("Player 1 won!!");
			else
				System.out.println("Player 2 won!!");
			return false;
		}

	}

	/*
	 * int whichPlayerPlays() return - int --> which player's turn it is 1 -->
	 * player 1 2 --> player 2
	 */
	public int whichPlayerPlays() {
		if (whichPlayer % 2 == 1)
			return 1;
		else
			return 2;
	}

	/*
	 * int whoWon() return: int --> which player won 1 --> player 2 -1 -->
	 * player 1 0 --> draw game
	 */
	public int whoWon() {
		if (boardObj.checkRowColumnAndDiagonal() == 1)
			return 1;
		if (boardObj.checkRowColumnAndDiagonal() == -1)
			return -1;
		return 0;
	}

}
