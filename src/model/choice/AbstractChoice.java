package model.choice;

import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * @author Steven Weston
 */
public abstract class AbstractChoice<T> implements Choice<T> {

	protected final @NotNull List<T> choices;

	public AbstractChoice(@NotNull List<T> choices) {
		if (choices.isEmpty()) {
			throw new IllegalArgumentException("Cannot choose from empty list.");
		}
		this.choices = choices;
	}
}
