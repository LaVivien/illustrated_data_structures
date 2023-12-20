public class Array {
	
    int[] array;
    int maxSize;
    int length = 0;
    
    //Constructor, Time O(1), Space O(1)
    Array(int capacity) {
    	maxSize = capacity;
    	array = new int[maxSize];
    }
    
    //Add one element at the back, Time O(1), Space O(1)
	void add(int value) {
		if (length == maxSize) {  //the array is full
			System.out.println("The array is full.");
			return;
		}
		array[length] = value;
		length++;
	}

	//Get the element by the index, Time O(1), Space O(1)
	int get(int index) {
		if (index >= maxSize || index < 0) { //beyond the range
			System.out.println("invalid index.");
			return Integer.MIN_VALUE; 
		}
		return array[index];
	}
   
	//Search by a key, return the index of the first match, Time O(n), Space O(1)
	//n is the array length
	int search(int key) {
		for (int i = 0; i < length; i++) {
			if (array[i] == key) 
				return i;                   
		}        
		return -1; 
	}

	//Delete the first matched element by a key, Time O(n), Space O(1) 
	void delete(int key) {
		int index = search(key); 
		if (index < 0) { //not found
			System.out.println("Key not found");
			return;
		}
		for (int j = index; j < length-1; j++)        
			array[j] = array[j+1];
		length--;
	}
    
    //Print all elements in the array, Time O(n), Space O(1)
	void print() {
		for (int i = 0; i < length; i++) 
			System.out.print(array[i] + " ");
		System.out.println();
	} 
	
	//Return the length of the array, Time O(1), Space O(1)
    int size() {
    	return length;
    }
    
	public static void main(String[] args) {
		//Initialize, call insert, print and size
		Array arr = new Array(10);
		arr.add(2);
		arr.add(5);
		arr.add(6);
		arr.add(8);
		arr.add(3); 
		arr.add(9); 
		arr.print();
		System.out.println("The length: " + arr.size());
		
		//Search by key
		int key = 10; //invalid
		int pos = arr.search(key);
		System.out.println("The index of " + key + ": " + pos);
		key = 6; //valid
		pos = arr.search(key);
		System.out.println("The index of " + key + ": " + pos);
		
		//Delete by key
		arr.delete(key);
		arr.print();  
		System.out.println("The new length: " + arr.size());
	}  
}
