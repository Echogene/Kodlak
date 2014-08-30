package model.choice.group;

import model.player.Player;

import java.util.Collection;
import java.util.Set;

/**
 * @author Steven Weston
 */
public interface GroupChoiceFactory<P extends Player, T, C extends GroupChoice<P, T>> {

	C create(Set<P> choosers, Collection<T> choices);
}
