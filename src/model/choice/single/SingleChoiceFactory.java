package model.choice.single;

import model.player.Player;

import java.util.Set;

/**
 * @author Steven Weston
 */
public interface SingleChoiceFactory<T, C extends SingleChoice<T>> {

	C create(Player chooser, Set<T> choices);
}
