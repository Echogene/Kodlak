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
public class PhaseEndEvent extends PhaseChangeEvent {

	public static final PhaseEndEvent DAY_END = new PhaseEndEvent(DAY);
	public static final PhaseEndEvent NIGHT_END = new PhaseEndEvent(NIGHT);
	static final Map<Phase, PhaseEndEvent> MAP = MapUtils.map(
			new Pair<>(DAY, DAY_END),
			new Pair<>(NIGHT, NIGHT_END)
	);

	private PhaseEndEvent(Phase changedPhase) {
		super(changedPhase);
	}
}
