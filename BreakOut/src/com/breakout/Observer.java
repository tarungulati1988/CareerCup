/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *          Prashant Chauhan, chauhanp@indiana.edu
 *          Sidharth Jhawar, sjhawar@indiana.edu
 *          Pallavi Pudakalakatti, ppudakal@indiana.edu
 *	Observer.java, Aug 29, 2013, 8:14:24 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * @author Tarun Gulati
 *
 */
public interface Observer {
	public void update();
	public void draw(Board com);
}
