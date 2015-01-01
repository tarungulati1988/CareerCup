package com.excelColumnName.toNumber;

import static java.lang.System.*;

public class ExcelColumnNameToNumber {

	/**
	 * @param args
	 */
	public static int stringToNumberConverter(String str){
		str = str.toUpperCase();
		int value = 0, temp;
		for(int i = 0; i < str.length(); i++){
			temp = (str.charAt(i)) - 64;
			out.println("temp is: " + temp);
			value = (value * 26) + temp;
		}
		return value;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int columnNumber = stringToNumberConverter("az");
		out.println("The column number is: " + columnNumber);

	}

}
