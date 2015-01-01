/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Board.java, Apr 5, 2014, 9:47:43 PM	
 *  TicTacToe, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class Board {
	private   char posn[]=new char[10];
	
	public  void newBoard()
    {
        
        char posndef[] = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 1 ; i < 10 ; i++){
        	posn[i]=posndef[i];
        }
        
    }
	
	
	public  String currentBoard()
    {
        System.out.println( "\n\n" );
        System.out.println(  "\n\n" );
        System.out.println(  "\n\n\t\t" + posn [1] + "   | " +posn [2]+ "  | " +posn [3]);
        System.out.println(  " \t\t    |    |   " );
        System.out.println(  " \t\t ___|____|___ " );
        System.out.println(  "\n\n\t\t" +posn [4]+ "   | " +posn [5]+ "  | " +posn [6]);
        System.out.println(  " \t\t    |    |   " );
        System.out.println(  " \t\t ___|____|___ " );
        System.out.println(  "\n\n\t\t" +posn [7]+ "   | " +posn [8]+ "  | " +posn [9]);
        System.out.println(  " \t\t    |    |   " );
        System.out.println(  " \t\t    |    |   " );
        System.out.println(  "\n\n" );
        return "currentBoard";
    }
}
