package standardgame.server.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import standardgame.game.StandardGame;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Steven Weston
 */
@Controller
@RequestMapping("/play")
public class PlayController {

	private final StandardGame game;

	@Autowired
	public PlayController(StandardGame game) {
		this.game = game;
	}

	@RequestMapping(method = GET)
	public ModelAndView play() {

		ModelAndView modelAndView = new ModelAndView("play");
		modelAndView.addObject("phase", game.getCurrentPhase().getPhase());

		return modelAndView;
	}
}
