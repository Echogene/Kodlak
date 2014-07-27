package model.choice.single;

import model.player.Player;

import java.util.Collection;

/**
 * @author Steven Weston
 */
public interface SingleChoiceFactory<P extends Player, T, C extends SingleChoice<P, T>> {

	C create(P chooser, Collection<T> choices);
}
