/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Human.java, Apr 5, 2014, 10:53:48 PM	
 *  TicTacToe, com.TicTacToe
 *
 */
package com.TicTacToe;

import java.util.Scanner;

/*
 * Human class, extends the player abstract class
 * defines implementation for the human player
 */
public class Human extends Player{
    public Scanner input = new Scanner(System.in);
    
    public Human(int player){
        super(player);
        this.player = player;
        System.out.println("Human player created!");
    }
    
    @Override
    public void play(Board board){
        positionOnBoard(board);
        board.setPosition(locationOnBoard, player);
    }
    
    @Override
    public void positionOnBoard(Board board){
        do{
            do{
                System.out.print("Row: ");
                locationOnBoard[0] = input.nextInt();
                
                if( locationOnBoard[0] > 3 ||locationOnBoard[0] < 1)
                    System.out.println("Invalid row, choose between 1-3");
                
            }while( locationOnBoard[0] > 3 ||locationOnBoard[0] < 1);
            
            do{
                System.out.print("Column: ");
                locationOnBoard[1] = input.nextInt();
                
                if(locationOnBoard[1] > 3 ||locationOnBoard[1] < 1)
                    System.out.println("Invalid column, choose between 1-3 ");
                
            }while(locationOnBoard[1] > 3 ||locationOnBoard[1] < 1);
            locationOnBoard[0]--; 
            locationOnBoard[1]--;
            if(!checkForPositionOnBoard(locationOnBoard, board))
                System.out.println("Place already filled, try another.");
        }while( !checkForPositionOnBoard(locationOnBoard, board) );
    }
}
