
public class Main {

	  public static void main(String args[]){  

		  Cell[][] grid = new Cell[4][4];
		  grid[3][3] = Cell.JOHN;
		  grid[2][2] = Cell.STORE;
		  grid[1][2] = Cell.OBSTACLE;
		  grid[1][3] = Cell.OBSTACLE;
		  grid[0][0] = Cell.WHITEWALKER;
		  grid[0][1] = Cell.WHITEWALKER;
		  
		  for(int i = 0; i <= 3; i++){
			  for(int j = 0; j <= 3; j++) {
				  if(grid[i][j] == null) {
					  grid[i][j] = Cell.BLANK;
				  }
			  }
		  }
		  
		  SaveWestros.search(grid,"BFS",true); 
		  
		  
	   }  
	}
