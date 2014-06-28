package model.choice.single;

import com.sun.istack.internal.NotNull;
import model.player.Player;

import java.util.List;

/**
 * Choose the first thing in the list.  For <s>science!</s> testing.
 */
public class FirstSingleChoice<T> extends SingleChoice<T> {

	public FirstSingleChoice(@NotNull Player chooser, @NotNull List<T> choices) {
		super(chooser, choices);
	}

	@Override
	public T getChoice() {
		return choices.get(0);
	}
}