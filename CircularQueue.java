public class CircularQueue {

	int[] queue;  
	int maxSize; 
	int frontIndex, rearIndex;
	int length = 0;
	  
	//Constructor, Time O(1), Space O(1)
	CircularQueue(int capacity) {
		maxSize = capacity;
	    frontIndex = -1;
	    rearIndex = -1;
	    queue = new int[maxSize];
	}

	//Insert an element at the rear, Time O(1), Space O(1)
	void enqueue(int value) {
		if (isFull()) { 
			System.out.println("The queue is full");
			return;
		}
		if (frontIndex == -1) //empty queue
			frontIndex = 0;
		rearIndex = (rearIndex + 1) % maxSize;
		queue[rearIndex] = value;
		length++;	    
	}

	//Remove the element from the front, Time O(1), Space O(1)
	int dequeue() {
		if (isEmpty()) {
			System.out.println("The queue is empty");
			return Integer.MIN_VALUE;
		}
		int item = queue[frontIndex];
		if (frontIndex == rearIndex) { //delete the last one, reset
			frontIndex = -1;
			rearIndex = -1;
		} else 
			frontIndex = (frontIndex + 1) % maxSize;
		length--;
		return item;
	}
	
	//Return the front value, Time O(1), Space O(1)
	int peek() {
	    if (isEmpty()) {
	    	System.out.println("The queue is empty");
	    	return  Integer.MIN_VALUE;
	    }
	    return queue[frontIndex];
	}
	
	//Print all elements, Time O(n), Space O(1)
	void print() {
		if (isEmpty()) 
			return;
	    int i;
	    for (i = frontIndex; i != rearIndex; i = (i+1) % maxSize)
	    	System.out.print(queue[i] + " ");
	    System.out.println(queue[i]);
	}
	  
	//return the length of the queue, Time O(1), Space O(1)
	int size() {
		return length;
	}
	  
	// Check if the queue is full, Time O(1), Space O(1)
	boolean isFull() {
		return (rearIndex + 1) % maxSize == frontIndex;
	}

	//Check if the queue is empty, Time O(1), Space O(1)
	boolean isEmpty() {
		return frontIndex == -1;
	}

    public static void main(String args[]) {
    	//Initialize, enqueue, size
    	CircularQueue queue = new CircularQueue(5); 
	    queue.enqueue(10);
	    queue.enqueue(2);
	    queue.enqueue(31);
	    queue.enqueue(18);
	    queue.enqueue(54);
		queue.print();
	    System.out.println("size: " + queue.size());  
	    System.out.println("peek: " + queue.peek());
	    
	    //Dequeue 
	    System.out.println("\nDequeue:");
        queue.dequeue();  
        queue.print();
	    System.out.println("size: " + queue.size()); 
	    System.out.println("peek: " + queue.peek());
    }
}
