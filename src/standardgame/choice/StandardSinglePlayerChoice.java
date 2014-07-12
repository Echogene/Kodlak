package standardgame.choice;

import com.sun.istack.internal.NotNull;
import model.choice.single.SingleChoice;
import model.player.Player;
import standardgame.player.StandardPlayer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * @author Steven Weston
 */
public class StandardSinglePlayerChoice extends SingleChoice<StandardPlayer> {

	public StandardSinglePlayerChoice(
			@NotNull Player chooser,
			@NotNull Set<StandardPlayer> choices
	) {
		super(chooser, choices);
	}

	@Override
	public StandardPlayer getChoice() {

		throw new NotImplementedException();
	}
}
