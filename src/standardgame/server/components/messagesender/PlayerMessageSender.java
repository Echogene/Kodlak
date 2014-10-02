package standardgame.server.components.messagesender;

import model.message.Message;
import standardgame.player.StandardPlayer;

/**
 * @author Steven Weston
 */
public class PlayerMessageSender extends AbstractLoggedMessageSender {

	private final StandardPlayer player;

	public PlayerMessageSender(MessageLog log, StandardPlayer player) {
		super(log);
		this.player = player;
	}

	@Override
	public void send(Message message) {
		log.logMessage(player, message);
	}
}
