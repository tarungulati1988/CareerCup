/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *          Prashant Chauhan, chauhanp@indiana.edu
 *          Sidharth Jhawar, sjhawar@indiana.edu
 *          Pallavi Pudakalakatti, ppudakal@indiana.edu
 *	Paddle.java, Aug 29, 2013, 8:09:51 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Sprite implements Dimensions, Observer{
	
	public Paddle()
	{
		//Setting the initial coordinates
		x = 300;
		y = 360;
	}
	
	public void resetState(){
		x = 300;
		y = 360;
	}
	Sprite spriteObj = new Sprite();
	
	int dx;
	/*
	 * 	keyPressed()
	 */
	public void KeyPressed(){
		
	}
	/*
	 * 	keyReleased()
	 */
	public void KeyReleased(){
		dx = 0;
	}
	/*
	 * 	move()
	 */
	public void move(String directionToMove){
		if(directionToMove.equals("left")){
			dx = -2;
		}else if (directionToMove.equals("right")){
			dx = 2;
		}
	}

	/*
	 * 	paint()
	 */
	public void paint(Graphics g){
		g.setColor(Color.GREEN);
		// fil;Rect(int x, int y, int width, int height)
		g.fillRect(250, 480, 80, 5);
	}
	
	// updates the x position of the paddle
	@Override
	public void update(){
		x += dx;
		if(x <= 2){
			x = 2;
		}else if(x >= Dimensions.PADDLE_RIGHT){
			x = Dimensions.PADDLE_RIGHT;
		}else if(x <= Dimensions.PADDLE_LEFT){
			x = Dimensions.PADDLE_LEFT;
		}
	}
	
	@Override
	public void draw(Board com){
		com.repaint();
	}
}
