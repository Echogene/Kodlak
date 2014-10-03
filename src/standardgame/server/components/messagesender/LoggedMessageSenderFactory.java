package standardgame.server.components.messagesender;

import model.message.MessageSenderFactory;
import standardgame.player.StandardPlayer;

/**
 * @author Steven Weston
 */
public class LoggedMessageSenderFactory implements MessageSenderFactory<PlayerMessageSender, StandardPlayer> {

	private final MessageLog log;

	public LoggedMessageSenderFactory(MessageLog log) {
		this.log = log;
	}

	@Override
	public PlayerMessageSender create(StandardPlayer player) {

		return new PlayerMessageSender(log, player);
	}
}
