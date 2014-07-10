package standardgame.role;

import model.player.Player;
import model.role.AbstractRole;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;

/**
 * @author Steven Weston
 */
public abstract class StandardRole extends AbstractRole<DayNightPhase, VillagerWerewolfAlignment> {

	public StandardRole(Player owner) {
		super(owner);
	}
}
