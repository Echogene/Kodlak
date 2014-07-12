package standardgame.role.villager;

import standardgame.player.StandardPlayer;
import standardgame.role.StandardRoleFactory;

/**
 * @author Steven Weston
 */
public class VillagerFactory extends StandardRoleFactory<Villager> {

	public static final String VILLAGER = "villager";

	@Override
	public String getRoleName() {
		return VILLAGER;
	}

	@Override
	public Villager create(StandardPlayer player) {
		return new Villager(player);
	}
}
