package standardgame.server.components.messagesender;

import model.message.Message;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import standardgame.player.StandardPlayer;
import standardgame.server.components.dao.Identifiable;
import util.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.function.FunctionUtils.safeGet;

/**
 * @author Steven Weston
 */
@Component
public class MessageLog {

	private final Map<Long, List<Message>> messages = new HashMap<>();
	private final List<Message> messagesInOrder = new ArrayList<>();

	public void logMessage(@Nullable StandardPlayer player, Message message) {

		Long id = safeGet(player, Identifiable::getId);
		MapUtils.updateListBasedMap(messages, id, message);
		messagesInOrder.add(message);
	}

	public void log(Message message) {
		logMessage(null, message);
	}

	public List<Message> getMessages() {
		return messagesInOrder;
	}

	public List<Message> getMessagesFor(long playerId) {
		return messages.get(playerId);
	}
}
