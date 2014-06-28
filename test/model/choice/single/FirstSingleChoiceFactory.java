package model.choice.single;

import model.player.Player;

import java.util.List;

/**
 * @author Steven Weston
 */
public class FirstSingleChoiceFactory<T> implements SingleChoiceFactory<T, FirstSingleChoice<T>> {

	@Override
	public FirstSingleChoice<T> create(Player chooser, List<T> choices) {

		return new FirstSingleChoice<>(chooser, choices);
	}
}
