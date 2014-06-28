package standardgame.role.villager;

import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import model.player.Player;
import model.role.AbstractRole;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;

/**
 * @author Steven Weston
 */
public abstract class AbstractVillagerRole extends AbstractRole<DayNightPhase, VillagerWerewolfAlignment> {

	public AbstractVillagerRole(Player owner) {
		super(owner);
	}

	@Override
	public VillagerWerewolfAlignment getAlignment() {
		return VILLAGER;
	}
}
