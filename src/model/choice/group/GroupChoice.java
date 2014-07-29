package model.choice.group;

import com.sun.istack.internal.NotNull;
import model.choice.AbstractChoice;
import model.player.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * @author Steven Weston
 */
public class GroupChoice<P extends Player, T> extends AbstractChoice<P, T> {

	protected final @NotNull Set<P> choosers;

	public GroupChoice(@NotNull Set<P> choosers, @NotNull Set<T> choices) {
		super(choices);
		if (choosers.isEmpty()) {
			throw new IllegalArgumentException("There must be at least one chooser.");
		}
		this.choosers = choosers;
	}

	@Override
	public T getChoice() {
		throw new NotImplementedException();
	}

	@Override
	public boolean hasChosen() {
		throw new NotImplementedException();
	}

	@Override
	public Set<P> getChoosers() {
		return choosers;
	}
}
