package model.choice.single;

import com.sun.istack.internal.NotNull;
import model.choice.AbstractChoice;
import model.player.Player;

import java.util.Set;

/**
 * @author Steven Weston
 */
public abstract class SingleChoice<T> extends AbstractChoice<T> {

	protected final @NotNull Player chooser;

	public SingleChoice(@NotNull Player chooser, @NotNull Set<T> choices) {
		super(choices);
		this.chooser = chooser;
	}
}
