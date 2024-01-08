public class MaxHeap {

	int[] heap;
	int length;
	int maxSize;

	//Constructor, Time O(1) Space O(1)
	MaxHeap(int capacity) {
		maxSize = capacity;
		heap = new int[maxSize] ; 
		length = 0; 
	}

	//Insert a new value in the heap, Call bubbleUp(), Time O(logn), Space O(1), 
	//n is the number of items in the heap
	void insert(int value) {		
		if (length == maxSize) { //the heap is full
			System.out.println("The heap is full.");
			return;
		}
		heap[length] = value;
		bubbleUp(length); //start from the last position
		length++;
	}

	//Called by insert(), move the new item up in the heap, Time O(logn), Space O(1)
	void bubbleUp(int index) {
		int parentIndex = (index-1)/2;
		int item = heap[index];
		while (index > 0 && item > heap[parentIndex]) {
			heap[index] = heap[parentIndex]; 
			index = parentIndex; 
			parentIndex = (parentIndex-1)/2;
		}
		heap[index] = item; 
	}

	//Remove the item at root and move the next biggest item there,
	//call bubbleDown(), Time O(logn), Space O(1)
	int remove() {	
		if (length == 0) { //the heap is empty
			System.out.println("The heap is empty");
			return Integer.MIN_VALUE;
		}
		int max = heap[0];
		heap[0] = heap[length-1]; //put the last item at root
		length--;		
		bubbleDown(0); //move down the item
		return max;
	}
	
	//Called by remove(), move the largest item to root, Time O(logn), space O(1)
	void bubbleDown(int index) {
		int item = heap[index];
		int larger;
		while (index < length/2) {
			int leftIndex = 2*index + 1;
			int rightIndex = 2*index + 2;
			if (rightIndex < length && heap[leftIndex] < heap[rightIndex]) 
				larger = rightIndex;
			else
				larger = leftIndex;
			if (item >= heap[larger]) //stop when it is at proper position
				break;
			heap[index] = heap[larger];
			index = larger;				
		}
		heap[index] = item;
	}
    
    //Search level by level, return the first match, Time O(n), Space O(1)
    boolean search(int key) {
		for (int i = 0; i < length; i++) {
			if (key == heap[i]) 
				return true;
		}	
		return false;
    }
    
    //Print level by level, Time O(n), Space O(1)
	void print() {		
		for (int i = 0; i < length; i++) 
			System.out.print(heap[i] + " "); 	
		System.out.println();
	}    

	public static void main(String[] args) {	
		//Initialize, call insrt, print, size
		MaxHeap maxHeap = new MaxHeap(10);  //capacity 10
		maxHeap.insert(5);           
		maxHeap.insert(4);
		maxHeap.insert(8);
		maxHeap.insert(2);
		maxHeap.insert(7);
		maxHeap.insert(9);
		maxHeap.insert(2);
		maxHeap.insert(10);
		maxHeap.insert(6);
		maxHeap.print();
        System.out.println("size: " + maxHeap.length);

        //Search
        int key = 3;
        System.out.println("has " + key +": " + maxHeap.search(key));
        key = 7;
        System.out.println("has " + key +": " + maxHeap.search(key));
        
        //Remove the maximum value in heap
        System.out.println("\nRemove the max val: " + maxHeap.remove());      
        System.out.println("size: " + maxHeap.length);
        maxHeap.print();
	}
}
