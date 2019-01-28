public class Node implements Comparable{
	
	
	Node parent;
	int depth;
	int pathCost;
	State currentState;
	Object actionPerformed;
	int eval;
	
	public Node() {
		parent = null;
		depth = 0;
		pathCost = 0;
		currentState = null;
		actionPerformed = null;
		eval = 0;
	}
	
	public Node(Node parent, int depth, int pathCost, State currentState,
			Object actionPerformed, int eval) {
		super();
		this.parent = parent;
		this.depth = depth;
		this.pathCost = pathCost;
		this.currentState = currentState;
		this.actionPerformed = actionPerformed;
		this.eval = eval;
	}

	public int getDepth() {
		// TODO Auto-generated method stub
		return this.depth;
	}

	@Override
	public int compareTo(Object o) { //compare with evals 
		if(this.eval == ((Node) o).eval)
			return 0;
		if(this.eval > ((Node) o).eval)
			return 1;
		else 
			return -1;
}
	
}