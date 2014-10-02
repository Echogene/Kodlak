package standardgame.server.components.messagesender;

import model.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Steven Weston
 */
@Controller
public class MessageLogController {

	private final MessageLog messageLog;

	@Autowired
	public MessageLogController(MessageLog messageLog) {
		this.messageLog = messageLog;
	}

	@RequestMapping(value = "/getLog.do", method = GET)
	@ResponseBody
	public String getSystemLog() {
		StringBuilder sb = new StringBuilder();
		List<Message> messages = messageLog.getMessages();
		for (int i = messages.size() - 1; i >= 0; i--) {
			Message message = messages.get(i);
			sb.append(message.getSystemMessage());
			sb.append("\n");
		}
		return sb.toString();
	}

	@RequestMapping(value = "/getPlayerLog.do", method = GET)
	@ResponseBody
	public String getPlayerLog(
			long playerId
	) {
		StringBuilder sb = new StringBuilder();
		List<Message> messages = messageLog.getMessagesFor(playerId);
		for (int i = messages.size() - 1; i >= 0; i--) {
			Message message = messages.get(i);
			sb.append(message.getMessage());
			sb.append("\n");
		}
		return sb.toString();
	}
}
