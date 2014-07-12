package standardgame.choice;

import model.choice.single.SingleChoiceFactory;
import model.player.Player;
import standardgame.player.StandardPlayer;

import java.util.Set;

/**
 * @author Steven Weston
 */
public class StandardSinglePlayerChoiceFactory implements SingleChoiceFactory<StandardPlayer, StandardSinglePlayerChoice> {

	public StandardSinglePlayerChoiceFactory() {
	}

	@Override
	public StandardSinglePlayerChoice create(
			Player chooser, Set<StandardPlayer> choices
	) {
		return new StandardSinglePlayerChoice(chooser, choices);
	}
}
