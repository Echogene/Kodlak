package model.message;

import model.player.Player;

/**
 * @author Steven Weston
 */
public interface PlayerMessageSender extends MessageSender {

	void setPlayer(Player<?, ?, ?, ?, ?> player);
}
