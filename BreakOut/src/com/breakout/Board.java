/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *          Prashant Chauhan, chauhanp@indiana.edu
 *          Sidharth Jhawar, sjhawar@indiana.edu
 *          Pallavi Pudakalakatti, ppudakal@indiana.edu
 *	Board.java, Aug 29, 2013, 8:12:55 PM	
 *  BreakOut, com.breakout
 *
 */
package com.breakout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Board extends JFrame implements Dimensions{
	
	//Variable Declaration
	private boolean isFirstTime;
	public boolean isRunning;
	//public boolean isPaused;
	public JPanel contentPane;
	public JPanel panelRight;
	public JPanel panelLeft;
	public JLabel timeLabel;
	public JLabel msgLabel;
	public JButton startStopBtn;
	public JButton pausePlayBtn;
	public JButton undoBtn;
	public JButton replayBtn;
	public Ball ball;
    public Paddle paddle;
    public Brick brick;
    public TimerTask timerTask;
    public GameTimer gameTimer;
    public PausePlay pausePlayObj;
    public Undo undoObj;
    public Replay replayObj;
	
	public Board() {
		
		isRunning = false;
		isFirstTime = true;
		//isPaused = false;
		
		panelRight = new JPanel();
		panelLeft = new JPanel();
		timeLabel = new JLabel();
		msgLabel = new JLabel();
		
		paddle = new Paddle();
		brick = new Brick();
		gameTimer = new GameTimer();
		timerTask = new TimerTask(this);
		ball = new Ball(this);
		
		// build the main container JFrame
		setTitle("Break Out Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Dimensions.START_X, Dimensions.START_Y, Dimensions.WIDTH, Dimensions.HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		// build the right panel of the JFrame
		// the ball, paddle and bricks are in this panel
		contentPane.add(layeredPane, BorderLayout.CENTER);
		panelRight.setBackground(Color.LIGHT_GRAY);
		panelRight.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRight.setBounds(110, 11, 454, 330);
		layeredPane.add(panelRight);
		
		// build the right panel of the JFrame
		// the timer and the game messages are displayed here
		panelLeft.setBackground(Color.GRAY);
		panelLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLeft.setBounds(10, 11, 90, 330);
		layeredPane.add(panelLeft);
		
		// time label to display the time elapsed since the game starts
		timeLabel.setFont(new Font("SansSerif",Font.BOLD, 22));
		timeLabel.setText("00:00");
		panelLeft.add(timeLabel);
		
		// message label to print a message when the game finishes
		msgLabel.setFont(new Font("SansSerif",Font.BOLD, 12));
		msgLabel.setForeground(Color.RED);
		panelLeft.add(msgLabel);
		
		// start/stop button
		startStopBtn = new JButton("Start");
        //startStopBtn.setBounds(5, 6, 80, 30);
        panelLeft.add(startStopBtn);
        
        //ActionListener for the start/stop button
        startStopBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(isFirstTime){
					//Register the gameTimer
			    	timerTask.register(gameTimer, Board.this);
			    	
			    	//Register ball
			    	timerTask.register(ball, Board.this);
			    	
			    	//Register Paddle
			    	timerTask.register(paddle, Board.this);
			    	gameTimer.startTime = System.currentTimeMillis();
			    	gameTimer.pausedTimer = 0;
			    	//Trigger the timerTask
					timerTask.run();
					
			    	isRunning = true;
			    	isFirstTime = false;
			    	//Board.this.setFocusable(true);
			    	//timerTask.press(startStopBtn,Board.this);
			    	startStopBtn.setText("Stop");
			    	Board.this.requestFocus();
				}
				else if(isRunning == true && (startStopBtn.getText()).equalsIgnoreCase("Stop")){
					isRunning = false;
					isFirstTime = true;
					//Board.this.msgLabel.setText("Game Over!");
					Board.this.timerTask.unRegister(ball);
					ball.resetState();
					//Board.this.timerTask.unRegister(paddle);
					paddle.resetState();
					Board.this.timerTask.unRegister(gameTimer);
	
					startStopBtn.setText("Start");
					//Board.this.panelRight.removeAll();
					//Board.this.panelRight.repaint();
				}
			}
		});
        
        // pause/play button
        pausePlayBtn = new JButton("Pause");
        //pausePlayBtn.setBounds(5, 6, 80, 30);
        //pausePlayBtn.setEnabled(false);
        panelLeft.add(pausePlayBtn);
        
      //ActionListener for the pause/play button
        pausePlayBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isRunning== true && (pausePlayBtn.getText()).equalsIgnoreCase("Pause")){
					//Board.this.timerTask.unRegister(gameTimer);
					gameTimer.pause();
					timerTask.stop();
					pausePlayBtn.setText("Play");
				}
				else if((pausePlayBtn.getText()).equalsIgnoreCase("Play")){
					//Board.this.timerTask.register(gameTimer, Board.this);
					timerTask.run();
					gameTimer.startTime = System.currentTimeMillis();
					pausePlayBtn.setText("Pause");
				}
			}
		});
        
        // undo button
        undoBtn = new JButton("Undo");
        //pausePlayBtn.setBounds(5, 6, 80, 30);
        undoBtn.setEnabled(false);
        panelLeft.add(undoBtn);
        
        // undo button
        replayBtn = new JButton("Replay");
        //pausePlayBtn.setBounds(5, 6, 80, 30);
        replayBtn.setEnabled(false);
        panelLeft.add(replayBtn);
		
		addKeyListener(new StrokeAdapter());
		
	}
	
	/** 
	 * Key Listener adapter class
	 */
	class StrokeAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent ke) {
			int key = ke.getKeyCode();
			
			/*if(key == KeyEvent.VK_SPACE && isFirstTime == true)
			{
				//Register the gameTimer
		    	timerTask.register(gameTimer, Board.this);
		    	
		    	//Register ball
		    	timerTask.register(ball, Board.this);
		    	
		    	//Register Paddle
		    	timerTask.register(paddle, Board.this);
		    	gameTimer.startTime = System.currentTimeMillis();
		    	//Trigger the timerTask
				timerTask.run();
				
		    	isRunning = true;
		    	isFirstTime = false;
		  
			}*/if(key == KeyEvent.VK_LEFT && isRunning == true){
				paddle.move("left");
			}else if(key == KeyEvent.VK_RIGHT && isRunning == true){
				paddle.move("right");
			}
		}
		
		public void keyReleased(KeyEvent ke){
			if(isRunning == true)
			{
				paddle.KeyReleased();
			}
		}
	}
	
	
	/** 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Board boardObj;
		boardObj = new Board();
		boardObj.setVisible(true);
		boardObj.setFocusable(true);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	*/
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		/*
	   	 * Paddle
	   	 * fillRect(x, y, width, height)
	   	 */
        g.setColor(Color.BLACK);
        g.fillRect(paddle.getX(), paddle.getY(), Dimensions.PADDLE_WIDTH, Dimensions.PADDLE_HEIGHT);
		/*
		 * Ball			 
		 * * fillOval(x, y, width, height)
		 */
		g.setColor(Color.RED);
		g.fillOval(ball.getX(), ball.getY(), Dimensions.BALL_WIDTH, Dimensions.BALL_HEIGHT);
		/*
	   	 * Brick
	   	 * fillRect(x, y, width, height)
	   	 */
		g.setColor(Color.BLUE);
		g.fillRect(brick.getX(), brick.getY(), brick.width, brick.height);		
	}
}
