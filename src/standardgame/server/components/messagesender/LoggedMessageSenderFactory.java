package standardgame.server.components.messagesender;

import model.message.MessageSenderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standardgame.player.StandardPlayer;

/**
 * @author Steven Weston
 */
@Component
public class LoggedMessageSenderFactory implements MessageSenderFactory<PlayerMessageSender, StandardPlayer> {

	private final MessageLog log;

	@Autowired
	public LoggedMessageSenderFactory(MessageLog log) {
		this.log = log;
	}

	@Override
	public PlayerMessageSender create(StandardPlayer player) {

		return new PlayerMessageSender(log, player);
	}
}
