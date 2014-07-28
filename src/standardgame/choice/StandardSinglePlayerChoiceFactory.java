package standardgame.choice;

import model.choice.single.SingleChoiceFactory;
import standardgame.player.StandardPlayer;

import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Steven Weston
 */
public class StandardSinglePlayerChoiceFactory implements SingleChoiceFactory<StandardPlayer, StandardPlayer, StandardSinglePlayerChoice> {

	private final Lock lock = new ReentrantLock();

	public StandardSinglePlayerChoiceFactory() {
	}

	@Override
	public StandardSinglePlayerChoice create(
			StandardPlayer chooser, Collection<StandardPlayer> choices
	) {
		return new StandardSinglePlayerChoice(chooser, choices, lock);
	}
}
