package standardgame.choice;

import model.choice.single.SingleChoiceFactory;
import standardgame.player.StandardPlayer;

import java.util.Collection;

/**
 * @author Steven Weston
 */
public class StandardSinglePlayerChoiceFactory implements SingleChoiceFactory<StandardPlayer, StandardPlayer, StandardSinglePlayerChoice> {

	public StandardSinglePlayerChoiceFactory() {
	}

	@Override
	public StandardSinglePlayerChoice create(
			StandardPlayer chooser, Collection<StandardPlayer> choices
	) {
		return new StandardSinglePlayerChoice(chooser, choices);
	}
}
