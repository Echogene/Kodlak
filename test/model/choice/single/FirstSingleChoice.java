package model.choice.single;

import com.sun.istack.internal.NotNull;
import standardgame.player.StandardPlayer;

import java.util.Collection;

/**
 * Choose the first thing in the list.  For <s>science!</s> testing.
 */
public class FirstSingleChoice<T> extends SingleChoice<StandardPlayer, T> {

	public FirstSingleChoice(@NotNull StandardPlayer chooser, @NotNull Collection<T> choices) {
		super(chooser, choices);
	}

	@Override
	public T getChoice() {
		return choices.iterator().next();
	}

	@Override
	public boolean hasChosen() {
		return true;
	}
}