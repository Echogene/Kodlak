package server.components;

import model.choice.Choice;
import model.choice.ChoiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.components.dao.choice.StandardChoiceReader;
import server.components.dao.player.StandardPlayerDao;
import standardgame.choice.ChoiceLock;
import standardgame.choice.StandardChoice;
import standardgame.game.StandardGame;
import standardgame.player.StandardPlayer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Steven Weston
 */
@Controller
public class ChoiceController {

	private final StandardGame game;
	private final ChoiceLock lock;
	private final StandardPlayerDao playerDao;
	private final StandardChoiceReader choiceDao;

	@Autowired
	public ChoiceController(
			StandardGame game,
			ChoiceLock lock,
			StandardPlayerDao playerDao,
			StandardChoiceReader choiceDao
	) {
		this.game = game;
		this.lock = lock;
		this.playerDao = playerDao;
		this.choiceDao = choiceDao;
	}

	@RequestMapping(value = "/getCurrentChoice.do", method = GET)
	@ResponseBody
	public Choice getCurrentChoice() {
		return lock.getCurrentlyWaiting();
	}

	@RequestMapping(value = "/choosePlayer.do", method = POST)
	@ResponseBody
	public void choose(
			@RequestParam("choiceId") long choiceId,
			@RequestParam("playerId") long playerId
	) throws ChoiceException {

		StandardPlayer player = playerDao.getById(playerId);
		StandardChoice choice = choiceDao.getById(choiceId);
		choice.choose(player);
	}
}
