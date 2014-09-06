package standardgame.server.components.dao.choice;

import standardgame.server.components.dao.Identifier;
import standardgame.server.components.dao.Reader;
import standardgame.choice.ChoiceLock;
import standardgame.choice.StandardChoice;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

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

	@Override
	public Set<C> getAll() {

		throw new NotImplementedException();
	}
}
