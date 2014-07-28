package standardgame.choice;

import com.sun.istack.internal.NotNull;
import model.choice.ChoiceException;
import model.choice.single.SingleChoice;
import standardgame.player.StandardPlayer;

import java.util.Collection;

/**
 * @author Steven Weston
 */
public class StandardSinglePlayerChoice extends SingleChoice<StandardPlayer, StandardPlayer> {

	private StandardPlayer choice = null;

	public StandardSinglePlayerChoice(
			@NotNull StandardPlayer chooser,
			@NotNull Collection<StandardPlayer> choices
	) {
		super(chooser, choices);
	}

	@Override
	public StandardPlayer getChoice() throws ChoiceException {
		while (choice == null) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				throw new ChoiceException(e);
			}
		}
		return choice;
	}

	public synchronized void choose(@NotNull StandardPlayer choice) throws ChoiceException {
		if (this.choice == null) {
			this.choice = choice;
		} else {
			throw new ChoiceException("Cannot choose more than once.");
		}
	}
}
