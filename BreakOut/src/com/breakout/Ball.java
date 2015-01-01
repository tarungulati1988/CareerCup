/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *          Prashant Chauhan, chauhanp@indiana.edu
 *          Sidharth Jhawar, sjhawar@indiana.edu
 *          Pallavi Pudakalakatti, ppudakal@indiana.edu
 *	Ball.java, Aug 29, 2013, 8:10:19 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import java.util.Random;

public class Ball extends Sprite implements Dimensions, Observer{
	public int xdir;
	public int ydir;	
	public Board board;
	
	public Ball(Board board)
	{
		this.board = board;
		
		//Setting initial coordinates of ball
		x = 325;
		y = 330;
		
		//Setting initial direction in which ball should move
		int arrXdir[] = {1,-1};
		
		Random random = new Random();
		
		xdir = arrXdir[random.nextInt(arrXdir.length)];
		ydir = -1;
	}
	
	public void resetState()
	{
		x = 325;
		y = 330;
		int arrXdir[] = {1,-1};
		
		Random random = new Random();
		
		xdir = arrXdir[random.nextInt(arrXdir.length)];
		ydir = -1;
	}
	//Check if the ball collides with the right end
	public boolean isCollidingWithRightWall()
	{
		if(x >= 548)
		{
			return true;
		}
		return false;
	}

	//Check if the ball collides with the top end
	public boolean isCollidingWithTopWall()
	{
		if(y <= 45)
		{
			return true;
		}
		return false;
	}
	
	//Check if the ball collides with the left end
	public boolean isCollidingWithLeftWall()
	{
		if(x <= 125)
		{
			return true;
		}
		return false;
	}
	
	//Check if the ball collides with the paddle
	public boolean isCollidingWithPaddle()
	{
		
		if(x >= board.paddle.getX() - 30 && x <= board.paddle.getX() + board.paddle.PADDLE_WIDTH + 30)
		{
			if(y >= 330 && y <= 335)
			{
				return true;
			}
		}
		return false;
	}
	
	//Check if the ball collides with the brick
	public boolean isCollidingWithBrick()
	{
		if(x >= board.brick.getX() - board.BALL_HEIGHT  && x <= board.brick.getX() + board.brick.width && y >= board.brick.getY() - board.BALL_HEIGHT && y <= board.brick.getY() + board.brick.height)
		{
			board.brick.height = 0;
			board.brick.width = 0;
			return true;
		}
		return false;
	}
	
	//Check if the ball has touched the floor
	public boolean isGameOver()
	{
		if(x <= board.paddle.getX() - 40 || x >= board.paddle.getX() + 40)
		{
			if(y >= 400)
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 	update() method of the Observer class
	 * Used to update the position of the ball
	*/
	@Override
	public void update(){
		
		if(isCollidingWithRightWall())
		{
			xdir = -1;
		}
		
		if(isCollidingWithTopWall())
		{
			ydir = 1;
		}
		
		if(isCollidingWithLeftWall())
		{
			xdir = 1;
		}
		
		if(isCollidingWithPaddle())
		{
			ydir = -1;
		}
		
		if(isCollidingWithBrick())
		{
			// player wins when brick is destroyed
			// all the observers are unregistered
			board.panelRight.repaint();
			board.isRunning = false;
			board.msgLabel.setText("You win!  ");
			board.timerTask.unRegister(this);
			board.timerTask.unRegister(board.paddle);
			board.timerTask.unRegister(board.gameTimer);
			board.replayBtn.setEnabled(true);
		}
		
		if(isGameOver())
		{
			// player looses when the ball falls through the floor
			// game over message is printed
			// and all the observers are unregistered
			board.isRunning = false;
			board.msgLabel.setText("Game Over!");
			board.timerTask.unRegister(this);
			board.timerTask.unRegister(board.paddle);
			board.timerTask.unRegister(board.gameTimer);
		}
		x += xdir;
		y += ydir;
	}

	/*
	 * Repaint the board
	 */
	@Override
	public void draw(Board com){
		com.repaint();
	}
}
