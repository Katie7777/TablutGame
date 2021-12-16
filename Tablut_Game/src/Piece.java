
/**
 * piece on board
 *
 */
public class Piece {

	private String type;
	

	/**
	 * piece type could be Black, White, King, or Empty
	 * construct a piece with the default type "Empty"
	 */
	public Piece() {
		type = "Empty";
		

	}

	/**
	 * get the type of the piece
	 * @return
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * set the type of the piece
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}




}
