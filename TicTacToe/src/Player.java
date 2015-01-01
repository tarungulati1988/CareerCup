/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Player.java, Apr 5, 2014, 9:48:04 PM	
 *  TicTacToe, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class Player {

	private   char player;
	
	public  void nextPlayer()
    {
        if (player == 'X')
        player = 'O';
        else player = 'X';
        
    }
    
    
    public  char getPlayer()
    {
        return player;
    }
}
