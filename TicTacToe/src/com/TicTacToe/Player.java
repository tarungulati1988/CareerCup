/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Player.java, Apr 5, 2014, 10:53:23 PM	
 *  TicTacToe, com.TicTacToe
 *
 */
package com.TicTacToe;

/*
 * abstract class for player description
 * will be extended by the human and computer player class
 */
public abstract class Player {
    
    protected int[] locationOnBoard = new int[2];
    protected int player;

    public Player(int player){
        this.player = player;
    }
    
    public abstract void play(Board board);
    
    public abstract void positionOnBoard(Board board);

    public boolean checkForPositionOnBoard(int[] attempt, Board board){
        if(board.getPosition(attempt) == 0)
            return true;
        else
            return false;
            
    }
    
}
