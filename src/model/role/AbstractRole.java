package model.role;

import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Steven Weston
 */
public abstract class AbstractRole<P extends Phase, A extends Alignment, Y extends Player> implements Role<P, A, Y> {

	@JsonIgnore
	protected final Y owner;

	public AbstractRole(Y owner) {
		this.owner = owner;
	}

	public Y getOwner() {
		return owner;
	}
}
