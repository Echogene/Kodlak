package server.components.dao.choice;

import server.components.dao.Builder;
import server.components.dao.IdentifiableBuilder;
import standardgame.choice.StandardChoice;

/**
 * @author Steven Weston
 */
public abstract class StandardChoiceBuilder<C extends StandardChoice, B extends Builder<C>>
		extends IdentifiableBuilder<C, B> {

	protected StandardChoiceBuilder(int id) {
		super(id);
	}
}
