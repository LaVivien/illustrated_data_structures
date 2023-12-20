public class DoublyLinkedList {
	
	//Define a node class used in a doubly linked list
    class DNode {	 	
        int data;
        DNode next = null;
        DNode previous = null;
    
        //Constructor, Time O(1), Space O(1)
        DNode(int value) { 
            data = value;
        }
        
        //Override, Time O(1), Space O(1)
        public String toString() {
            return String.valueOf(data);
        }
    }
	
    DNode head = null;
	DNode tail = null;  
    
    //Insert a node at the head, Time O(1), Space O(1)    
    void insertFirst(int value) {
        DNode newNode = new DNode(value);
        newNode.next = head;
        if (head != null)
            head.previous = newNode;
        head = newNode;
        if (tail == null)
            tail = newNode;
    }

    //Add a node at the tail, Time O(1), Space O(1)
    void insertLast(int value) {
        DNode newNode = new DNode(value);
        newNode.previous = tail;
        if (tail != null)
            tail.next = newNode;
        tail = newNode;
        if (head == null)
            head = newNode;
    }

    //Delete all nodes by a key, call deleteNode(), 
	//Time O(n), Space O(1), n is the number of nodes in the list
	DNode delete(int key) { 
	    if (head == null) {
	    	System.out.println("empty list");
	        return null; 
	    }
	    DNode curr = head; 
	    DNode next = null; 
	    while (curr != null)  { 
	        if (curr.data == key) { 
	            next = curr.next; 
	            deleteNode(curr); 
	            curr = next;              
	        } else
	            curr = curr.next;  
	    }  
	    return head; 
	} 
    
    //Called by delete(), remove a node, Time O(1), Space O(1)
    void deleteNode(DNode node) {
    	if (node == null)
    		return;
        if (node.previous != null) 
            node.previous.next = node.next;
        else 
            head = node.next;    //the deleted node is head, move head           
        if (node.next != null)
            node.next.previous = node.previous;
        else
            tail = node.previous; //the deleted node is tail, move tail   
    }
	
	//Search a node by a key, return the first matched node, Time O(n), Space O(1) 
	DNode search(int key)  { 
        DNode curr = head;   
        while (curr != null)  { 
            if (curr.data == key) 
                return curr; 
            curr = curr.next; 
        } 
        return null;    
    } 
	
	//Print the list in both directions, Time O(n), Space O(1) 
    void print() {
        if (head == null) 
            return;
        printForward();
        printBackward();
    }
    
    //Print from the head to the tail, Time O(n), Space O(1)     
    void printForward() {
        DNode curr = head;          
        while (curr != null) {
            System.out.print(curr.data + " ");  
            curr = curr.next;    
        }
        System.out.println();
    }

    //Print from the tail to the head, Time O(n), Space O(1)      
    void printBackward() {
        DNode curr = tail;          
        while (curr != null) {
            System.out.print(curr.data + " ");    
            curr = curr.previous; 
        }
        System.out.println();
    }
            
    //Return the length of the list, Time O(n), Space O(1)
    int size() {
    	int length = 0;
        DNode curr = head;          
        while(curr != null) {
            length++;
            curr = curr.next;    
        }
        return length;
    }
          
    public static void main(String[] args) {
    	//Initialize, insert and print 
    	DoublyLinkedList dll = new DoublyLinkedList();
    	dll.insertFirst(3);
    	dll.insertLast(5);
    	dll.insertLast(2);
    	dll.insertLast(1);
    	dll.insertLast(8);
        dll.print();            
        System.out.println("The length: " + dll.size() + "\n");      
        
        //Search
        int key = 2;
        DNode node = dll.search(key);
        if (node != null)
        	System.out.println("Search the key " + key + ", node data: " + node.data);
        else
        	System.out.println("not found");
                   
        //Delete
        dll.delete(key);
        System.out.println("Delete " + key);
        dll.print();
        System.out.println("The length: " + dll.size() + "\n");          
    }
}
