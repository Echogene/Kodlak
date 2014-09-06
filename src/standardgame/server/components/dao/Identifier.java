package standardgame.server.components.dao;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Stores the current id to use for an new {@link Identifiable}.
 * @author Steven Weston
 */
@Component
public class Identifier {

	private final AtomicInteger id = new AtomicInteger();

	public int getNewId() {
		return id.getAndIncrement();
	}
}
