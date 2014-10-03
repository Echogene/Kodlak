package standardgame.server.components.dao;

import java.util.HashMap;

/**
 * @author Steven Weston
 */
public class IdentifiableMap<I extends Identifiable> extends HashMap<Long, I> {

	public IdentifiableMap() {
		super();
	}

	public I put(I i) {
		return put(i.getId(), i);
	}
}
