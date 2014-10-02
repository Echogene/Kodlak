package standardgame.server.components.messagesender;

import model.message.Message;

/**
 * @author Steven Weston
 */
public class SystemMessageSender extends AbstractLoggedMessageSender {

	public SystemMessageSender(MessageLog log) {
		super(log);
	}

	@Override
	public void send(Message message) {
		log.log(message);
	}
}
