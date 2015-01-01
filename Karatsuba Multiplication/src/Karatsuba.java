import java.lang.Math;
/*
 * 
 *  author: Tarun Gulati. tgulati@indiana.edu
 * 	implements the Karatsuba multiplication algorithm for long integers\
 *  x.y = (10^n(a.c)) + (10^(n/2)*(ad + bc)) + bd
 * 
 */

public class Karatsuba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long num1, num2;
		num1 = 5678;
		num2 = 1234;
		long n1, n2;
		long n = ( Long.toString(num1).length() >= Long.toString(num2).length() ) ? Long.toString(num1).length() : Long.toString(num2).length();
		System.out.println(n);
		//step 1: divide x and y into a,b,c and d
		long a, b, c, d;
		a = Long.parseLong(Long.toString(num1).substring(0, (Long.toString(num1).length()/2)));
		b = Long.parseLong(Long.toString(num1).substring((Long.toString(num1).length()/2), (Long.toString(num1).length())));
		c = Long.parseLong(Long.toString(num2).substring(0, (Long.toString(num2).length()/2)));
		d = Long.parseLong(Long.toString(num2).substring((Long.toString(num2).length()/2), (Long.toString(num2).length())));
		System.out.println(a + " " + b + " " + c + " " + d);
		//step 2: Calculate ac and bd
		long AC, BD;
		AC = a * c; // --> 1
		BD = b * d; // --> 2
		System.out.println("AC = " + AC);
		System.out.println("BD = " + BD);
		//step 3: (a +b)(c + d) = ac + ad + bc + bd
		long temp = a*c + a*d + b*c + b*d; // --> 3
		System.out.println("temp = " + temp);
		//step 4: 3 -2 -1
		long sum = temp - AC - BD; // --> 4
		System.out.println("sum = " + sum);
		//step 5: pad 1 with n zeroes and pad 4 with n/2+1 zeroes
		for(int i = 0 ; i < n ; i ++){
			AC = AC * 10;
			if( i < n/2 ){
				sum = sum * 10;
			}
		}
		System.out.println(AC + " " + n + " " + sum);
		long finalSum = AC + sum + BD;
		System.out.println("product of x and y is = " + finalSum);
	}

}
