package server.components.dao.choice;

import server.components.dao.Builder;
import server.components.dao.IdentifiableBuilder;
import standardgame.choice.StandardChoice;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Steven Weston
 */
public class StandardChoiceBuilder extends IdentifiableBuilder<StandardChoice, StandardChoiceBuilder.ChoiceBuilder> {

	protected StandardChoiceBuilder(int id) {
		super(id);
	}

	@Override
	public ChoiceBuilder build() {

		throw new NotImplementedException();
	}

	public class ChoiceBuilder implements Builder<StandardChoice> {

	}
}
