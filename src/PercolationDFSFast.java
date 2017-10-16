
public class PercolationDFSFast extends PercolationDFS {
	
	@Override
	public boolean isOpen(int row, int col) {
		if(!inBounds(row,col)) {
			throw new IndexOutOfBoundsException("Index " + row + "," + col + "out of bounds");
		}
		return super.isOpen(row,col);
	}
	

}
