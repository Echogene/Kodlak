package standardgame.server.components.messagesender;

import model.message.Message;
import model.player.Player;
import util.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Steven Weston
 */
public class MessageLog {

	private final Map<Player<?, ?, ?, ?, ?>, List<Message>> messages = new HashMap<>();
	private final List<Message> messagesInOrder = new ArrayList<>();

	public void logMessage(Player<?, ?, ?, ?, ?> player, Message message) {
		MapUtils.updateListBasedMap(messages, player, message);
		messagesInOrder.add(message);
	}

	public void log(Message message) {
		logMessage(null, message);
	}
}
