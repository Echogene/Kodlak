package standardgame.role.villager;

import model.player.Player;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.role.StandardRole;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;

/**
 * @author Steven Weston
 */
public abstract class AbstractVillagerRole extends StandardRole {

	public AbstractVillagerRole(Player owner) {
		super(owner);
	}

	@Override
	public VillagerWerewolfAlignment getAlignment() {
		return VILLAGER;
	}
}
