import java.util.Scanner;

/**
 * 
 */

/**
 * @author tgulati
 *
 */
public class Pangram {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String inputString = "";

        System.out.print("Enter the input: ");
        inputString = sc.nextLine();
        sc.close();

        if (inputString != null && inputString.length() > 0) {
            System.out.print((isPanagram(inputString) ? "pangram" : ("not pangram")));
        } else {
            System.out.print("Not a valid string!");
        }
    }

    /**
     * Checks if the given input is a panagram.
     * 
     * @param inputString
     * @return boolean
     */

    private static boolean isPanagram(String inputString) {

        if (inputString.length() < 26) {
            return false;
        }

        inputString = inputString.toLowerCase();

        for (char i = 'a'; i <= 'z'; i++) {
            if (!(inputString.contains(i + ""))) {
                return false;
            }
        }

        return true;
    }

}
