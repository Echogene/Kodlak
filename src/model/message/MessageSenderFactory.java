package model.message;

import model.player.Player;

/**
 * @author Steven Weston
 */
public interface MessageSenderFactory<S extends MessageSender> {

	S create(Player<?, ?, ?, ?> player);
}
