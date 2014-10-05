package standardgame.server.components.messagesender;

import model.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Steven Weston
 */
@Component
public class SystemMessageSender extends AbstractLoggedMessageSender {

	@Autowired
	public SystemMessageSender(MessageLog log) {
		super(log);
	}

	@Override
	public void send(Message message) {
		log.log(message);
	}
}
