package server.components;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.components.messagesender.LoggedMessageSender;
import server.components.messagesender.LoggedMessageSenderFactory;
import server.components.messagesender.MessageLog;
import standardgame.game.StandardGame;
import standardgame.player.StandardPlayer;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Steven Weston
 */
@Controller
public class GameController {

	private final MessageLog log = new MessageLog();
	private final LoggedMessageSenderFactory messageSenderFactory = new LoggedMessageSenderFactory(log);
	private final StandardGame<LoggedMessageSender> game = new StandardGame<>(messageSenderFactory);

	@RequestMapping(value = "/addPlayer.do", method = POST)
	@ResponseBody
	public void addPlayer(
			@RequestParam("name") String name,
			@RequestParam("top") Double top,
			@RequestParam("left") Double left
	) {

		StandardPlayer newPlayer = game.addPlayer(name);
		newPlayer.setTop(top);
		newPlayer.setLeft(left);
	}

	@RequestMapping(value = "/getPlayers.do", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<StandardPlayer> getPlayers() {
		return game.getPlayers();
	}
}
