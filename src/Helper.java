import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Helper {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	
	public static Object dataStructure(int strategy) {
		switch(strategy) {
		 case 0: Queue<Node> nodes = new LinkedList<Node>();
		 	return nodes;
		}
		//Remove return null
		return null;
	}
	
	public static void insertNode(Object nodes, Node node, String strategy) {
		switch(strategy) {
		case "BFS":
			Queue<Node> nodes2 = (Queue) nodes;
			nodes2.add(node);
			nodes = nodes2;
		}
	}

	public static boolean checkEmpty(Object nodes, String strategy) {
		switch(strategy) {
		case "BFS":
			Queue<Node> nodes2 = (Queue) nodes;
		    return nodes2.isEmpty();
	}
		//Remove return null
		System.out.println(",,,,,");
		return (Boolean) null;
}

	public static Node removeFront(Object nodes, int strategy) {
		switch(strategy) {
		case 0:
			Queue<Node> nodes2 = (Queue) nodes;
			return nodes2.poll();
		}
		//Remove return null
		return null;
	 }

	public static void drawGrid(Cell[][] grid) {
	    Cell[][] x = grid;
		for(int i = 0; i <= grid.length-1; i++) {
			for(int j = 0; j <= grid[0].length-1; j++) {
				if(x[i][j] == Cell.WHITEWALKER) {
					System.out.print("WALKER   ");
				} else if(x[i][j] == Cell.OBSTACLE) {
					System.out.print("OBSTCL   ");
				} else {
					System.out.print(x[i][j]+"    ");
				}
			}
			System.out.println();
			System.out.println();
		}
	}

	public static boolean goalTest(Cell[][] grid) {
		for(int i = 0; i <=3; i++) {
			for(int j = 0; j <=3; j++) {
				if(grid[i][j] == Cell.WHITEWALKER) {
					return false;
				}
			}
		 }
		return true;
		}

	public static ArrayList<Node> expandNode(Node node) {
		ArrayList<Node> possibleNodes = new ArrayList<Node>();
		Cell[][] grid = (Cell[][]) node.currentState.grid;
		//int dp = node.currentState.dragonPieces;
		
		
		int agentRow =0;
		int agentColumn =0;
		
		for(int i =0;i<grid.length;i++){
			for(int j=0; j<grid[i].length;j++){
				if(grid[i][j] == Cell.JOHN || grid[i][j] == Cell.JOHSTOR){   //It can either be john or john on store
					agentRow=i;
					agentColumn=j;
					System.out.println(i+"   "+j+" ");
				}
			}
		}
		
		if(grid[agentRow][agentColumn] == Cell.JOHSTOR) {
			node.currentState.dragonPieces += 3;
		}
		killWalkers(agentRow, agentColumn, grid, node);
		
		//4 grids are made of the original one to avoid referencing problem
		Cell[][] newGN = new Cell[grid.length][grid[0].length];
		Cell[][] newGE = new Cell[grid.length][grid[0].length];
		Cell[][] newGW = new Cell[grid.length][grid[0].length];
		Cell[][] newGS = new Cell[grid.length][grid[0].length];
		for(int i =0;i<grid.length;i++){
			for(int j=0; j<grid[i].length;j++){
				newGN[i][j]=grid[i][j];
			}
		}

		for(int i =0;i<grid.length;i++){
			for(int j=0; j<grid[i].length;j++){
				newGE[i][j]=grid[i][j];
			}
		}
		for(int i =0;i<grid.length;i++){
			for(int j=0; j<grid[i].length;j++){
				newGW[i][j]=grid[i][j];
			}
		}

		for(int i =0;i<grid.length;i++){
			for(int j=0; j<grid[i].length;j++){
				newGS[i][j]=grid[i][j];
			}
		}
		Cell[][] GN = newGN;
		Cell[][] GS = newGS;
		Cell[][] GE = newGE;
		Cell[][] GW = newGW;
		
		
//		int agentRow =0;
//		int agentColumn =0;
//		
//		for(int i =0;i<grid.length;i++){
//			for(int j=0; j<grid[i].length;j++){
//				if(grid[i][j] == Cell.JOHN || grid[i][j] == Cell.JOHSTOR){   //It can either be john or john on store
//					agentRow=i;
//					agentColumn=j;
//					System.out.println(i+"   "+j+" ");
//				}
//			}
//		}

//		if(grid[agentRow][agentColumn] == Cell.JOHSTOR) {
//			node.currentState.dragonPieces += 3;
//		}
		//killWalkers(agentRow, agentColumn, GN, node);
		//killWalkers(agentRow, agentColumn, GS, node);
		//killWalkers(agentRow, agentColumn, GE, node);
		//killWalkers(agentRow, agentColumn, GW, node);
		//------------ NORTH EXPANSION ------------
		if(agentRow-1 != -1 && (GN[agentRow-1][agentColumn] == Cell.BLANK || GN[agentRow-1][agentColumn] == Cell.STORE)) {
			 if(GN[agentRow-1][agentColumn] == Cell.BLANK) {
				  if(GN[agentRow][agentColumn] == Cell.JOHSTOR) {
					  GN[agentRow-1][agentColumn] = Cell.JOHN;
					  GN[agentRow][agentColumn] = Cell.STORE;
				  } else {
				 GN[agentRow-1][agentColumn] = Cell.JOHN;
				 GN[agentRow][agentColumn] = Cell.BLANK;
				  }
			 } else if(GN[agentRow-1][agentColumn] == Cell.STORE) {
				 GN[agentRow-1][agentColumn] = Cell.JOHSTOR;
				 GN[agentRow][agentColumn] = Cell.BLANK;
			 }
			 
			 System.out.println("------GN---------");
			 Helper.drawGrid(GN);
			 State state = new State(GN, node.currentState.dragonPieces);
			 Node newNode = new Node(node, 0, 0, state, Action.UP, 0);
			 possibleNodes.add(newNode);
		}
		//------------ SOUTH EXPANSION ------------
		if(agentRow+1 != GS.length && (GS[agentRow+1][agentColumn] == Cell.BLANK || GS[agentRow+1][agentColumn] == Cell.STORE)) {
			 if(GS[agentRow+1][agentColumn] == Cell.BLANK) {
				  if(GS[agentRow][agentColumn] == Cell.JOHSTOR) {
					  GS[agentRow+1][agentColumn] = Cell.JOHN;
					  GS[agentRow][agentColumn] = Cell.STORE;
				  } else {
				 GS[agentRow+1][agentColumn] = Cell.JOHN;
				 GS[agentRow][agentColumn] = Cell.BLANK;
				  }
			 } else if(GS[agentRow+1][agentColumn] == Cell.STORE) {
				 GS[agentRow+1][agentColumn] = Cell.JOHSTOR;
				 GS[agentRow][agentColumn] = Cell.BLANK;
			 }
			 
			 System.out.println("------GS---------");
			 Helper.drawGrid(GS);
			 State state = new State(GS, node.currentState.dragonPieces);
			 Node newNode = new Node(node, 0, 0, state, Action.DOWN, 0);
			 possibleNodes.add(newNode);
		}
		//------------ WEST EXPANSION ------------
		if(agentColumn-1 != -1 && (GW[agentRow][agentColumn-1] == Cell.BLANK || GW[agentRow][agentColumn-1] == Cell.STORE)) {
			 if(GW[agentRow][agentColumn-1] == Cell.BLANK) {
				  if(GW[agentRow][agentColumn] == Cell.JOHSTOR) {
					  GW[agentRow][agentColumn-1] = Cell.JOHN;
					  GW[agentRow][agentColumn] = Cell.STORE;
				  } else {
				 GW[agentRow][agentColumn-1] = Cell.JOHN;
				 GW[agentRow][agentColumn] = Cell.BLANK;
				  }
			 } else if(GS[agentRow][agentColumn-1] == Cell.STORE) {
				 GW[agentRow][agentColumn-1] = Cell.JOHSTOR;
				 GW[agentRow][agentColumn] = Cell.BLANK;
			 }
			 
			 System.out.println("------GW---------");
			 Helper.drawGrid(GW);
			 State state = new State(GW, node.currentState.dragonPieces);
			 Node newNode = new Node(node, 0, 0, state, Action.LEFT, 0);
			 possibleNodes.add(newNode);
		}
		//------------ EAST EXPANSION ------------
		if(agentColumn+1 != GE[0].length && (GE[agentRow][agentColumn+1] == Cell.BLANK || GE[agentRow][agentColumn+1] == Cell.STORE)) {
			 if(GE[agentRow][agentColumn+1] == Cell.BLANK) {
				  if(GE[agentRow][agentColumn] == Cell.JOHSTOR) {
					  GE[agentRow][agentColumn+1] = Cell.JOHN;
					  GE[agentRow][agentColumn] = Cell.STORE;
				  } else {
				 GE[agentRow][agentColumn+1] = Cell.JOHN;
				 GE[agentRow][agentColumn] = Cell.BLANK;
				  }
			 } else if(GS[agentRow][agentColumn+1] == Cell.STORE) {
				 GE[agentRow][agentColumn+1] = Cell.JOHSTOR;
				 GE[agentRow][agentColumn] = Cell.BLANK;
			 }
			 
			 System.out.println("------GE---------");
			 Helper.drawGrid(GE);
			 State state = new State(GE, node.currentState.dragonPieces);
			 Node newNode = new Node(node, 0, 0, state, Action.RIGHT, 0);
			 possibleNodes.add(newNode);
		}
		
		return possibleNodes;
		
		}
	
	public static void killWalkers(int i, int j, Cell[][] grid, Node node) {
		if(node.currentState.dragonPieces == 0) {
			return;
		}
		//Case 1: JOHN AT RIGHT BOTTOM
		if(i == grid.length-1 && j == grid[0].length-1) {
			if(grid[i-1][j] == Cell.WHITEWALKER || grid[i][j-1] == Cell.WHITEWALKER) {
				node.currentState.dragonPieces -= 1;
			}
					if(grid[i-1][j] == Cell.WHITEWALKER) {
						grid[i-1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j-1] == Cell.WHITEWALKER) {
						grid[i][j-1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
		 //Case 2: JOHN AT LEFT BOTTOM			
		} else if(i == grid.length-1 && j == 0) {
			if(grid[i-1][j] == Cell.WHITEWALKER || grid[i][j+1] == Cell.WHITEWALKER) {
				node.currentState.dragonPieces -= 1;
			}
					if(grid[i-1][j] == Cell.WHITEWALKER) {
						grid[i-1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j+1] == Cell.WHITEWALKER) {
						grid[i][j+1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
		 //Case 3: JOHN AT LEFT UP			
		} else if(i == 0 && j == 0) {
			if(grid[i+1][j] == Cell.WHITEWALKER || grid[i][j+1] == Cell.WHITEWALKER) {
				node.currentState.dragonPieces -= 1;
			}
					if(grid[i+1][j] == Cell.WHITEWALKER) {
						grid[i+1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j+1] == Cell.WHITEWALKER) {
						grid[i][j+1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
		 //Case 4: JOHN AT RIGHT UP
		} else if(i == 0 && j == grid[0].length-1) {
			if(grid[i+1][j] == Cell.WHITEWALKER || grid[i][j-1] == Cell.WHITEWALKER) {
				node.currentState.dragonPieces -= 1;
			}
					if(grid[i+1][j] == Cell.WHITEWALKER) {
						grid[i+1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j-1] == Cell.WHITEWALKER) {
						grid[i][j-1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
		 //Case 5: JOHN AT TOP
		} else if(i == 0) {
			if(grid[i+1][j] == Cell.WHITEWALKER || grid[i][j-1] == Cell.WHITEWALKER || grid[i][j+1] == Cell.WHITEWALKER) {
				node.currentState.dragonPieces -= 1;
			}
					if(grid[i+1][j] == Cell.WHITEWALKER) {
						grid[i+1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j-1] == Cell.WHITEWALKER) {
						grid[i][j-1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j+1] == Cell.WHITEWALKER) {
						grid[i][j+1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
		 //Case 6: JOHN AT BOTTOM			
		} else if(i == grid.length-1) {
			if(grid[i-1][j] == Cell.WHITEWALKER || grid[i][j+1] == Cell.WHITEWALKER || grid[i][j-1] == Cell.WHITEWALKER) {
				node.currentState.dragonPieces -= 1;
			}
					if(grid[i-1][j] == Cell.WHITEWALKER) {
						grid[i-1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j+1] == Cell.WHITEWALKER) {
						grid[i][j+1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j-1] == Cell.WHITEWALKER) {
						grid[i][j-1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
		 //Case 7: JOHN AT LEFT CORNER	
		} else if(j == 0) {
			if(grid[i-1][j] == Cell.WHITEWALKER || grid[i+1][j] == Cell.WHITEWALKER || grid[i][j+1] == Cell.WHITEWALKER) {
				node.currentState.dragonPieces -= 1;
			}
					if(grid[i-1][j] == Cell.WHITEWALKER) {
						grid[i-1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i+1][j] == Cell.WHITEWALKER) {
						grid[i+1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j+1] == Cell.WHITEWALKER) {
						grid[i][j+1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
		 //Case 8: JOHN AT RIGHT CORNER
		} else if(j == grid[0].length-1) {
			if(grid[i-1][j] == Cell.WHITEWALKER || grid[i+1][j] == Cell.WHITEWALKER || grid[i][j-1] == Cell.WHITEWALKER) {
				node.currentState.dragonPieces -= 1;
			}
					if(grid[i-1][j] == Cell.WHITEWALKER) {
						grid[i-1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i+1][j] == Cell.WHITEWALKER) {
						grid[i+1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j-1] == Cell.WHITEWALKER) {
						grid[i][j-1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
		} else {
			if(grid[i-1][j] == Cell.WHITEWALKER || grid[i+1][j] == Cell.WHITEWALKER || grid[i][j-1] == Cell.WHITEWALKER || grid[i][j+1] == Cell.WHITEWALKER) {
				node.currentState.dragonPieces -= 1;
			  }
					if(grid[i-1][j] == Cell.WHITEWALKER) {
						grid[i-1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i+1][j] == Cell.WHITEWALKER) {
						grid[i+1][j] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j-1] == Cell.WHITEWALKER) {
						grid[i][j-1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
					if(grid[i][j+1] == Cell.WHITEWALKER) {
						grid[i][j+1] = Cell.BLANK;
						System.out.println(ANSI_RED +" Walker died "+ANSI_RESET);
					}
		}
	 } 
	}
