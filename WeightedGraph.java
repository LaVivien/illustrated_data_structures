import java.util.*;

//The generic type T can be a node class or other types as Integer, String
class Edge<T> { 
	T neighbor; //a connected node
	int weight; //weight
	
	//Constructor, Time O(1) Space O(1)
	Edge(T v, int w) {
		this.neighbor = v; 
		this.weight = w;
	}
	
	//Time O(1) Space O(1)
	@Override
	public String toString() {
		return "(" + neighbor + "," + weight + ")";
	}
}

public class WeightedGraph<T> {
	Map<T, LinkedList<Edge<T>>> graph = new HashMap<>() ;
	boolean directed;

	//Constructor, Time O(1) Space O(1)
	WeightedGraph() {
		directed = false; //default, Undirected graph
	}
	
	//Constructor, Time O(1) Space O(1)
	WeightedGraph(boolean d) {
		directed = d;
	}
	
	//Add edges and nodes, Time O(1) Space O(1)
	void addEdge(T a, T b, int w) {
		graph.putIfAbsent(a, new LinkedList<>()); //add node
		graph.putIfAbsent(b, new LinkedList<>()); //add node
		Edge<T> edgeAB = new Edge<>(b, w);
		graph.get(a).add(edgeAB); //add an edge
		if (!directed) { //undirected
			Edge<T> edgeBA = new Edge<>(a, w);
			graph.get(b).add(edgeBA);
		}			
	}
	
	//Find the edge from a to b, Time O(n) Space O(1), n is the number of a's neighbors 
	Edge<T> findEdgeByNodes(T a, T b) {
		if (!graph.containsKey(a) || !graph.containsKey(b)) //invalid input
			return null;
		LinkedList<Edge<T>> edges = graph.get(a);
		for (Edge<T> edge: edges) {
			if (edge.neighbor.equals(b)) 
				return edge;
		}
		return null;
	}
	
	//Remove the direct connection between a and b, Time O(n) Space O(1)
	boolean removeEdge(T a, T b) {
		if (!graph.containsKey(a) || !graph.containsKey(b)) //invalid input
			return false;
		LinkedList<Edge<T>> edgesOfA = graph.get(a);
		LinkedList<Edge<T>> edgesOfB = graph.get(b); 
		if (edgesOfA == null || edgesOfB == null) //not edges from the nodes
			return false;
		Edge<T> edgeAB = findEdgeByNodes(a, b);
		if (edgeAB == null) //no edge from A to B
			return false;
		edgesOfA.remove(edgeAB);
		if (!directed)  {//undirected
			Edge<T> edgeBA = findEdgeByNodes(b, a);
			if (edgeBA == null) //no edge from B to A
				return false;
			edgesOfB.remove(edgeBA);
		}
		return true;
	}
	
	//Remove a node and all its edges, Time O(V+E) Space O(1),
	//V is the number of nodes in the graph, E is the number of edges in the graph 
	boolean removeNode(T a) {	
		if (!graph.containsKey(a)) //invalid input
			return false;
		if (!directed) { //undirected
			LinkedList<Edge<T>> edgesOfA = graph.get(a);
			for (Edge<T> edge: edgesOfA) {	// all edges from neighbors to A
				Edge<T> edgeNe = findEdgeByNodes(edge.neighbor, a);
				graph.get(edge.neighbor).remove(edgeNe);
			}
		} else { //directed
			for (T key: graph.keySet()) { 
				Edge<T> edgeKey = findEdgeByNodes(key, a);
				if (edgeKey != null) // if there is edge from any other node to A
					graph.get(key).remove(edgeKey);
			}
		}
		graph.remove(a); // remove the node
		return true;
	}
		
	//Print the graph as a hashmap, Time O(V+E), Space O(1)
	void printGraph() {
		for (T key: graph.keySet()) 
			System.out.println(key + "," + graph.get(key));
	}
	
	//DFS Traversal starting from the start, Time O(V+E), Space O(V)
	void dfsTraversal(T start) {
		if (!graph.containsKey(start)) //invalid input
			return;
		HashMap<T, Boolean> visited = new HashMap<>();
		dfs(start, visited);
		System.out.println();
	}

	//DFS helper, Time O(V+E), Space O(V) 
	void dfs(T v, HashMap<T, Boolean> visited) {
		visited.put(v, true);
		System.out.print(v.toString() + " ");
		for (Edge<T> edge : graph.get(v)) {
			T u = edge.neighbor;
			if (visited.get(u) == null)
				dfs(u, visited);
		}
	}
	
	//BFS Traversal starting from the start, Time O(V+E), Space O(V)
	void bfsTraversal(T start) { 
		if (!graph.containsKey(start)) // invalid input
			return;
		Queue<T> queue = new LinkedList<>(); 
		HashMap<T, Boolean> visited = new HashMap<>(); 
		queue.add(start); 
		visited.put(start, true); 
		while (!queue.isEmpty()) { 
			T v = queue.poll(); 
			System.out.print(v.toString() + " ");        
			for (Edge<T> edge : graph.get(v))  { 
				T u = edge.neighbor;
				if (visited.get(u) == null) { 
					queue.add(u); 
					visited.put(u, true); 
				} 
			}   
		} 
		System.out.println(); 
	} 
	
	public static void main(String[] args) {
		/*
		 * directed graph
        1
        | \  
        V   V
        2-->3
            |
            V
            4  
		 */
		//Build a graph
		WeightedGraph<Integer> g1 = new WeightedGraph<>(true); 
		g1.addEdge(1, 2, 50); 
		g1.addEdge(1, 3, 350);  
		g1.addEdge(2, 3, 300);   
		g1.addEdge(3, 4, 120); 
		System.out.println("Directed graph:");
		g1.printGraph();
		System.out.print("dfs: ");
		g1.dfsTraversal(1);
		System.out.print("bfs ");
		g1.bfsTraversal(1);
		
		//Remove an edge
		int start = 2, end = 3, weight = 100;
		boolean r =  g1.removeEdge(start, end);
		System.out.println("\nremove edge " + start + " " + end + ": " + r);
		g1.printGraph();
		if (r) {
			g1.addEdge(start, end, weight); //add back
		}
		
		//Remove a node
		int node = 2;
		r = g1.removeNode(node);
		System.out.println("\nremove node " + node + ":" + r);
		g1.printGraph();
		System.out.print("dfs: ");
		g1.dfsTraversal(1);
		System.out.print("bfs: ");
		g1.bfsTraversal(1);
		
		/*
		 Test case  Undirected graph
		 1-- 3
		 | \ |
		 2   4 
		*/
		//Build a graph
		WeightedGraph<Integer> g = new WeightedGraph<>(); 
		g.addEdge(1, 2, 50); 
		g.addEdge(1, 3, 350);  
		g.addEdge(2, 3, 300);   
		g.addEdge(3, 4, 120);
		System.out.println("\nUndirected graph:");
		g.printGraph();
		System.out.print("dfs: ");
		g.dfsTraversal(4);
		System.out.print("bfs: ");
		g.bfsTraversal(4);
		
		//Remove an edge
		start = 3; end = 2; weight = 100;
		r = g.removeEdge(start, end);
		System.out.println("\nafter remove edge "+ start + " " + end + ":" +r);
		g.printGraph();
		if(r)
			g.addEdge(start, end, weight); //add back o
		
		//Remove a node
		node = 3;
		r = g.removeNode(node);
		System.out.println("after remove node " + node + ":" + r);
		g.printGraph();
		System.out.print("dfs: ");
		g.dfsTraversal(1);
		System.out.print("bfs: ");
		g.bfsTraversal(1);			
	}
}
