package standardgame.server.components.messagesender;

import model.message.MessageSenderFactory;
import model.player.Player;

/**
 * @author Steven Weston
 */
public class LoggedMessageSenderFactory implements MessageSenderFactory<PlayerMessageSender> {

	private final MessageLog log;

	public LoggedMessageSenderFactory(MessageLog log) {
		this.log = log;
	}

	@Override
	public PlayerMessageSender create(Player<?, ?, ?, ?, ?> player) {

		return new PlayerMessageSender(log, player);
	}
}
