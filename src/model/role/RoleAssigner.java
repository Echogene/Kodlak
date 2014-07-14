package model.role;

import com.google.common.collect.Multiset;
import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;

/**
 * Assign a role to a player.
 * @author Steven Weston
 */
public interface RoleAssigner<P extends Phase, A extends Alignment, T extends Role<P, A>, Y extends Player<P, A, ?>> {

	T addRoleToPlayer(Y player, String roleName) throws UnavailableRoleException;

	Multiset<String> getAvailableRoles();

	default void addRole(String roleName) {
		getAvailableRoles().add(roleName);
	}
}
