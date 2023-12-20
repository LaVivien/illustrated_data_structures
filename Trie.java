import java.util.*;

public class Trie {
	
	//Define a trie node used in a trie
	class TrieNode {
		char data;
		Map<Character, TrieNode> children = new HashMap<>();     
		boolean isEnd = false;
		
		//Constructor, Time O(1), Space O(1)
		TrieNode(char c) {
			this.data = c;
		}
	}
	
	TrieNode root = new TrieNode(' '); //root's data will be ignored
		
	//Add a string to the trie, Time O(s), Space O(s), s is the string length
	void insert(String str) {
		if (search(str))  // return if the string is already in the trie
            return;
		TrieNode curr = root;
		for (char ch : str.toCharArray()) {
		    if (!curr.children.containsKey(ch)) 
		        curr.children.put(ch, new TrieNode(ch));		         			    
		    curr = curr.children.get(ch);	
		}
		curr.isEnd = true;	
	}
		
	//Search if a string is in the trie, Time O(s), Space O(1)
	boolean search(String str) {
		TrieNode curr = root;
		for (char ch : str.toCharArray()) {
			if (!curr.children.containsKey(ch)) 
				return false;		        
			curr = curr.children.get(ch);
		}
		return curr.isEnd;
	}	
	    
	//Remove a string from the trie, Time O(s), Space O(1)
	void delete(String str) {	 
		if (!search(str))  //return if the string doesn't exist in the trie
			return;
		TrieNode curr = root;
		for (char ch : str.toCharArray()) {        	
			if (!curr.children.containsKey(ch)) 
				return;	        
			curr = curr.children.get(ch);	            
		}
		curr.isEnd = false; 
	}
	    
	//Print all strings in the trie, call dfs() recursive method
	//Time O(n), Space O(n), n is the number of nodes in the trie, 
	void printTrie() {
		dfs(root,  ""); //start from the root
	}

	//Called by printTrie(), concatenate characters in a branch and print a string at a time
	//Time O(n), Space O(n)
	void dfs(TrieNode node, String prefix) {
		if (node.isEnd) { //terminating condition
			String str = prefix + node.data;
			System.out.println(str.substring(1)); //skip the first character at root
		}
		for (Character ch : node.children.keySet()) 
			dfs(node.children.get(ch), prefix + node.data);
	}
	
	public static void main(String[] args) { 
		//Build a trie, cal insert and print
        Trie trie = new Trie();            
        trie.insert("ana"); 
		trie.insert("anna"); 
		trie.insert("anne");          
        trie.insert("apple ipone");
        trie.insert("amazon prime");
        trie.insert("amazing");      
        trie.printTrie();
        
		//Search a string
		String key = "anne";
		System.out.println("Search '" + key + "': " + trie.search(key));  
			
		//Delete a string
		trie.delete(key);
		System.out.println("\nAfter delete: " + key);
		trie.printTrie();	
		System.out.println("Search '" + key + "': " + trie.search(key)); 
	}	 
}
