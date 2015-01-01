/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	JumpGame.java, Feb 11, 2014, 8:38:36 PM	
 *  JumpGame, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
import java.util.*;
/*
 * http://www.leetcode.com/onlinejudge
 *
 * JumpGame1
 Given an array of non-negative integers, you are initially positioned at the first index of the array.
 Each element in the array represents your maximum jump length at that position.
 Determine if you are able to reach the last index.
 For example:
 A = [2,3,1,1,4], return true.
 A = [3,2,1,0,4], return false.

 
 * JumpGame2
   Given an array of non-negative integers, you are initially positioned at the first index of the array.
   Each element in the array represents your maximum jump length at that position.
   Your goal is to reach the last index in the minimum number of jumps.
   For example:
   Given array A = [2,3,1,1,4]
   The minimum number of jumps to reach the last index is 2.
   (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
public class JumpGame {

    // solution to problem 1:
    // record the furthest step
    public boolean canJump(int[] A) {
        int max = 0;
        int i=0;
        while (i<=max) {
            if (i+A[i]>max) max = i+A[i];
            if (max>=A.length-1) return true;
            i++;
        }
        return false;
    }


    /* solutions to problem 2
     *
     * Best Approach O(n)!!!! 
     * Greedy, jump as far as possible
     * http://www.mitbbs.com/article_t1/JobHunting/32118959_0_1.html
     */
    public int jump(int[] A) {
        int start, end, max, step;
        start = end = step = 0;
        while (end<A.length-1) {
            max = 0;
            while(start<=end) {
                max = Math.max(start+A[start],max);
                start++;
            }
            step++;
            System.out.println(max);
            start = end+1;
            end = max;
            System.out.println(" : " + end);
        }
        return step;
    }
   /* public int jump(int[] A) {
        if (A==null||A.length==0){
            return -1;
        }
        
        if (A.length==1){
            return 0;
        }
        
        int minStep=0;
        
        int start=0;
        // current longest distance the jump can reach
        int end=A[start];
        
        // if current longest distance plus current postion passed the length of array
        // then return current minStep + 1;
        if (start+end>=A.length-1){
            return minStep+1;
        }
        
        while(end<A.length-1){
            minStep++;
            
            // record farest position can be reached by jump from position within current farest position
            int max=0;
            
            for (int i=start; i<=end; i++){
                int current=i+A[i];
                // pass the total length, return minStep+1, 
                
                if (current>=A.length-1){
                    return minStep+1;
                }
                
                max=Math.max(max, current);
            }
            // update start position(items in array are not negative, so end+1 is exist)
            start=end+1;
            // update the most far position can reached for next jump
            end=max;
        }
 
        return minStep;
    }*/
    /*
     * Approach 2: DP
     */
     public int jumpDP(int[] A) {
        int len = A.length;
        int[] cache = new int[len];
        Arrays.fill(cache,len);
        cache[len-1]=0;
        
        for (int i=len-2; i>=0; i--) {
            int step = A[i];
            if (i+step>=len-1) {
                cache[i] = 1;
            } else {
                int min = len;
                while(step>0) {
                    min = Math.min(cache[i+step]+1, min);
                    step--;
                }
                cache[i] = min;
            }
        }
        return cache[0];
    }

 
    public static void main(String[] args) {
        JumpGame j = new JumpGame();
        System.out.println(j.jump(new int[] {5,6,0,4,2,4,1,0,0,4}));
        System.out.println(j.jump(new int[] {2,3,1,1,4}));
        //System.out.println(Integer.MAX_VALUE);
    }
}