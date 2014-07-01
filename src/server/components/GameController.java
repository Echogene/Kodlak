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

	@RequestMapping(value = "/addPlayer.do", method = GET)
	@ResponseBody
	public StandardPlayer addPlayer(
			@RequestParam("name") String name,
			@RequestParam("top") Double top,
			@RequestParam("left") Double left
	) {

		StandardPlayer newPlayer = game.addPlayer(name);
		newPlayer.setTop(top);
		newPlayer.setLeft(left);
		return newPlayer;
	}

	@RequestMapping(value = "/editPlayer.do", method = POST)
	@ResponseBody
	public void editPlayer(
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("top") Double top,
			@RequestParam("left") Double left
	) {

		StandardPlayer player = game.getPlayerById(id);
		player.setName(name);
		player.setTop(top);
		player.setLeft(left);
	}

	@RequestMapping(value = "/getPlayers.do", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<StandardPlayer> getPlayers() {
		return game.getPlayers();
	}

	@RequestMapping(value = "/deletePlayer.do", method = POST)
	@ResponseBody
	public void deletePlayer(@RequestParam("id") String id) {
		game.deletePlayer(id);
	}
}
