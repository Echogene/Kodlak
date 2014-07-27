package model.choice.single;

import com.sun.istack.internal.NotNull;
import model.choice.AbstractChoice;
import model.player.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author Steven Weston
 */
public abstract class SingleChoice<P extends Player, T> extends AbstractChoice<P, T> {

	protected final @NotNull P chooser;

	public SingleChoice(@NotNull P chooser, @NotNull Collection<T> choices) {
		super(choices);
		this.chooser = chooser;
	}

	@Override
	public Set<P> getChoosers() {
		return Collections.singleton(chooser);
	}
}
