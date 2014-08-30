package server.components.dao.choice;

import org.springframework.stereotype.Component;
import standardgame.choice.StandardChoice;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Steven Weston
 */
@Component
public class StandardChoiceDao implements ChoiceDao<StandardChoice, StandardChoiceBuilder.ChoiceBuilder> {

	@Override
	public StandardChoice getById(String id) {

		throw new NotImplementedException();
	}

	@Override
	public void delete(String id) {

		throw new NotImplementedException();
	}

	@Override
	public StandardChoiceBuilder.ChoiceBuilder create() {

		throw new NotImplementedException();
	}
}
