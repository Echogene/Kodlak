package standardgame.choice;

import com.sun.istack.internal.NotNull;
import model.choice.ChoiceException;
import model.choice.single.SingleChoice;
import standardgame.player.StandardPlayer;

import java.util.Collection;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Steven Weston
 */
public class StandardSinglePlayerChoice extends SingleChoice<StandardPlayer, StandardPlayer> {

	private StandardPlayer choice = null;
	private final Lock lock;
	private final Condition chosen;

	public StandardSinglePlayerChoice(
			@NotNull StandardPlayer chooser,
			@NotNull Collection<StandardPlayer> choices,
			Lock lock
	) {
		super(chooser, choices);
		this.lock = lock;
		this.chosen = lock.newCondition();
	}

	@Override
	public StandardPlayer getChoice() throws ChoiceException {
		if (hasNotChosenYet()) {
			waitForChoice();
		}
		assert !hasNotChosenYet();
		return choice;
	}

	private void waitForChoice() throws ChoiceException {
		lock.lock();
		try {
			while (hasNotChosenYet()) {
				chosen.await();
			}
		} catch (InterruptedException e) {
			throw new ChoiceException(e);
		} finally {
			lock.unlock();
		}
	}

	public synchronized void choose(@NotNull StandardPlayer choice) throws ChoiceException {
		if (hasNotChosenYet()) {
			this.choice = choice;
			chosen.signal();
		} else {
			throw new ChoiceException("Cannot choose more than once.");
		}
	}

	private boolean hasNotChosenYet() {
		return choice == null;
	}
}
