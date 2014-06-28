package server.components;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import server.components.messagesender.LoggedMessageSenderFactory;
import server.components.messagesender.MessageLog;
import standardgame.game.StandardGame;

/**
 * @author Steven Weston
 */
@Controller
public class GameController {

	private final MessageLog log = new MessageLog();
	private final LoggedMessageSenderFactory messageSenderFactory = new LoggedMessageSenderFactory(log);
	private final StandardGame game = new StandardGame<>(messageSenderFactory);

	@RequestMapping("/addPlayer.do")
	public void addPlayer(@RequestParam("name") String name) {
		game.addPlayer(name);
	}
}
