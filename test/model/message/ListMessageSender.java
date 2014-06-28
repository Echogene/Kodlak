package model.message;

import java.util.ArrayList;
import java.util.List;

/**
 * Store sent message strings in a list.
 * @author Steven Weston
 */
public class ListMessageSender implements MessageSender {

	private final List<String> sentMessages = new ArrayList<>();

	@Override
	public void send(Message message) {
		sentMessages.add(message.getMessage());
	}

	public List<String> getSentMessages() {

		return sentMessages;
	}
}
