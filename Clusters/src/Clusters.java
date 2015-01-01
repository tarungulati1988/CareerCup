/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Clusters.java, Mar 2, 2014, 6:09:48 PM	
 *  Clusters, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class Clusters {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        int[] nodes = {5, 5, 5, 5, 5, 5};
        int[] loops = new int[nodes.length];
        for (int i = 0; i < loops.length; i++) {
            loops[i] = -1;
        }

        for (int i = 0; i < loops.length; i++) {
            if (loops[i] == -1) {
                int s = mark(i, i, loops, nodes);

                if (s != i) {
                    for (int j = 0; j < loops.length; j++) {
                        if (loops[j] == i) {
                            loops[j] = s;
                        }
                    }
                }
            }
        }

        int[] count = new int[nodes.length];
        int sum = 0;
        for (int cluster : loops) {
            if (count[cluster] == 0) {
                count[cluster] = 1;
                sum++;
            }
        }
        if( loops[0] == 0 ){
            sum--;
        }

        System.out.println(sum);
    }

    public static int mark(int loop, int i, int[] loops, int[] nodes) {
        if (loops[i] == -1) {
        	loops[i] = loop;
            if (nodes[i] != i) {
                return mark(loop, nodes[i], loops, nodes);
            } else {
                return i;
            }
        } else {
            return loops[i];
        }
    }

}
