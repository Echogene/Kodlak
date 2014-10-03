package model.role;

import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Steven Weston
 */
public abstract class AbstractRole<P extends Phase, A extends Alignment, Y extends Player> implements Role<P, A, Y> {

	protected final long id;

	@JsonIgnore
	protected final Y owner;

	protected AbstractRole(long id, Y owner) {
		this.id = id;
		this.owner = owner;
	}

	public Y getOwner() {
		return owner;
	}

	@Override
	public long getId() {
		return id;
	}
}
