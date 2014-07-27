package standardgame.role;

import model.role.AbstractRole;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.player.StandardPlayer;

/**
 * @author Steven Weston
 */
public abstract class StandardRole extends AbstractRole<DayNightPhase, VillagerWerewolfAlignment, StandardPlayer> {

	public StandardRole(StandardPlayer owner) {
		super(owner);
	}
}
