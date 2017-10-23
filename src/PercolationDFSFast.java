
public class PercolationDFSFast extends PercolationDFS {
	
	public PercolationDFSFast(int n) {
		super(n);
	}
	
	@Override
	public boolean isOpen(int row, int col) {
		if(!inBounds(row,col)) {
			throw new IndexOutOfBoundsException("Index " + row + "," + col + "out of bounds");
		}
		return super.isOpen(row,col);
	}
	
	@Override
	public void open(int row, int col) {
		if(!inBounds(row,col)) {
			throw new IndexOutOfBoundsException("Index " + row + "," + col + "out of bounds");
		}
		if (myGrid[row][col] != BLOCKED)
			return;
		myOpenCount += 1;
		myGrid[row][col] = OPEN;
		if (row == 0) {
			myGrid[row][col]	 = FULL;
		}
		super.updateOnOpen(row,col);
		
}
	
	@Override
	public boolean isFull(int row, int col) {
		if(!inBounds(row,col)) {
			throw new IndexOutOfBoundsException("Index " + row + "," + col + " out of bounds");
		}
		return super.myGrid[row][col] == FULL;
	}
	
	@Override
	protected void updateOnOpen(int row, int col) {
	
		if(!inBounds(row,col)) {
			throw new IndexOutOfBoundsException("Index " + row + "," + col + " out of bounds");}
		
		if(isFull(row,col)) {
			super.dfs(row,col);
			super.myGrid[row][col] = FULL;
		}
		if(inBounds(row-1,col)) {
			if(isFull(row - 1, col)) {
				super.dfs(row - 1, col);
				super.myGrid[row][col] = FULL;
		}
		}
		if(inBounds(row,col-1)) {
			if(isFull(row, col - 1)) {
				super.dfs(row, col - 1);
				super.myGrid[row][col] = FULL;
		}
		}
		if(inBounds(row,col + 1)) {
			if(isFull(row, col + 1)) {
				super.dfs(row, col + 1);
				super.myGrid[row][col] = FULL;
		}
		}
		if(inBounds(row+1,col)) {
			if(isFull(row + 1, col)) {
				super.dfs(row + 1, col);
				super.myGrid[row][col] = FULL;
		}
		}
	
	
	
	}
	

}
