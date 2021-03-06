package model.player;

import model.alignment.Aligned;
import model.alignment.Alignment;
import model.phase.Phase;
import model.player.status.Status;
import model.role.Role;

import java.util.List;

/**
 * @author Steven Weston
 */
public interface Player<
		P extends Phase,
		A extends Alignment,
		S extends Status,
		R extends Role<P, A, Y>,
		Y extends Player<P, A, S, R, Y>
> extends Aligned<A> {

	String getName();

	List<R> getRoles();

	void kill();
}
