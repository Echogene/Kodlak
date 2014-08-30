package standardgame.choice;

import model.choice.single.SingleChoiceFactory;
import standardgame.player.StandardPlayer;

import java.util.Collection;

/**
 * @author Steven Weston
 */
public class StandardSinglePlayerChoiceFactory implements SingleChoiceFactory<StandardPlayer, StandardPlayer, StandardSinglePlayerChoice> {

	private final ChoiceLock lock;

	public StandardSinglePlayerChoiceFactory(ChoiceLock lock) {
		this.lock = lock;
	}

	@Override
	public StandardSinglePlayerChoice create(
			StandardPlayer chooser, Collection<StandardPlayer> choices
	) {
		// todo: don't use 1
		return new StandardSinglePlayerChoice(1, chooser, choices, lock);
	}
}
