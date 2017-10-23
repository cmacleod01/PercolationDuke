//Carolyn Macleod
import java.util.Arrays;

public class PercolationUF implements IPercolate {
	public IUnionFind myFinder;
	public int myOpenCount;
	protected boolean[][] myGrid;
	private final int VTOP;
	private final int VBOTTOM;
	private int mySize;
	
	public PercolationUF(int size, IUnionFind finder) { //create object
		VTOP = size*size; //set top
		VBOTTOM = (size*size) +1;
		finder.initialize(size*size + 2);
		myFinder = finder;
		myGrid = new boolean[size][size];
		myOpenCount = 0;
		mySize = size;
		for (boolean[] row : myGrid)
			for (boolean each : row) 
				each = false;	
	}
	
	int getIndex(int row,int col) { //create single, unique int value for each cell
		if(!inBounds(row,col)) {
			throw new IndexOutOfBoundsException("Index " + row + "," + col + " out of bounds");
		}
		int index = (row*mySize)+col;
		return index;
	}
	
protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= mySize) return false;
		if (col < 0 || col >= mySize) return false;
		return true;
	}
	
protected void updateOnOpen(int row, int col) {
		if(row==0 && inBounds(row,col)){ //if in top row
			myFinder.union(getIndex(row,col),VTOP);
			
		}
		if(row==mySize-1 && inBounds(row,col)){ //if in bottom row
			myFinder.union(getIndex(row,col), VBOTTOM);
			
		}
		if(inBounds(row+1,col) && myGrid[row+1][col]) {
			myFinder.union(getIndex(row+1,col), getIndex(row,col));
		
		}
		if(inBounds(row-1,col) && myGrid[row-1][col]) {
			myFinder.union(getIndex(row-1,col), getIndex(row,col));
			
		}
		if(inBounds(row,col+1) && myGrid[row][col+1]) {
			myFinder.union(getIndex(row,col+1), getIndex(row,col));
			
		}
		if(inBounds(row,col-1) && myGrid[row][col-1]) {
			myFinder.union(getIndex(row,col-1), getIndex(row,col));
			
		}
		
		
	}
@Override
	public boolean isOpen(int row, int col) {
	if(!inBounds(row,col)) {
		throw new IndexOutOfBoundsException("Index " + row + "," + col + " out of bounds");
	}
//	System.out.println(row);
		return myGrid[row][col];
	}

@Override
	public boolean isFull(int row, int col) {
	if(!inBounds(row,col)) {
		throw new IndexOutOfBoundsException("Index " + row + "," + col + " out of bounds");
	}
//		return myGrid[row][col] == true;
	return myFinder.connected(VTOP, getIndex(row,col));
		
	}

@Override
public boolean percolates() { //check for bounds
	
	if (myFinder.connected(VTOP, VBOTTOM)) {
			return true; }
	return false;
}

@Override
public int numberOfOpenSites() { //check for bounds
	return myOpenCount;
}

@Override
	public void open(int row, int col) {
	if(!inBounds(row,col)) {
		throw new IndexOutOfBoundsException("Index " + row + "," + col + " out of bounds");
	}
		if (myGrid[row][col] != false)
			return;
		myOpenCount += 1;
		myGrid[row][col] = true;
		if (row == 0) {
			myGrid[row][col] = true;
		}
		updateOnOpen(row,col); 
	}

}
