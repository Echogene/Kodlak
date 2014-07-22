package model.role;

import com.google.common.collect.Multiset;
import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;

/**
 * Assign a role to a player.
 * @author Steven Weston
 */
public interface RoleAssigner<P extends Phase, A extends Alignment, R extends Role<P, A>, Y extends Player<P, A, ?, R>> {

	R addRoleToPlayer(Y player, String roleName) throws UnavailableRoleException;

	Multiset<String> getAvailableRoles();

	boolean isRoleSupported(String roleName);

	default void addRole(String roleName) {
		getAvailableRoles().add(roleName);
	}

	default void removeRole(String roleName) {
		getAvailableRoles().remove(roleName);
	}
}
