public class StackAsArray {

	int maxSize;       
	int[] stack;
	int topIndex;  
	
	//Constructor, Time O(1), Space O(1)
	public StackAsArray(int capacity) {
		maxSize = capacity;             
		stack = new int[maxSize]; 
		topIndex = -1;                
    }
	
	//Push an element at the top, Time O(1), Space O(1)
	void push(int value) {
		if (topIndex == maxSize-1) {//the stack is full
			System.out.println("The stack is full.");
			return;
		}
		stack[++topIndex] = value;
	}
	
	//Remove the element from the top, Time O(1), Space O(1)
	int pop() {
		if (topIndex == -1) {//the stack is empty
			System.out.println("The stack is empty.");
			return Integer.MIN_VALUE;
		}
		return stack[topIndex--]; 
	}
	
	//Return the value at the top, Time O(1), Space O(1)
	int top() {
		if (topIndex == -1) { //the stack is empty
			System.out.println("The stack is empty.");
			return Integer.MIN_VALUE;
		}
		return stack[topIndex];
	}
	
	//Print all elements starting from the top, Time O(n), Space O(1)
	void print() {
		for (int i = topIndex; i >= 0; i--)
			System.out.print(stack[i] + " ");
		System.out.println();
	}
	
	//Return the length of the stack, Time O(1), Space O(1)
	int size() {
		return topIndex + 1;
	}
	
	public static void main(String[] args) {
		//Initialize, push, size and top
		StackAsArray stack = new StackAsArray(5);    
		stack.push(8);
		stack.push(3);
		stack.push(6);
		stack.print();		
		System.out.println("The size: " + stack.size()); 
		System.out.println("The top: " + stack.top()); 
		
		//Pop 	
		stack.pop();
		System.out.println("After pop:");
		stack.print();
		System.out.println("The size: " + stack.size());
		System.out.println("The top: " + stack.top()); 
	}  
}
