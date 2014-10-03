package standardgame.role;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import model.role.RoleAssigner;
import model.role.UnavailableRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.player.StandardPlayer;

/**
 * @author Steven Weston
 */
public class StandardRoleAssigner
		implements RoleAssigner<DayNightPhase, VillagerWerewolfAlignment, StandardRole, StandardPlayer> {

	private final RoleDao roleDao;

	private final Multiset<String> availableRoles = HashMultiset.create();

	@Autowired
	public StandardRoleAssigner(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void addRoleFactory(StandardRoleFactory<?> factory) {
		// todo: move this method
		roleDao.addRoleFactory(factory);
	}

	@Override
	public StandardRole addRoleToPlayer(
			StandardPlayer player, String roleName
	) throws UnavailableRoleException {

		if (!availableRoles.contains(roleName)) {
			throw new UnavailableRoleException();
		}
		availableRoles.remove(roleName);
		return roleDao.create().create(roleName, player);
	}

	@Override
	public Multiset<String> getAvailableRoles() {
		return availableRoles;
	}

	@Override
	public boolean isRoleSupported(String roleName) {
		// todo: move this method and fix it
		return true;
	}
}
