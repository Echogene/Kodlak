package server.components;

import model.choice.Choice;
import model.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import standardgame.choice.ChoiceLock;
import standardgame.game.StandardGame;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Steven Weston
 */
@Controller
public class GameController {

	private final StandardGame game;
	private final ChoiceLock lock;

	@Autowired
	public GameController(StandardGame game, ChoiceLock lock) {
		this.game = game;
		this.lock = lock;
	}

	@RequestMapping(value = "/getPhase.do", method = GET)
	@ResponseBody
	public Phase getCurrentPhase() {
		return game.getCurrentPhase();
	}

	@RequestMapping(value = "/getCurrentChoice.do", method = GET)
	@ResponseBody
	public Choice getCurrentChoice() {
		return lock.getCurrentlyWaiting();
	}
}
