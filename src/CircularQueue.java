import java.util.Arrays;

/**
 * 
 */

/**
 * @author tgulati
 * 
 */
public class CircularQueue {
	private int size;
	private int head;
	private int tail;
	private int q[];

	public CircularQueue(int s) {
		size = s;
		q = new int[s + 1];
		head = 0;
		tail = 0;
	}

	public synchronized void initialize() {
		head = 0;
		tail = 0;
	}

	public synchronized boolean enqueue(int v) {
		int tmp = (tail + 1) % size;
		if (tmp == head)
			return false;
		q[tail] = v;
		tail = tmp;
		return true;
	}

	public synchronized int dequeue() throws Exception {
		if (head == tail)
			throw new Exception("queue underflow!");
		int tmp = q[head];
		head = (head + 1) % size;
		return tmp;
	}

	public void printQueue() {
		System.out.println(Arrays.toString(q));
		System.out.println(head);
		System.out.println(tail);
	}

	public static void main(String[] args) throws Exception {
		CircularQueue myQueue = new CircularQueue(5);
		myQueue.enqueue(1);
		myQueue.printQueue();
		myQueue.enqueue(2);
		myQueue.printQueue();
		myQueue.enqueue(3);
		myQueue.printQueue();
		myQueue.enqueue(4);
		myQueue.printQueue();
		myQueue.enqueue(5);
		myQueue.printQueue();
		myQueue.enqueue(6);
		myQueue.printQueue();
		myQueue.dequeue();
		myQueue.dequeue();
		myQueue.dequeue();
		myQueue.dequeue();
	}
}