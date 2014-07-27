package standardgame.choice;

import com.sun.istack.internal.NotNull;
import model.choice.single.SingleChoice;
import standardgame.player.StandardPlayer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

/**
 * @author Steven Weston
 */
public class StandardSinglePlayerChoice extends SingleChoice<StandardPlayer, StandardPlayer> {

	public StandardSinglePlayerChoice(
			@NotNull StandardPlayer chooser,
			@NotNull Collection<StandardPlayer> choices
	) {
		super(chooser, choices);
	}

	@Override
	public StandardPlayer getChoice() {

		throw new NotImplementedException();
	}
}
