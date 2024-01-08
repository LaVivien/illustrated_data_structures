//Descending priority queue, the higher the value, the higher the priority
public class PriorityQueueAsHeap {

	int[] heap;
	int length;
	int maxSize;

	//Constructor, Time O(1) Space O(1)
	public PriorityQueueAsHeap(int capacity) {
		maxSize = capacity;
		heap = new int[maxSize]; 
		length = 0; 
	}

	//Insert a new item in the priority queue, call bubbleUp(), Time O(logn), Space O(1), 
	//n is the number of items in the queue,
	void enqueue(int value) {		
		if (length == maxSize) { //the queue is full
			System.out.println("The priority queue is full");
			return;
		}
		heap[length] = value;
		bubbleUp(length); //start from the last position
		length++;
	}

	//Called by enqueue(), move the new item up in the heap, Time O(logn), Space O(1)
	void bubbleUp(int index) {
		int parentIndex = (index-1)/2;
		int item = heap[index];
		while (index > 0 && item> heap[parentIndex]) {
			heap[index] = heap[parentIndex]; 
			index = parentIndex; 
			parentIndex = (parentIndex-1)/2;
		}
		heap[index] = item; 
	}

	//Remove the first item, place the next largest item at the front, 
	//Call BubbleDown(), Time O(logn), Space O(1)
	int dequeue() {	
		if (length == 0) { //the queue is empty
			System.out.println("The priority queue is empty");
			return Integer.MIN_VALUE;	
		}
		int max = heap[0];
		heap[0] = heap[length-1]; //move the last item to the first position
		length--;		
		bubbleDown(0);	//start from the first position	
		return max;
	}

	//Called by dequeue(), move the largest item at the front, Time O(logn), space O(1)
	void bubbleDown(int index) {
		int item = heap[index];
		int larger;
		while (index < length/2) {
			int left = 2*index + 1;
			int right = 2*index + 2;
			if (right < length && heap[left]< heap[right])
				larger = right;
			else
				larger = left;
			if (item >= heap[larger]) //stop when it is at proper position
				break;
			heap[index] = heap[larger];
			index = larger;				
		}
		heap[index] = item;
	}
	
	//Return the value of the first item, Time O(1) Space O(1)
	int peek() {
		if (length == 0) { //the queue is empty
			System.out.println("The priority queue is empty");
			return Integer.MIN_VALUE;
		}
		return heap[0];
	}
	
	//Print the array, Time O(n), Space O(1)
	void print() {
		for (int i = 0; i < length; i++)
			System.out.print(heap[i] + " ");
		System.out.println();
	}
	
	//Time O(1) Space O(1)
	int size() {
		return length;
	}		
	
	public static void main(String[] args) {
		//Initialize, call enqueue, print, size			
		PriorityQueueAsHeap pq = new PriorityQueueAsHeap(10);	
        pq.enqueue(30);
        pq.enqueue(12);
        pq.enqueue(20);
        pq.enqueue(15);       
        pq.enqueue(54);  
        pq.enqueue(67);
		pq.print();
        System.out.println("size: " + pq.size());
        System.out.println("peek: " + pq.peek());
        
        //Dequeue
        System.out.println("\nDequeue:");
        pq.dequeue();
        pq.print();
        System.out.println("size: " + pq.size());
        System.out.println("peek: " + pq.peek());
        
        //Enqueue again
        System.out.println("\nEnqueue:");
        pq.enqueue(68);
        pq.enqueue(98);
        pq.print();
        System.out.println("size: " + pq.size());
        System.out.println("peek: " + pq.peek());		
	}
}
