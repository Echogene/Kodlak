package server.components.dao.choice;

import server.components.dao.Identifier;
import server.components.dao.Reader;
import standardgame.choice.ChoiceLock;
import standardgame.choice.StandardChoice;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Steven Weston
 */
public class StandardChoiceReader<C extends StandardChoice> implements Reader<C> {

	protected final Identifier identifier;
	protected final ChoiceLock lock;

	public StandardChoiceReader(Identifier identifier, ChoiceLock lock) {

		this.identifier = identifier;
		this.lock = lock;
	}

	@Override
	public C getById(long id) {

		throw new NotImplementedException();
	}
}
