import java.util.*;

//Define a tree node used in a binary search tree
class TreeNode {		
	int data;
	TreeNode left = null;        
	TreeNode right = null;
	
	//Constructor, Time O(1), Space O(1)
	TreeNode(int value) {
		data = value;
	}

    //Override, Time O(1), Space O(1)
    public String toString() {
    	return String.valueOf(data);
    }
}  
	
public class BinarySearchTree {	

	TreeNode root = null;
	
	//Insert a node in the BST, Time O(h) Space O(1), 
	//h is the height of the tree
	void insert(int value) {
		TreeNode newNode = new TreeNode(value);
		if (root == null) {  //the first node               
			root = newNode;
			return;
		}		
		TreeNode curr = root;       
		TreeNode parent = null;
		while (true) {
			parent = curr;
			if (value < curr.data) {
				curr = curr.left;
				if (curr == null) {                 
					parent.left = newNode;
					return;
				}
			} else {
				curr = curr.right;
				if (curr == null) {                 
					parent.right = newNode;
					return;
				}
			}  
		}	
	} 

	//Delete the first matched node, call getSuccessor(), Time O(h), Space O(1)
	TreeNode delete(int key) {
		if (root == null)
			return null;
		if (root.data == key) { 
			root = getSuccessor(root);       	
			return root;
		}        
		TreeNode curr = root;     
		while (true) { 
			if (key > curr.data) {
				if (curr.right == null || curr.right.data == key) {
					curr.right = getSuccessor(curr.right);
					break;
				}
				curr = curr.right;
			} else {
				if (curr.left == null || curr.left.data == key) {
					curr.left = getSuccessor(curr.left);
					break;
				}
				curr = curr.left;
			}
		}
		return root;
	}
    
	//Called by delete(), return the next successor, Time O(h), Space O(1)
	//h is the height of the node
	TreeNode getSuccessor(TreeNode node) {
		if (node == null) 
			return null;
		if (node.right == null) 
			return node.left;
		TreeNode curr = node.right;
		while (curr.left != null) //find the leftmost node
			curr = curr.left; 
		curr.left = node.left;
		return node.right;
	}
    
	//Search a binary search tree, return the first matched node, Time O(h), Space O(1)
	TreeNode search(int key) {                           
		TreeNode curr = root;               
		while (curr != null) {
			if (curr.data == key)
				return curr;
			else if (key < curr.data)
				curr = curr.left;
			else                            
				curr = curr.right;              
		}
		return curr;                    
	} 
	
	//Inorder traversal using recusion, Time O(n), Space O(n)
	void inorder(TreeNode node) {
		if (node == null) 
			return;
		inorder(node.left);
		System.out.println(node.data);
		inorder(node.right);	
	}
	
	//Preorder traversal using recusion, Time O(n), Space O(n) 
	void preorder(TreeNode node) {
		if (node == null) 
			return;
		System.out.println(node.data);
		preorder(node.left);
		preorder(node.right);	
	}
	
	//Postorder traversal using recusion, Time O(n), Space O(n) 
	void postorder(TreeNode node) {
		if (node == null) 
			return;
		postorder(node.left);
		postorder(node.right);
		System.out.println(node.data);	
	}
	
	//Level order traversal, BFS, Time O(n), Space O(n)
	void levelOrder() {
		if (root == null)
			return;	
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);		
		while (!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			System.out.println(curr.data);
			if (curr != null) {
				if (curr.left != null) 
					queue.add(curr.left);
				if (curr.right != null) 	
					queue.add(curr.right);	
			} 
		}
	}
	
	public static void main(String[] args) {		
		//Build a binary search tree 
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(50);
	    tree.insert(12);
	    tree.insert(25);
	    tree.insert(75);
	    tree.insert(37);
	    tree.insert(22);
	    tree.insert(43);
	    System.out.println("The root: " + tree.root.data); 
	    
	    //traversal
	    System.out.println("inorder:");
	    tree.inorder(tree.root);
	    System.out.println("preorder:");
	    tree.preorder(tree.root);
	    System.out.println("postorder:");
	    tree.postorder(tree.root);
	    System.out.println("level order:");
	    tree.levelOrder();
	    
	    //Search
	    int key = 22;
	    TreeNode node = tree.search(key);
	    System.out.println("BFS Search " + key + ", node data: " + node);
	    
	    //Delete 
	    key = 25;	  
	    tree.root  = tree.delete(key);    
	    System.out.println("\nAfter delete " + key);
	    System.out.println("The root: " + tree.root.data); 
			
	    node = tree.search(key);
	    System.out.println("DFS search " + key + ", node data: " + node);
	    System.out.println("inorder:");
	    tree.inorder(tree.root);
	    System.out.println("level order:");
	    tree.levelOrder();
	}
}
