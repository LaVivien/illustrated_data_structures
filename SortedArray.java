public class SortedArray {
    
    int[] array;
    int maxSize;
    int length = 0;
    
    //Constructor, Time O(1), Space O(1)
    SortedArray(int capacity) {
    	maxSize = capacity;
    	array = new int[maxSize];
    }  
    
    //Insert an element at the proper position, Time O(n), Space O(1)
	//n is the array length
    void insert(int value) {
        if (length == maxSize) {
            System.out.println("The array is full.");
            return;
        }
        int i = length-1;
        while (i >= 0 && array[i] > value) {
            array[i+1] = array[i];
            i--;
        }
        array[i+1] = value;
        length++;    	
    } 
        
    //Delete the first matched element by a key, Time O(n), Space O(1)
    boolean delete(int key) {
        int i = binarySearch(key);
        if (i == length || i < 0) //not found
            return false;
        else {
            for (int j = i; j < length-1; j++) 
                array[j] = array[j+1];
            length--;         
            return true;
        }
    }

    //Binary search, return the index of the first match, Time O(logn), Space O(1)
	int binarySearch(int key) {
		int low = 0;
		int high = length - 1;		
		while (low <= high) {
			int mid = low + (high-low)/2;
			if (array[mid] == key)
				return mid;
			else if (key < array[mid])
				high = mid - 1;				
			else 
				low = mid + 1;		
		}
		return -1;
	}
		
	//Print the array, Time O(n), Space O(1)
    void print( ) {
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
		SortedArray arr = new SortedArray(6);
        arr.insert(0);
        arr.insert(-1);
        arr.insert(19);
        arr.insert(2);
        arr.insert(10);
        arr.print();      
        System.out.println("The length: " + arr.size());    
        
        //Search
        int key = 20;
        System.out.println("Found " + key + " at position: " + arr.binarySearch(key)); 
        key = 2;
        System.out.println("Found " + key + " at position: " + arr.binarySearch(key)); 
        
        //Delete
        arr.delete (key);
        arr.print();              
        System.out.println("The length: " + arr.size());
	}
}

