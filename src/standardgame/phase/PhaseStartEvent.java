package standardgame.phase;

import javafx.util.Pair;
import util.MapUtils;

import java.util.Map;

import static standardgame.phase.DayNightPhase.Phase;
import static standardgame.phase.DayNightPhase.Phase.DAY;
import static standardgame.phase.DayNightPhase.Phase.NIGHT;

/**
 * @author Steven Weston
 */
public class PhaseStartEvent extends PhaseChangeEvent {

	public static final PhaseStartEvent DAY_START = new PhaseStartEvent(DAY);
	public static final PhaseStartEvent NIGHT_START = new PhaseStartEvent(NIGHT);
	static final Map<Phase, PhaseStartEvent> MAP = MapUtils.map(
			new Pair<>(DAY, DAY_START),
			new Pair<>(NIGHT, NIGHT_START)
	);

	private PhaseStartEvent(Phase changedPhase) {
		super(changedPhase);
	}
}
