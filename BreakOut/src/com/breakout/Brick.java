/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *          Prashant Chauhan, chauhanp@indiana.edu
 *          Sidharth Jhawar, sjhawar@indiana.edu
 *          Pallavi Pudakalakatti, ppudakal@indiana.edu
 *	Brick.java, Aug 29, 2013, 8:10:30 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import java.util.Random;

public class Brick extends Sprite{
	
	public Brick()
	{
		//Setting the initial coordinates of the brick
		int arrX[] = {400, 300, 150, 200};
		
		// chooses a x co-ordinate randomly
		Random random = new Random();
		
		x = arrX[random.nextInt(arrX.length)];
		y= 140;
		
		//Setting the height and width of the brick
		height = 20;
		width = 60;
	}

}
