/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Brick.java, Aug 29, 2013, 2:00:26 PM	
 *  Brick, com.Breakout.brick
 *
 */
package com.Breakout.brick;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Tarun Gulati
 *
 */
public class Brick extends JFrame{
	
	public void paint (Graphics g) {
	    g.setColor(Color.BLUE);
		// fillRect(int x, int y, int width, int height)
	    g.fillRect (20, 50, 80, 15);
	  }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	        Brick brickObj = new Brick();
	        brickObj.setSize(700,500);  
	        brickObj.setVisible(true); 
	}

}
