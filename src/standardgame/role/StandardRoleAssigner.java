package standardgame.role;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import model.role.RoleAssigner;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.player.StandardPlayer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steven Weston
 */
public class StandardRoleAssigner
		implements RoleAssigner<DayNightPhase, VillagerWerewolfAlignment, StandardRole, StandardPlayer> {

	private final Map<String, StandardRoleFactory<?>> factories;

	private final Multiset<String> availableRoles = HashMultiset.create();

	public StandardRoleAssigner() {

		this.factories = new HashMap<>();
	}

	public void addRoleFactory(StandardRoleFactory<?> factory) {
		factories.put(factory.getRoleName(), factory);
	}

	@Override
	public StandardRole addRoleToPlayer(
			StandardPlayer player, String roleName
	) {

		StandardRoleFactory<?> factory = factories.get(roleName);
		StandardRole role = factory.create(player);
		player.getRoles().add(role);
		return role;
	}

	@Override
	public Multiset<String> getAvailableRoles() {
		return availableRoles;
	}
}
