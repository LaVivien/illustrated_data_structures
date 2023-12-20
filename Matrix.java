public class Matrix {

	//Set a character in the cell, Time O(1), Space O(1)
	static void set(char[][] matrix, int r, int c, char value) {
		if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length)
			return; //invalid input
		matrix[r][c] = value;
	}

	//Get the character in the cell, Time O(1), Space O(1)
	static char get(char[][] matrix, int r, int c) {
		if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length)
			return  Character.MAX_VALUE;  //invalid input
		return matrix[r][c] ;
	}
	
	//Search in an unsorted matrix, return the indices of the first match, Time O(m*n), Space O(1)
	static int[] searchMatrix(int[][] matrix, int key) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == key) 
					return new int[]{i,j};
			}
		}
		return new int[]{-1,-1};
	}
	
	//Search in a sorted matrix using two pointers, 
	//return the indices of the first match, Time O(m+n), Space O(1)
	static int[] searchSortedMatrix(int[][] matrix, int key) {
		int row = 0;
		int col = matrix[0].length - 1; 
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] == key) 
				return new int[]{row,col};
			else if (matrix[row][col] < key) 
				row++; 
			else 
				col--; 
		}
		return new int[]{-1,-1};
	}
	
	//Print a matrix, Time O(m*n), Space O(1)
	static void print(int[][] matrix) {
	    for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j ++) 
	            System.out.print( matrix[i][j] + " ");
	        System.out.println();
	    }
	}
	
	//Print a board, Time O(m*n), Space O(1)
	static void printBoard(char[][] board) {
	    for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[0].length; j ++) 
	            System.out.print( board[i][j] + " ");
	        System.out.println();
	    }
	}
	
	public static void main(String[] args) {
		//Initialize a board
		int rows = 3;
		int columns = 3;
		char[][] board = new char[rows][columns];
		
		//Set values in the board
		Matrix.set(board, 0, 1, 'x');
		Matrix.set(board, 1, 1, 'o');
		Matrix.printBoard(board);
		
		//Get values from the board
		int r = 1, c = 1;
		char ch = Matrix.get(board, r, c);
		if (ch == Character.MAX_VALUE)
			System.out.println("invalid input");
		else if (ch == Character.MIN_VALUE)
			System.out.println("it is empty");
		else
			System.out.println("The cell is: " + ch);
		
		//initialize a matrix
		int[][] matrix = {{1,  4,  6,  7},
				          {5, 10, 14, 18},
				          {11, 12, 17, 20}};
		System.out.println("Input matrix:");
		Matrix.print(matrix);
		
		//Search
		int key = 14;
		int[] b = Matrix.searchMatrix(matrix, key);
		System.out.println("Find "+ key + " (linear search) at: " + b[0] + "," + b[1]);
		
		int[] b1 = Matrix.searchSortedMatrix(matrix,key);
		System.out.println("Find "+ key + " (binary search) at: " + b1[0] + "," + b1[1]);
	}
}
