package model.role;

import com.sun.istack.internal.NotNull;
import model.alignment.Aligned;
import model.alignment.Alignment;
import model.effect.Effect;
import model.phase.Phase;
import model.player.Player;

/**
 * @author Steven Weston
 */
public interface Role<P extends Phase, A extends Alignment, Y extends Player> extends Aligned<A> {

	/**
	 * @return the effect this role has during the given phase
	 */
	Effect getEffect(P phase);

	/**
	 * @return the Player that has this role.
	 */
	Y getOwner();

	@NotNull
	default String getName() {
		return this.getClass().getSimpleName().toLowerCase();
	}
}
