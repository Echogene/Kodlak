package server.components.dao.choice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.components.dao.Dao;
import server.components.dao.Identifier;
import standardgame.choice.ChoiceLock;
import standardgame.choice.StandardSinglePlayerChoice;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Steven Weston
 */
@Component
public class StandardSinglePlayerChoiceDao
		extends StandardChoiceReader<StandardSinglePlayerChoice>
		implements Dao<StandardSinglePlayerChoice, StandardSinglePlayerChoiceBuilder.ChoiceBuilder> {

	@Autowired
	public StandardSinglePlayerChoiceDao(Identifier identifier, ChoiceLock lock) {
		super(identifier, lock);
	}

	@Override
	public void delete(long id) {

		throw new NotImplementedException();
	}

	@Override
	public StandardSinglePlayerChoiceBuilder.ChoiceBuilder create() {

		return new StandardSinglePlayerChoiceBuilder(
				identifier.getNewId(),
				lock
		).build();
	}
}
