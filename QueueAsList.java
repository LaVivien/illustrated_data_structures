public class QueueAsList {

	Node front = null;
	Node rear = null;

	//Add an element at the rear, Time O(1), Space O(1)
	void enqueue(int value) {
		Node newNode = new Node(value);
		if (front == null)  //empty queue
			front = rear = newNode;
		else {
			rear.next = newNode;
			rear = newNode;
		}
	}
	
	//Remove the element from the front, Time O(1), Space O(1)
	int dequeue() {
		if (front == null) {
			System.out.println("The queue is empty.");
			return Integer.MIN_VALUE;
		}
		int item = front.data;
		front = front.next;
		if (front == null) 
			rear = null;			
		return item;
	}
	
	//Return the front value, Time O(1), Space O(1)
	int peek() {
		if (front == null) {	
			System.out.println("The queue is empty.");
			return Integer.MIN_VALUE;
		}
		return front.data;
	}
	
	//Print all elements, Time O(n), Space O(1)
	void print() { 
        Node curr = front;  
        while (curr != null) { 
            System.out.print(curr.data + " "); 
            curr = curr.next; 
        } 
        System.out.println();
    } 	
	
	//Return the length of the queue, Time O(n), Space O(1)
	int size() {
		int length = 0;
		Node node = front;  
		while (node != null) { 
			length++; 
			node = node.next; 
		} 
		return length;
	}

	public static void main(String[] args) {
		//Initialize, call enqueue, size
		QueueAsList queue = new QueueAsList();
		queue.enqueue(7);
		queue.enqueue(10);
        queue.enqueue(5);
        queue.enqueue(12);  
        queue.enqueue(6);
		queue.print();
        System.out.println("size: " + queue.size());
	    System.out.println("peek: " + queue.peek());
    
	    //Dequeue
	    queue.dequeue();  
	    System.out.println("\nAfter dequeue:");
	    queue.print();
	    System.out.println("size: " + queue.size());
	    System.out.println("peek: " + queue.peek());   
    }
}
