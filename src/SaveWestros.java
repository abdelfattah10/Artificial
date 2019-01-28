import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class SaveWestros extends Problem {
	
	int numberOfExpandedNodes = 0;
	
	public static void search(Cell[][] grid, String strategy, boolean visualize) {
		
		SaveWestros savewestros = new SaveWestros();
		savewestros.initialState = grid;
		savewestros.operators = new Action[] {Action.DOWN, Action.UP, Action.LEFT, Action.RIGHT};
		Node result = null;
		
		int cost = 0;
		int limit = 0;
		
		
		if(strategy.equals("ID")){
			//loop with incrementing limit
			while(true){
				limit++;
				result=generalSearch(savewestros, "ID", true, limit);
				if(result != null){
					cost=result.pathCost;
					break;
				}
			}
		} else{
			result = generalSearch(savewestros, strategy, visualize, 0);
			 if(result == null) {
				System.out.println("NO SOLUTION");
			 }
			 if(result != null) {
				 while(result.parent != null) {
					 System.out.println(result.actionPerformed);
					 result = result.parent;
				 }
			 }
			 
			
		}
	}
	
	
	private static Node generalSearch(SaveWestros problem, String strategy, boolean visualize, int limit) {

		//ArrayList<Cell [][]> history = new ArrayList<Cell[][]>();
		
		//Object nodes = Helper.dataStructure(strategy);
		LinkedList<Node> nodes = new LinkedList<Node>();
		
		Node node = new Node();
		node.currentState = new State((Cell[][]) problem.getInitialState(),0);
		nodes.add(node);
		//Helper.insertNode(nodes, node, strategy);
		//int maxDepth = 0;
		//int depthSoFar = 0;
		//Node currentNode = null;
		//int limit = 0;

		while (true) {
			if(nodes.isEmpty()) {
				return null;
			} else {
				Node n = nodes.removeFirst();
				if(Helper.goalTest(n.currentState.grid)) {
					return n;
				} else {
				ArrayList<Node> expandedNodes = Helper.expandNode(n);
				switch(strategy){
				case"BFS" :
					while(!expandedNodes.isEmpty())
						nodes.addLast(expandedNodes.remove(0));
					break;
					
				case"DFS" :
					while(!expandedNodes.isEmpty()){
						nodes.addFirst(expandedNodes.remove(0));
					}
					break;	
					
				case"ID":
					expandedNodes = Helper.expandNode(n);
						while(!expandedNodes.isEmpty()){
							if(expandedNodes.get(0).getDepth() < limit)//if the depth is still less than the limit remove the node and add it to nodes, else just remove it
								nodes.addFirst(expandedNodes.remove(0));
							else expandedNodes.remove(0);
						}
					break;
					
				case"UC":
					while(!expandedNodes.isEmpty()){
						Node new1 =expandedNodes.remove(0);
						new1.eval = new1.pathCost;
						nodes.addFirst(new1);
					}
					Collections.sort(nodes);
					break;
					
					default : break;
				}
			  }
			}
		}
//			if (Helper.checkEmpty(nodes, strategy)) {
//				if (strategy != 1) {
//					return null;
//				} else {
//					maxDepth++;
//					//depthSoFar = 0;
//					Node node2 = new Node();
//					//node2.currentState = (Cell[][]) problem.getInitialState();
//					Helper.insertNode(nodes, node, strategy);
//					continue;
//				}
//			}
//			currentNode = Helper.removeFront(nodes, strategy);
//			if (visualize) {
//				//Helper.drawGrid((Cell[][]) currentNode.currentState);
//				}
//			//history.add((Cell[][]) currentNode.currentState);
//			if (problem.goolTest(currentNode)) {
//				return currentNode;
//			}else {
//				ArrayList<Node> expandedNodes = Helper.expandNode(currentNode);
//				problem.numberOfExpandedNodes++;
//				
//				while(!expandedNodes.isEmpty())
//					nodes.addLast(expandedNodes.remove(0));
//			}
//			if (strategy != ENQUE_AT_FRONT_ITERATIVELY || (strategy == ENQUE_AT_FRONT_ITERATIVELY && currentNode.depth < maxDepth)) {
//				ArrayList<Node> expandedNodes = Helper.expandNode(problem, currentNode, history, strategy);
//				problem.numberOfExpandedNodes++;
//				for (Node node2 : expandedNodes) {
//					Helper.insertNode(nodes, node2, strategy);
//				} 
//			} 
			
			//depthSoFar++;
	//	}
		//return null;
	}


	@Override
	public Object[] operators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getInitialState() {
		// TODO Auto-generated method stub
		return this.initialState;
	}

	@Override
	public double pathCost() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean goolTest(Node currentNode) {
		Cell[][] grid = (Cell[][]) currentNode.currentState.grid;
		return Helper.goalTest(grid);
	}


}
