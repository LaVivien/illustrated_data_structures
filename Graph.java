import java.util.*;

//No node class is defined, the generic type T can be a node class or any other data types 
public class Graph<T> {
	Map<T, LinkedList<T>> adj = new HashMap<>();
	boolean directed; 
	
	//Constructor, Time O(1) Space O(1)
	Graph() {
		directed = false; //the default value is false
	}
	
	//Constructor, Time O(1) Space O(1)
	Graph(boolean d) {
		directed = d; //can be true or false
	}

	//Add edges and nodes, Time O(1) Space O(1)
	void addEdge(T a, T b) {
		adj.putIfAbsent(a, new LinkedList<>()); //add node
		adj.putIfAbsent(b, new LinkedList<>()); //add node
		adj.get(a).add(b); //add an edge a->b
		if (!directed)  //undirected
			adj.get(b).add(a);		
	}
	
	//Remove the direct connection between a and b, Time O(1) Space O(1)
    boolean removeEdge(T a, T b) {
        if (!adj.containsKey(a) || !adj.containsKey(b)) 
            return false; //stop when input is invalid
        LinkedList<T> neighborsOfA = adj.get(a);
        LinkedList<T> neighborsOfB = adj.get(b); 
        if (neighborsOfA == null || neighborsOfB == null)
            return false; //stop when no neighbors found
        boolean r1 = neighborsOfA.remove(b);
        boolean r2;
        if (!directed) { //undirected
            r2 = neighborsOfB.remove(a);
            return r1 && r2;
        }
        return r1;
    }
	
	//Remove a node and all its edges, 
	//Time O(V) Space O(1), V is the number of nodes in a graph 
    boolean removeNode(T a) {	
        if (!adj.containsKey(a)) //invalid input
            return false;
        if (!directed) { //undirected
            LinkedList<T> neighbors = adj.get(a);
            for (T node: neighbors) 	
                adj.get(node).remove(a);
        } else { //directed
            for (T key: adj.keySet()) 
                adj.get(key).remove(a);
        }
        adj.remove(a);
        return true;
    }
			
	//Print the graph as a hashmap, Time O(V+E), Space O(1)
	void printGraph() {
		for (T key: adj.keySet()) 
			System.out.println(key + "," + adj.get(key));
	}
	
	//DFS Traversal starting from the start, call dfs(), Time O(V+E), Space O(V)
    void dfsTraversal(T start) {
        if (!adj.containsKey(start))
            return;
        HashMap<T, Boolean> visited = new HashMap<>();
        dfs(start, visited);
        System.out.println();
    }

    //DFS recursive method, Time O(V+E), Space O(V) 
    void dfs(T v, HashMap<T, Boolean> visited) {
        visited.put(v, true);
        System.out.print(v.toString() + " ");
        for (T neighbor : adj.get(v)) {
            if (visited.get(neighbor) == null)
                dfs(neighbor, visited);
        }
    }
	
	//BFS Traversal starting from the start, Time O(V+E), Space O(V)
    void bfsTraversal(T start) { 
        if (!adj.containsKey(start))
            return;
        Queue<T> queue = new LinkedList<>(); 
        queue.add(start);
        HashMap<T, Boolean> visited = new HashMap<>(); 	     
        visited.put(start, true); 
        while (!queue.isEmpty()) { 
            T v = queue.poll(); 
            System.out.print(v.toString() + " ");        
            for (T neighbor : adj.get(v))  { 
                if (visited.get(neighbor) == null) { 
                    queue.add(neighbor); 
                    visited.put(neighbor, true); 
                } 
            }   
        } 
        System.out.println(); 
    } 
	
	public static void main(String args[]) { 
        /*
        Test case Directed graph
        1
        | \  
        V   V
        2-->3
            |
            V
            4        
        */
		//Building a graph
        Graph<Integer> g1 = new Graph<>(true);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
        System.out.println("Directed graph:"); 
		g1.printGraph();  
        System.out.print("DFS: ");
        g1.dfsTraversal(1); 
        System.out.print("BFS: ");
        g1.bfsTraversal(1);
        
		//Remove an edge
        int start = 1, end = 2;
        boolean r = g1.removeEdge(start, end);
        System.out.println("\nAfter remove edge " + start + "," + end + ":" + r);      
        g1.printGraph();
        if (r)
        	g1.addEdge(start, end); //add back
        
        //Remove a node
        int node = 1;
        r = g1.removeNode(node);
        System.out.println("\nAfter remove node " + node + ": "+ r);       
        g1.printGraph();
        
        System.out.print("DFS: ");
        g1.dfsTraversal(2); 
        System.out.print("BFS: ");
        g1.bfsTraversal(2);
        
		/*
		 Test case  Undirected graph
		 1-- 3
		 | / |
		 2   4 
		*/
		//Building a graph
		Graph<Integer> g = new Graph<>();
		g.addEdge(1, 3);
		g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        System.out.println("\nUndirected graph: ");
        g.printGraph();  
        start = 3;
        System.out.print("DFS: ");
        g.dfsTraversal(start); 
        System.out.print("BFS: ");
        g.bfsTraversal(start);
        
        //Remove an edge
        start = 2; end =3;
        r = g.removeEdge(start, end);
        System.out.println("\nAfter remove edge " + start + "," + end + ":"+r);    
        g.printGraph();
        if (r)
        	g.addEdge(start, end); //add back
        
        //Remove a node
        node = 4;
        r = g.removeNode(node);
        System.out.println("\nAfter remove node " + node + ":" + r);        
        g.printGraph();
        System.out.println();
        
        System.out.print("DFS: ");
        g.dfsTraversal(1); 
        System.out.print("BFS: ");
        g.bfsTraversal(1);
    } 
}
