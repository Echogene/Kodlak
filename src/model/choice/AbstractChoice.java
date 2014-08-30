package model.choice;

import com.sun.istack.internal.NotNull;
import model.player.Player;

import java.util.Collection;

/**
 * @author Steven Weston
 */
public abstract class AbstractChoice<P extends Player, T> implements Choice<P, T> {

	private final long id;

	protected final @NotNull Collection<T> choices;

	public AbstractChoice(long id, @NotNull Collection<T> choices) {

		this.id = id;
		if (choices.isEmpty()) {
			throw new IllegalArgumentException("Cannot choose from empty list.");
		}
		this.choices = choices;
	}

	@Override
	public long getId() {
		return id;
	}
}
