package standardgame.choice;

import com.sun.istack.internal.NotNull;
import model.choice.ChoiceException;
import model.choice.single.SingleChoice;
import standardgame.player.StandardPlayer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

import static standardgame.choice.ChoiceLock.ChoiceCondition;

/**
 * @author Steven Weston
 */
public class StandardSinglePlayerChoice extends SingleChoice<StandardPlayer, StandardPlayer>
		implements StandardChoice<StandardPlayer, StandardPlayer> {

	private StandardPlayer choice = null;
	private final ChoiceLock lock;
	private final ChoiceCondition chosen;

	public StandardSinglePlayerChoice(
			long id,
			@NotNull StandardPlayer chooser,
			@NotNull Collection<StandardPlayer> choices,
			ChoiceLock lock
	) {
		super(id, chooser, choices);
		this.lock = lock;
		this.chosen = lock.newCondition(this);
	}

	@Override
	public StandardPlayer getChoice() throws ChoiceException {
		lock.waitUntilConditionMet(chosen);
		assert hasChosen();
		return choice;
	}

	public synchronized void choose(@NotNull StandardPlayer choice) throws ChoiceException {
		if (!hasChosen()) {
			this.choice = choice;
			chosen.signal();
		} else {
			throw new ChoiceException("Cannot choose more than once.");
		}
	}

	@Override
	public boolean hasChosen() {
		return choice != null;
	}

	@Override
	public long getId() {
		throw new NotImplementedException();
	}
}
