
/**
 * player of the game
 *
 */
public class Player {

	private String color;
	private String name;
	
	
	/**
	 * construct a player with a given color and the default name ""
	 * @param playerColor
	 */
	public Player(String playerColor){
		color = playerColor;
		this.name = "";
	}
	
	/**
	 * set a name to a player
	 * @param playerInput
	 */
	public void set_player_name(String playerInput)
	{
		this.name = playerInput;

	}
	
	/**
	 * get the color of the player
	 * @return
	 */
	public String get_color(){
		return color;
	}
	

}
