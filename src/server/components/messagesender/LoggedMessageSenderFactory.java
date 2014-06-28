package server.components.messagesender;

import model.message.MessageSenderFactory;
import standardgame.player.StandardPlayer;

/**
 * @author Steven Weston
 */
public class LoggedMessageSenderFactory implements MessageSenderFactory<LoggedMessageSender> {

	private final MessageLog log;

	public LoggedMessageSenderFactory(MessageLog log) {
		this.log = log;
	}

	@Override
	public LoggedMessageSender create(StandardPlayer player) {

		return new LoggedMessageSender(log);
	}
}
