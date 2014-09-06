package standardgame.server.components.messagesender;

import model.message.MessageSenderFactory;
import model.player.Player;

/**
 * @author Steven Weston
 */
public class LoggedMessageSenderFactory implements MessageSenderFactory<LoggedMessageSender> {

	private final MessageLog log;

	public LoggedMessageSenderFactory(MessageLog log) {
		this.log = log;
	}

	@Override
	public LoggedMessageSender create(Player<?, ?, ?, ?, ?> player) {

		return new LoggedMessageSender(log);
	}
}
