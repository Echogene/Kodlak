package model.choice.group;

import com.sun.istack.internal.NotNull;
import model.choice.AbstractChoice;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * @author Steven Weston
 */
public class GroupChoice<T> extends AbstractChoice<T> {

	protected final @NotNull Set<T> choosers;

	public GroupChoice(@NotNull Set<T> choosers, @NotNull Set<T> choices) {
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
