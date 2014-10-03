package model.message;

import model.player.Player;

/**
 * @author Steven Weston
 */
public interface MessageSenderFactory<S extends MessageSender, P extends Player<?, ?, ?, ?, ?>> {

	S create(P player);
}
