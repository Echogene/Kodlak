package server.components.messagesender;

import model.message.Message;
import model.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steven Weston
 */
public class MessageLog {

	private final Map<Player<?, ?, ?, ?, ?>, Message> messages = new HashMap<>();

	public void logMessage(Player<?, ?, ?, ?, ?> player, Message message) {
		messages.put(player, message);
	}
}
