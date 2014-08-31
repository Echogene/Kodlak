package standardgame.choice;

import com.sun.istack.internal.NotNull;
import model.choice.ChoiceException;
import model.choice.single.SingleChoice;
import standardgame.player.StandardPlayer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

import static standardgame.choice.ChoiceLock.ChoiceCondition;

/**
 * The default implementation for a single player's choice of a player.
 * @author Steven Weston
 */
public class StandardSinglePlayerChoice extends SingleChoice<StandardPlayer, StandardPlayer>
		implements StandardChoice<StandardPlayer, StandardPlayer> {

	private StandardPlayer choice = null;
	private final ChoiceLock lock;
	private final ChoiceCondition chosen;

	/**
	 * Create a choice where the chooser has to choose from the choices.
	 * @param id the identifier of this choice
	 * @param chooser the player choosing
	 * @param choices the players who can be chosen
	 * @param lock a lock that waits for the choice
	 */
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

	@Override
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
