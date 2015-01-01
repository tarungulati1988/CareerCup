/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *          Prashant Chauhan, chauhanp@indiana.edu
 *          Sidharth Jhawar, sjhawar@indiana.edu
 *          Pallavi Pudakalakatti, ppudakal@indiana.edu
 *	Timer.java, Aug 29, 2013, 8:11:25 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import javax.swing.JLabel;

public class GameTimer implements Observer{
	
	public boolean isPaused = false;
	
	//Storing the current System time
	public static long startTime;
	
	//Variable to store the elapsed time
	public static long timeElapsed;
	
	public static long pausedTimer;
	
	//Update method to find the elapsed time
	@Override
	public void update(){
		timeElapsed = System.currentTimeMillis() - startTime;
		System.out.println(timeElapsed + " : " + startTime);
	}
	
	//Draw the eplased time on the panel
	@Override
	public void draw(Board com) {
		JLabel label = (JLabel)com.timeLabel;
		
		int sec = (int)((timeElapsed/1000)%60);
		int min = (int)(timeElapsed/(60*1000));
		
		String secString = String.format("%02d", sec);
		String minString = String.format("%02d", min);
		
		label.setText(minString+":"+secString);
		label.repaint();
	}
	

	public void pause() {
			    // Subtract elapsed time from the remaining time and stop timing
				long now = System.currentTimeMillis();
				timeElapsed = 1 + timeElapsed;
			    
			    /*int sec = (int)((timeElapsed /1000)%60);
				int min = (int)(timeElapsed/(60*1000));
				
				String secString = String.format("%02d", sec);
				String minString = String.format("%02d", min);
				System.out.println(min + ":" + sec);*/
				//timerTask.t.stop();
			   // timeElapsed -= (startTime - timeElapsed);
	}
}
