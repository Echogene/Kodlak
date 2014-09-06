package standardgame.phase;

import model.phase.Phase;
import util.event.Observable;

/**
 * @author Steven Weston
 */
public class DayNightPhase extends Observable<PhaseChangeEvent> implements Phase {

	private Phase phase;

	public DayNightPhase(Phase phase) {
		this.phase = phase;
	}

	public Phase getPhase() {
		return phase;
	}

	@Override
	public void advance() {

		fireEvent(new PhaseEndEvent(phase));
		Phase newPhase;
		if (Phase.NIGHT == phase) {
			newPhase = Phase.DAY;
		} else {
			newPhase = Phase.NIGHT;
		}
		phase = newPhase;
		fireEvent(new PhaseStartEvent(phase));
	}

	public static enum Phase {DAY, NIGHT}
}
