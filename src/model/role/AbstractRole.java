package model.role;

import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Steven Weston
 */
public abstract class AbstractRole<P extends Phase, A extends Alignment> implements Role<P, A> {

	@JsonIgnore
	protected final Player owner;

	public AbstractRole(Player owner) {
		this.owner = owner;
	}

	public Player getOwner() {
		return owner;
	}
}
