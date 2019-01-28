public abstract class Problem {
	
	Object[] operators;
	Object initialState;
	int limit;
	
	
	public abstract Object[] operators();
	
	public abstract Object getInitialState();
	
	public abstract double pathCost();
	
	public abstract boolean goolTest(Node currentnode);
	
	
	
}