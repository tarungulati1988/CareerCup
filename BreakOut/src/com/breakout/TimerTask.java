/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *          Prashant Chauhan, chauhanp@indiana.edu
 *          Sidharth Jhawar, sjhawar@indiana.edu
 *          Pallavi Pudakalakatti, ppudakal@indiana.edu
 *	TimerTask.java, Aug 30, 2013, 5:51:42 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JButton;
import javax.swing.Timer;

public class TimerTask implements Observable{
	public Timer t;
	public Board board;
	static boolean isRunning = true;
	Map<Observer, Board> observerList;
	
	public TimerTask(Board board)
	{	
		t = new Timer(10, new TimerTaskListener());
		observerList = new ConcurrentHashMap<Observer, Board>();
		this.board = board;
	}
	
	public void run()
	{
		t.start();
	}
	
	public void stop(){
		t.stop();
	}
	
	class TimerTaskListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			notifyObservers();	
		}	
	}

	@Override
	public void register(Observer observer, Board com) {
		observerList.put(observer, com);
	}

	@Override
	public void unRegister(Observer observer) {
		observerList.remove(observer);
	}

	@Override
	public void notifyObservers() {
		@SuppressWarnings("rawtypes")
		Iterator it = observerList.entrySet().iterator();
		
		while(it.hasNext())
		{
			@SuppressWarnings({ "rawtypes", "unchecked" })
			Map.Entry<Observer, Board> entry = (Map.Entry)it.next();
			Observer ob = entry.getKey();
			Board com = entry.getValue();
			ob.update();
			ob.draw(com);
		}
	}
	
	
	public void press(JButton button, Board board){
		//TODO parameter to be passed
		//that parameter button.execute()
		System.out.println(button.getText());
		//board.setFocusable(true);
		//button.setFocusable(false);
		board.requestFocus();
	}
}
