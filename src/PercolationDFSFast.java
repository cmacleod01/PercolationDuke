//Carolyn Macleod
public class PercolationDFSFast extends PercolationDFS {
	
	public PercolationDFSFast(int n) { //initialize with super
		super(n);
	}
	
	@Override
	public boolean isOpen(int row, int col) { //checks if cell is open
		if(!inBounds(row,col)) { //check bounds
			throw new IndexOutOfBoundsException("Index " + row + "," + col + "out of bounds");
		}
		return super.isOpen(row,col);
	}
	
	@Override
	public void open(int row, int col) { //opens cell
		if(!inBounds(row,col)) { //checks bounds
			throw new IndexOutOfBoundsException("Index " + row + "," + col + "out of bounds");
		}
		if (myGrid[row][col] != BLOCKED) //check if blocked
			return;
		myOpenCount += 1; //update open cell count
		myGrid[row][col] = OPEN; //set to open
		if (row == 0) {
			myGrid[row][col]	 = FULL; //set top row equal to open
		}
		super.updateOnOpen(row,col);
		
}
	
	@Override
	public boolean isFull(int row, int col) { //check if full
		if(!inBounds(row,col)) { //bounds
			throw new IndexOutOfBoundsException("Index " + row + "," + col + " out of bounds");
		}
		return super.myGrid[row][col] == FULL; //mark as full
	}
	
	@Override
	protected void updateOnOpen(int row, int col) { //check neighbors after opening
	
		if(!inBounds(row,col)) { //bounds
			throw new IndexOutOfBoundsException("Index " + row + "," + col + " out of bounds");}
		
		if(isFull(row,col)) {
			super.dfs(row,col);
			super.myGrid[row][col] = FULL;
		}
		if(inBounds(row-1,col)) { //down
			if(isFull(row - 1, col)) {
				super.dfs(row - 1, col);
				super.myGrid[row][col] = FULL;
		}
		}
		if(inBounds(row,col-1)) { //left
			if(isFull(row, col - 1)) {
				super.dfs(row, col - 1);
				super.myGrid[row][col] = FULL;
		}
		}
		if(inBounds(row,col + 1)) { //right
			if(isFull(row, col + 1)) {
				super.dfs(row, col + 1);
				super.myGrid[row][col] = FULL;
		}
		}
		if(inBounds(row+1,col)) { //up
			if(isFull(row + 1, col)) {
				super.dfs(row + 1, col);
				super.myGrid[row][col] = FULL;
		}
		}
	
	
	
	}
	

}
