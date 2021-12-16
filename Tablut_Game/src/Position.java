/**
 * board position
 *
 */
public class Position {

	int row;
	int column;

	/**
	 * construct a position with given numbers of row and column
	 * @param row
	 * @param column
	 */
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * get the row of the position
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * get the column of the position
	 * @return
	 */
	public int getColumn() {
		return column;
	}
	
	
	/**
	 * check if two positions are the same (when player moves a piece need check if
	 * the end position is the same as the thronePosiiton)
	 * @param other
	 * @return
	 */
	public boolean samePosition(Position other) {
		if (this.row == other.row && this.column == other.column) {
			return true;
		}
		return false;

	}



}
