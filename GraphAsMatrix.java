import java.util.*;

public class GraphAsMatrix {

	//Check if there is a path from start(x,y) to destination (x, y)
	//DFS uses recursion, Time O(mxn), Space O(d), d is the depth of the recursion
    static boolean dfsFindPath(int[][] matrix, int sx, int sy, int dx, int dy, int passable) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        dfs(matrix, sx, sy, visited, passable);
        if (!visited[dx][dy]) {
            System.out.println("cannot find a path.");
            return false; //cannot find a path
        }
        return true;
    }

    //DFS helper, Time O(m*n), Space O(d) 
    static void dfs(int[][] matrix,int x, int y, boolean[][] visited, int passable) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] != passable || visited[x][y]) 
            return;		//terminating condition
        visited[x][y] = true;
        dfs(matrix, x-1, y, visited, passable); // Move left
        dfs(matrix, x+1, y, visited, passable); // Move Right
        dfs(matrix, x, y-1, visited, passable); // Move up
        dfs(matrix, x, y+1, visited, passable); // Move down	
    }

    //Define a class to save information for each cell, used by BFS
    static class Cell {
        int x, y;
        int dist;  	//distance
        Cell prev;  //previous cell in the path
        
        //Constructor, Time O(1), Space O(1)
        Cell(int x, int y, int dist, Cell prev) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.prev = prev;
        }
        
        @Override
        public String toString(){
            return "(" + x + "," + y + ")";
        }
    }

    //BFS to find shortest path from the start to the destination, Time O(mxn), Space O(mxn)
    //initialize cells and call bfs()
    static void bfsShortestPath(int[][] matrix, int sx, int sy, int dx, int dy , int passable) {
        int m = matrix.length;
        int n = matrix[0].length;	 
        if (sx < 0 || sx >= m || sy < 0 ||sy >= n || dx < 0 || dx >= m || dy < 0 || dy >= n ||
                matrix[sx][sy] != passable || matrix[dx][dy] != passable) {
            System.out.println("invalid input."); 
            return;  //stop when the start or end cells are invalid
        }	
        Cell[][] cells = new Cell[m][n];  //initialize cells   
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == passable) 
                    cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null);
            }
        }
        bfs(cells, sx, sy, dx, dy); //call bfs()
    }

    //BFS of a graph, Time O(m*n), Space O(p), p is the number of cells that have been checked
    static void bfs(Cell[][] cells, int sx, int sy, int dx, int dy) {
        LinkedList<Cell> queue = new LinkedList<>();       
        Cell start = cells[sx][sy];
        start.dist = 0;
        queue.add(start);
        Cell dest = null;
        Cell curr;
        while ((curr = queue.poll()) != null) { 
            if (curr.x == dx && curr.y == dy) { //found the destination
                dest = curr;
                break;
            }          
            visit(cells, queue, curr.x - 1, curr.y, curr);  // move left          
            visit(cells, queue, curr.x + 1, curr.y, curr);  // move right       
            visit(cells, queue, curr.x, curr.y - 1, curr);  // move up  
            visit(cells, queue, curr.x, curr.y + 1, curr);  // move down
        }
        if (dest == null) { 
            System.out.println("there is no path.");
            return;
        } else {
            LinkedList<Cell> path = new LinkedList<>(); //compose the path if it exists
            curr = dest;
            do {       	
                path.addFirst(curr);
            } while ((curr = curr.prev) != null);
            System.out.println(path);
        }
    }

    //Called by bfs(), update the cell's info,  Time O(1), Space O(1)
    static void visit(Cell[][] cells, LinkedList<Cell> queue, int x, int y, Cell prev) { 	
        if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length || cells[x][y] == null) 
            return; //return when the cell is out of boundary or not passable    
        int dist = prev.dist + 1; 
        Cell cell = cells[x][y];
        if (dist < cell.dist) { //update distance, and the previous node
            cell.dist = dist;
            cell.prev = prev;
            queue.add(cell);
        }
    }
    
	//Print a matrix, Time O(m*n), Space O(1)
	static void printMatrix(int[][] matrix) {
	    for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j ++) 
	            System.out.print( matrix[i][j] + " ");
	        System.out.println();
	    }
	}
	
	public static void main(String[] args) {
		//2D matrix with 1 as passable and 0 as a blocker.
		int[][] matrix = {{1,1,1,1,0},
		          		  {1,1,0,0,1},
				          {1,0,1,0,0},
				          {1,1,1,1,1}};
		GraphAsMatrix.printMatrix(matrix);
		
		//Call DFS and BFS 
		int sx = 0, sy = 3, dx = 2, dy =2; //there is a path
		System.out.println("Find path from (" + sx + "," + sy + ") to (" + dx + "," + dy + "): ");
		System.out.println("DFS: " + GraphAsMatrix.dfsFindPath(matrix, sx, sy, dx, dy, 1));	
		GraphAsMatrix.bfsShortestPath(matrix, sx, sy, dx, dy, 1);
	}
}
