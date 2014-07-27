package model.role;

import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;

/**
 * @author Steven Weston
 */
public interface RoleFactory<
		P extends Phase,
		A extends Alignment,
		R extends Role<P, A, Y>,
		Y extends Player<P, A, ?, ? extends Role<P, A, Y>, Y>
> {

	String getRoleName();

	R create(Y player);
}
