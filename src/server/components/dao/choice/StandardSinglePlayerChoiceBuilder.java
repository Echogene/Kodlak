package server.components.dao.choice;

import server.components.dao.Builder;
import standardgame.choice.ChoiceLock;
import standardgame.choice.StandardSinglePlayerChoice;
import standardgame.player.StandardPlayer;

import java.util.Collection;

/**
 * @author Steven Weston
 */
public class StandardSinglePlayerChoiceBuilder
		extends StandardChoiceBuilder<StandardSinglePlayerChoice, StandardSinglePlayerChoiceBuilder.ChoiceBuilder> {

	private final ChoiceLock lock;

	protected StandardSinglePlayerChoiceBuilder(int id, ChoiceLock lock) {
		super(id);
		this.lock = lock;
	}

	@Override
	public ChoiceBuilder build() {
		return new ChoiceBuilder();
	}

	public class ChoiceBuilder implements Builder<StandardSinglePlayerChoice> {

		public StandardSinglePlayerChoice create(
				StandardPlayer chooser, Collection<StandardPlayer> choices
		) {
			return new StandardSinglePlayerChoice(id, chooser, choices, lock);
		}
	}
}
