package model.game;

import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;
import model.player.status.Status;
import model.role.Role;

import java.util.Set;

/**
 * @author Steven Weston
 */
public interface Game<
		P extends Phase,
		A extends Alignment,
		S extends Status,
		R extends Role<P, A, Y>,
		Y extends Player<P, A, S, R, Y>
> {

	P getCurrentPhase();
}
