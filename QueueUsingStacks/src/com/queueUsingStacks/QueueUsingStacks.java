/*
 * Author : Tarun Gulati
 * email : tgulati@indiana.edu, tarun.gulati1988@gmail.com
 * 
 * Queue implementation using 2 stacks...
 * keep rocking \m/
 */

package com.queueUsingStacks;

import static java.lang.System.*;


class stackImplementation{
	private int[] storage;
	private int stackSize;
	
	stackImplementation(int capacity){
		if(capacity <= 0)
			out.println("must be positive!!");
		storage = new int[capacity];
		stackSize = 0;
	}
	
	void push(int data){
		if(stackSize == storage.length)
			throw new StackException("Stack Overflow!!");
		storage[stackSize] = data;
		stackSize++;
	}
	
	int pop(){
		if(stackSize == -1)
			throw new StackException("Stack Empty!!");
		//out.println("..." + storage[stackSize-1]);
		return storage[--stackSize];
	}
	
	boolean isEmpty(){
		return (stackSize == -1);
	}
	
	int top(){
		if(stackSize == -1)
			throw new StackException("Stack Empty!!");
		return storage[stackSize];
	}
	
	void display(){
		if(!isEmpty()){
			for(int i = 0; i < stackSize; i++){
				out.println(storage[i]);
			}
		}
	}
	
	
	public class StackException extends RuntimeException{
		public StackException(String msg){
			super(msg);
		}
	}
	
}



public class QueueUsingStacks {
	public static void main(String[] args){
		stackImplementation st = new stackImplementation(10);
		stackImplementation st2 = new stackImplementation(10);
		st.push(1);
		st.push(3);
		st.push(4);
		st.push(5);
		st.push(6);
		st.display();
		out.println("------------------------");
		st2.push(st.pop());
		st2.push(st.pop());
		st2.push(st.pop());
		st2.push(st.pop());
		st2.push(st.pop());
		st2.display();
		int temp = st2.pop();
		out.println("temp= " + temp);
	}
	
	

}
