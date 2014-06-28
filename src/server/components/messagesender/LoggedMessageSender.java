package server.components.messagesender;

import model.message.Message;
import model.message.PlayerMessageSender;
import model.player.Player;

/**
 * @author Steven Weston
 */
public class LoggedMessageSender implements PlayerMessageSender {

	private final MessageLog log;
	private Player<?, ?, ?> player;

	public LoggedMessageSender(MessageLog log) {
		this.log = log;
	}

	@Override
	public void send(Message message) {

		log.logMessage(player, message);
	}

	@Override
	public void setPlayer(Player<?, ?, ?> player) {
		this.player = player;
	}
}
