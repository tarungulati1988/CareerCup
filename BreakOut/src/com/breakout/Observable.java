/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *          Prashant Chauhan, chauhanp@indiana.edu
 *          Sidharth Jhawar, sjhawar@indiana.edu
 *          Pallavi Pudakalakatti, ppudakal@indiana.edu
 *	Observable.java, Aug 29, 2013, 8:14:04 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import javax.swing.JComponent;

/**
 * @author Tarun Gulati
 *
 */
public interface Observable {
	public void register(Observer observer, Board com);
	public void unRegister(Observer observer);
	public void notifyObservers();
}
