package standardgame.game;

import model.game.AbstractGame;
import org.springframework.beans.factory.annotation.Autowired;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.phase.PhaseChangeEvent;
import standardgame.player.StandardPlayer;
import standardgame.player.status.StandardStatus;
import standardgame.role.StandardRole;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static standardgame.phase.DayNightPhase.Phase.NIGHT;
import static standardgame.phase.PhaseStartEvent.NIGHT_START;

/**
 * @author Steven Weston
 */
public class StandardGame
		extends AbstractGame<DayNightPhase, VillagerWerewolfAlignment, StandardStatus, StandardRole, StandardPlayer> {

	@Autowired
	public StandardGame() {
		super(new DayNightPhase(NIGHT));
		currentPhase.observe(NIGHT_START, this::onNightStart);
	}

	private void onNightStart(PhaseChangeEvent phaseChangeEvent) {
		throw new NotImplementedException();
	}
}
