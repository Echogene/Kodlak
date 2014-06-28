package model.choice.single;

import model.choice.AbstractChoice;
import com.sun.istack.internal.NotNull;
import model.player.Player;

import java.util.List;

/**
 * @author Steven Weston
 */
public abstract class SingleChoice<T> extends AbstractChoice<T> {

	protected final @NotNull Player chooser;

	public SingleChoice(@NotNull Player chooser, @NotNull List<T> choices) {
		super(choices);
		this.chooser = chooser;
	}
}
