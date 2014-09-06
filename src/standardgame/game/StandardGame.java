package standardgame.game;

import model.game.AbstractGame;
import org.springframework.beans.factory.annotation.Autowired;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.player.StandardPlayer;
import standardgame.player.status.StandardStatus;
import standardgame.role.StandardRole;

import static standardgame.phase.DayNightPhase.Phase.NIGHT;

/**
 * @author Steven Weston
 */
public class StandardGame
		extends AbstractGame<DayNightPhase, VillagerWerewolfAlignment, StandardStatus, StandardRole, StandardPlayer> {

	@Autowired
	public StandardGame() {
		super(new DayNightPhase(NIGHT));
	}
}
