package model.game;

import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;
import model.player.status.Status;
import model.role.Role;

/**
 * @author Steven Weston
 */
public abstract class AbstractGame<
		P extends Phase,
		A extends Alignment,
		S extends Status,
		R extends Role<P, A, Y>,
		Y extends Player<P, A, S, R, Y>
> implements Game<P, A, S, R, Y> {

	/**
	 * The current phase of the game.
	 */
	protected final P currentPhase;

	protected AbstractGame(P currentPhase) {
		this.currentPhase = currentPhase;
	}

	@Override
	public P getCurrentPhase() {
		return currentPhase;
	}
}
