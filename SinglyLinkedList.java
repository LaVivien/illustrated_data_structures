//Define a node class used in a singly linked list
class Node {  
	
	int data;
	Node next = null;
	 
	//Constructor, Time O(1), Space O(1)
	Node(int value) { 
		this.data = value;
    }	
	
	//Override, Time O(1), Space O(1)
	public String toString() {
		return String.valueOf(data);
	}
}

public class SinglyLinkedList {
	
    Node head = null;

    //Insert a node at the head, Time O(1), Space O(1)
    void insertFirst(int value) {                          
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }
    
    //Add a node at the tail, Time O(n), Space O(1), 
	//n is the number of nodes in the list
    void insertLast(int value) {
        Node newNode = new Node(value);
        if (head == null) { //insert the first node
            head = newNode;
            return; 
        }        
        Node curr = head;       
        while (curr.next != null) 
            curr = curr.next;
        curr.next = newNode;       
    }
    
    //Delete the first matched node by a key, Time O(n), Space O(1)
    void delete(int key) {
        if (head == null)
            return;
        else if (head.data == key) {
            head = head.next;
            return;
        }
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (curr.data == key) {
                prev.next = curr.next;
                return;
            }
            prev = curr;
            curr = curr.next;            
        }		
    }

    //Search a node by a key, return the first matched node, Time O(n), Space O(1)
    Node search(int key) {
		Node curr = head;
	    while (curr != null) {
			if (curr.data == key)
				return curr;
			curr = curr.next;
		}
		return null;
	}
    
    //Print the list, Time O(n), Space O(1)
    void print() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    } 
     
    //Return the length of the list, Time O(n), Space O(1)
    int size() {
    	int length = 0;
    	Node curr = head;
    	while (curr != null) {
    		length++;
    	    curr = curr.next;
    	}
    	return length ;
    }  
    
    public static void main(String[] args) {      
    	//Initialize, insert, print and size
    	SinglyLinkedList ll = new SinglyLinkedList();
        ll.insertFirst(3);
        ll.insertLast(4);
        ll.insertLast(2);
        ll.insertLast(1);
        ll.insertLast(5);
        System.out.print("The linked list: ");
        ll.print();
        System.out.println("The length: " + ll.size());
        
        //Delete by key
        int key = 2;
        ll.delete(key);
        System.out.print("After delete " + key + ": ");
        ll.print();
        System.out.println("The length: " + ll.size());

        //Search
        key = 3;
        Node node = ll.search(key);
        System.out.println("find " + key + ": " + node);             
    }
}



