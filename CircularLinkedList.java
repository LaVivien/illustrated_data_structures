public class CircularLinkedList {
	
	Node head = null;
    Node last = null; 
 
    //Insert a node between the head and the last, Time O(1), Space O(1)
    void insert(int value) {
        Node newNode = new Node(value);
        if (head == null) 
            head = newNode;
        else 
            last.next = newNode;
        last = newNode;
        last.next = head;
    }
    
    //Delete the first matched node by a key, Time O(n), Space O(1), 
	//n is the number of nodes in the list
    void delete(int key) {
        Node node = search(key);
        if (node == null) {
            System.out.println("not found "+ key);
            return;
        }
        Node prev = head;
        Node curr = head.next;
        while (curr.data != key) {
            prev = curr;
            curr = curr.next;
        }
        if (head == last) //delete the last node in the list
            head = null;
        else if (curr == head) { //the deleted node is head, move head
            prev.next = curr.next;
            head = curr.next;
        } else 
            prev.next = curr.next;
    }
    
    //Search a node by a key, return the first matched node, Time O(n), Space O(1)
    Node search(int key) {
        if (head == null) {
            System.out.println("empty list");
            return null;
        }
        Node curr = head;
        do {
            if (curr.data == key)
                return curr;
            curr = curr.next;
        } while (curr != head);
        return null;
    }
    
    //Print the list, Time O(n), Space O(1)
    void print() {
        if (head == null) {
            System.out.println("empty list");
            return;
        }
        Node curr = head;
        do {
            System.out.print(curr.data + " ");
            curr = curr.next;
        } while (curr != head);
        System.out.println();
    }
  
    //Return the length of the list, Time O(n), Space O(1)
    int size() {   	
    	if (head == null) 
	        return 0;
    	int length = 0;
	    Node curr = head;
	    do {
	        length++;
	        curr = curr.next;
	    } while (curr != head);
    	return length;
    }

    public static void main(String[] args) {   
    	//Initialize, insert and print
    	CircularLinkedList cll = new CircularLinkedList();
    	cll.insert(3);
    	cll.insert(5);
    	cll.insert(7);
    	cll.insert(5);
    	cll.insert(4);
    	cll.insert(9);	
    	cll.insert(2);
		cll.print();
		System.out.println("size: " + cll.size());
		 
		//Delete and search
		int key = 5;
		Node node = cll.search(key);
		System.out.println("found: " + node);
		
		cll.delete(key);
		cll.print();	
		System.out.println("size: " + cll.size());		
    }
}
