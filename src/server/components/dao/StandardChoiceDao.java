package server.components.dao;

import org.springframework.stereotype.Component;
import standardgame.choice.StandardChoice;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Steven Weston
 */
@Component
public class StandardChoiceDao implements ChoiceDao<StandardChoice> {

	@Override
	public StandardChoice getById(String id) {

		throw new NotImplementedException();
	}

	@Override
	public void delete(String id) {

		throw new NotImplementedException();
	}
}
