/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	SubstringsOfAString.java, Mar 2, 2014, 5:24:19 PM	
 *  SubstringsOfAString, 
 *
 */

import java.util.HashMap;
/**
 * @author Tarun Gulati
 *
 */
import java.util.Scanner;
 
class SubstringsOfAString
{
	public static int count = 0;
	public static void generate(String word) {
        if (word.length() == 1) {
            System.out.println(word);
            return;
        }else{
            System.out.println(word);
            count++;
            generate(word.substring(0, word.length()-1)); 
            generate(word.substring(1, word.length())); 
        }

    }
	
	
   public static void main(String args[])
   {
      String string, sub;
      int i, c, length;
 
      Scanner in = new Scanner(System.in);
      System.out.println("Enter a string to print it's all substrings");
      string  = in.nextLine();
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      length = string.length();   
      int cnt = 0;
      System.out.println("Substrings of \""+string+"\" are :-");
 
      for( c = 0 ; c < length ; c++ )
      {
         for( i = 1 ; i <= length - c ; i++ )
         {
        	 sub = string.substring(c, c+i);
        	 if(!map.containsKey(sub)){
        		 map.put(sub, cnt);
                 
                 System.out.println(sub);
                 cnt++;
        	 }
        	 
            
         }
      }
      
      
      generate("hello");
      System.out.println(count);
      System.out.println(cnt);
   }
}