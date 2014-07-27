package model.choice.single;

import standardgame.player.StandardPlayer;

import java.util.Collection;

/**
 * @author Steven Weston
 */
public class FirstSingleChoiceFactory<T> implements SingleChoiceFactory<StandardPlayer, T, FirstSingleChoice<T>> {

	@Override
	public FirstSingleChoice<T> create(StandardPlayer chooser, Collection<T> choices) {

		return new FirstSingleChoice<>(chooser, choices);
	}
}
