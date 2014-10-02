package model.message;

/**
 * A message to a player.
 * @author Steven Weston
 */
public interface Message {

	/**
	 * @return the message that the player sees.
	 */
	String getMessage();

	/**
	 * @return the message that the GM sees.
	 */
	String getSystemMessage();
}
