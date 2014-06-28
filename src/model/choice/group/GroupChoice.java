package model.choice.group;

import model.choice.AbstractChoice;
import com.sun.istack.internal.NotNull;
import model.player.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Set;

/**
 * @author Steven Weston
 */
public class GroupChoice<T> extends AbstractChoice<T> {

	protected final @NotNull Set<Player> choosers;

	public GroupChoice(@NotNull Set<Player> choosers, @NotNull List<T> choices) {
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
}
