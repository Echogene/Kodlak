package standardgame.server.components;

import model.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import standardgame.game.StandardGame;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Steven Weston
 */
@Controller
public class GameController {

	private final StandardGame game;

	@Autowired
	public GameController(StandardGame game) {
		this.game = game;
	}

	@RequestMapping(value = "/getPhase.do", method = GET)
	@ResponseBody
	public Phase getCurrentPhase() {
		return game.getCurrentPhase();
	}

	@RequestMapping(value = "/advancePhase.do", method = GET)
	@ResponseBody
	public void advanceCurrentPhase() {
		// todo: probably should check that we're not in the middle of doing something for a phase
		game.getCurrentPhase().advance();
	}
}
