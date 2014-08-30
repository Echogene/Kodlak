package server.components.dao.choice;

import model.choice.single.SingleChoiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standardgame.choice.StandardSinglePlayerChoice;
import standardgame.player.StandardPlayer;

import java.util.Collection;

/**
 * Create player choices for single players with this factory.  Basically a wrapper for the dao's creation method.
 * @author Steven Weston
 */
@Component
public class StandardSinglePlayerChoiceFactory
		implements SingleChoiceFactory<StandardPlayer, StandardPlayer, StandardSinglePlayerChoice> {

	private final StandardSinglePlayerChoiceDao choiceDao;

	@Autowired
	public StandardSinglePlayerChoiceFactory(StandardSinglePlayerChoiceDao choiceDao) {
		this.choiceDao = choiceDao;
	}

	@Override
	public StandardSinglePlayerChoice create(
			StandardPlayer chooser, Collection<StandardPlayer> choices
	) {
		return choiceDao.create()
				.create(chooser, choices);
	}
}
