package standardgame.server.components.messagesender;

import model.message.MessageSender;

/**
 * @author Steven Weston
 */
public abstract class AbstractLoggedMessageSender implements MessageSender {

	protected final MessageLog log;

	protected AbstractLoggedMessageSender(MessageLog log) {
		this.log = log;
	}
}
