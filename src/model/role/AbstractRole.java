package model.role;

import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;

/**
 * @author Steven Weston
 */
public abstract class AbstractRole<P extends Phase, A extends Alignment> implements Role<P, A> {

	protected final Player owner;

	public AbstractRole(Player owner) {
		this.owner = owner;
	}

	public Player getOwner() {
		return owner;
	}
}
