package server.components;

import model.choice.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import standardgame.choice.ChoiceLock;
import standardgame.game.StandardGame;
import standardgame.player.StandardPlayer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Steven Weston
 */
@Controller
public class ChoiceController {

	private final StandardGame game;
	private final ChoiceLock lock;

	@Autowired
	public ChoiceController(StandardGame game, ChoiceLock lock) {
		this.game = game;
		this.lock = lock;
	}

	@RequestMapping(value = "/getCurrentChoice.do", method = GET)
	@ResponseBody
	public Choice getCurrentChoice() {
		return lock.getCurrentlyWaiting();
	}

	@RequestMapping(value = "/choosePlayer.do", method = POST)
	@ResponseBody
	public void choose(
			@RequestParam("choiceId") String choiceId,
			@RequestParam("playerId") String playerId
	) {

		StandardPlayer playerById = game.getPlayerById(playerId);
		throw new NotImplementedException();
	}
}
