package standardgame.server.components.dao;

/**
 * @author Steven Weston
 */
public abstract class IdentifiableBuilder<I extends Identifiable, B extends Builder<I>> implements Builder<I> {

	protected final int id;

	protected IdentifiableBuilder(int id) {
		this.id = id;
	}

	public abstract B build();
}
