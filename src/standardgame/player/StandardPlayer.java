package standardgame.player;

import model.alignment.AlignmentResolver;
import model.player.AbstractPlayer;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.player.status.StandardStatus;

/**
 * @author Steven Weston
 */
public class StandardPlayer extends AbstractPlayer<DayNightPhase,VillagerWerewolfAlignment,StandardStatus> {

	public StandardPlayer(
			String name,
			AlignmentResolver<VillagerWerewolfAlignment> resolver,
			StandardStatus startingStatus
	) {
		super(name, resolver, startingStatus);
	}
}
