package standardgame.role.villager;

import model.effect.Effect;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.player.StandardPlayer;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;

/**
 * @author Steven Weston
 */
public class Villager extends AbstractVillagerRole {

	public Villager(long id, StandardPlayer owner) {
		super(id, owner);
	}

	@Override
	public Effect getEffect(DayNightPhase phase) {
		return Effect.doNothing();
	}

	@Override
	public VillagerWerewolfAlignment getVisibleAlignment() {
		return VILLAGER;
	}
}
