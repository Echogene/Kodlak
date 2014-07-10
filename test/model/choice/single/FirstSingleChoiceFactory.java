package model.choice.single;

import model.player.Player;

import java.util.Set;

/**
 * @author Steven Weston
 */
public class FirstSingleChoiceFactory<T> implements SingleChoiceFactory<T, FirstSingleChoice<T>> {

	@Override
	public FirstSingleChoice<T> create(Player chooser, Set<T> choices) {

		return new FirstSingleChoice<>(chooser, choices);
	}
}
