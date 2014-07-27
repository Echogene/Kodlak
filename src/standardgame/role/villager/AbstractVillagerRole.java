package standardgame.role.villager;

import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.player.StandardPlayer;
import standardgame.role.StandardRole;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;

/**
 * @author Steven Weston
 */
public abstract class AbstractVillagerRole extends StandardRole {

	public AbstractVillagerRole(StandardPlayer owner) {
		super(owner);
	}

	@Override
	public VillagerWerewolfAlignment getAlignment() {
		return VILLAGER;
	}
}
