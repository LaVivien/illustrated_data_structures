import java.util.LinkedList;

//Define an entry class used in a hash table
class Entry {
	int key;
	String value;
	
	//Constructor, Time O(1), Space O(1)
	Entry(int key, String value) {
		this.key = key;
		this.value = value;
	}
	
	//Override, Time O(1), Space O(1)
	public String toString() {
		return "(" + key + ", " + value.toString() + ")";
	}	
}

public class HashTable {
	int maxSize;
	LinkedList<Entry>[] buckets; 
	
	//Constructor, Time O(1), Space O(m), m is the max size of the buckets
	@SuppressWarnings("unchecked")
	HashTable(int capacity) {
		maxSize = capacity;
		buckets = new LinkedList[maxSize];		
	}
	
	//Calculate the hashcode by the key, Time O(1), Space O(1)
	int hashFunc(int key) {
		if (key < Integer.MIN_VALUE || key > Integer.MAX_VALUE) {
			System.out.println("invalid key");
			return -1;
		}
		return key % maxSize;
	}
	
	//Add an entry in the hash table, Time O(1), Space O(1) 
	void put(int key, String value) {
		int x = hashFunc(key);
		if (x < 0)
			return;
		if (buckets[x] == null) 
			buckets[x] = new LinkedList<Entry>();
		LinkedList<Entry> list = buckets[x];	
		Entry entry = new Entry(key, value);
		list.add(entry);
	}
	
	//Return the value of the first matched entry by a key, Time O(n), Space O(1), 
	//n is the length of the list in that bucket
	String get(int key) {
		int x = hashFunc(key);
		if (x < 0)
			return null;
		if (buckets[x] == null) //empty list
			return null;
		LinkedList<Entry> list = buckets[x];
		for (Entry entry : list) {
			if (entry.key == key) 
				return entry.value;
		}		
		return null;		
	}
	
	//Delete all entries by the same key, Time O(n), Space O(n)
	void delete(int key) {
		int x = hashFunc(key);
		if (x < 0)
			return;
		if (buckets[x] == null)
			return;
		LinkedList<Entry> list = buckets[x];
		LinkedList<Entry> newList = new LinkedList<>(); //copy to a new list
		for (Entry entry : list) {
			if (entry.key != key) 
				newList.add(entry);
		}
		buckets[x] = (newList.size() == 0) ? null: new LinkedList<>(newList); 
	}
    
    //Print the whole hash table, Time O(m*n), Space O(1), 
    //m is the size of the buckets, n is the max list size of the buckets 
	void print() {
		for (int i = 0; i < maxSize; i++) {    		
			LinkedList<Entry> list = buckets[i];
			if (list != null) {
				for (Entry entry : list)
					System.out.print(entry + " ");
				System.out.println();
			}
		}
	}

    //Return the count of the nonempty buckets, Time O(m), space O(1), m is the buckets max size
    int size() {
    	int length = 0;
        for (int i = 0; i < this.maxSize; i++) {    		
        	LinkedList<Entry> list = this.buckets[i];
    		if (list != null)
    			length++;
    	}
    	return length;
	}
    
	public static void main(String[] args) {
		//Initialize, call put, size and print		
		int size = 5;
		HashTable table = new HashTable(size);
		table.put(0, "banana");
		table.put(1, "red apple");
		table.put(11, "green apple");
		table.put(3, "orange");
		table.put(33, "blood orange");
		System.out.println("The HashTable: ");
		table.print();
		System.out.println("The size: " + table.size());
		
		//Get
		int key = 3;
		System.out.println("Get value of " + key + ": " + table.get(key));
		int k1 = 8;
		System.out.println("Get value of " + k1 + ": " + table.get(k1));	
		
		//Update
		table.put(4, "blue berry");	
		System.out.println("\nAfter update:");
		table.print();
		System.out.println("The size: " + table.size());
		
		//Delete
		key = 1;
		table.delete(key);
		System.out.println("\nAfter delete key " + key);
		table.print();
		System.out.println("The size after delete: " + table.size());	
		int k2 = 3;
		System.out.println("Get value of " + k2 + ": " + table.get(k2));
	}
}


