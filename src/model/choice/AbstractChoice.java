package model.choice;

import com.sun.istack.internal.NotNull;

import java.util.Set;

/**
 * @author Steven Weston
 */
public abstract class AbstractChoice<T> implements Choice<T> {

	protected final @NotNull Set<T> choices;

	public AbstractChoice(@NotNull Set<T> choices) {
		if (choices.isEmpty()) {
			throw new IllegalArgumentException("Cannot choose from empty list.");
		}
		this.choices = choices;
	}
}
