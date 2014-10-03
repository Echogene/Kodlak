package standardgame.role.villager;

import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.player.StandardPlayer;
import standardgame.role.StandardRole;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;

/**
 * @author Steven Weston
 */
public abstract class AbstractVillagerRole extends StandardRole {

	protected AbstractVillagerRole(long id, StandardPlayer owner) {
		super(id, owner);
	}

	@Override
	public VillagerWerewolfAlignment getAlignment() {
		return VILLAGER;
	}
}
