package model.choice;

import com.sun.istack.internal.NotNull;
import model.player.Player;

import java.util.Collection;

/**
 * @author Steven Weston
 */
public abstract class AbstractChoice<P extends Player, T> implements Choice<P, T> {

	protected final @NotNull Collection<T> choices;

	public AbstractChoice(@NotNull Collection<T> choices) {
		if (choices.isEmpty()) {
			throw new IllegalArgumentException("Cannot choose from empty list.");
		}
		this.choices = choices;
	}
}
