package standardgame.role.villager;

import standardgame.alignment.VillagerWerewolfAlignment;
import model.effect.Effect;
import standardgame.phase.DayNightPhase;
import model.player.Player;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;

/**
 * @author Steven Weston
 */
public class Villager extends AbstractVillagerRole {

	public Villager(Player owner) {
		super(owner);
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
