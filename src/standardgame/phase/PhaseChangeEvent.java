package standardgame.phase;

import util.event.Event;

import static standardgame.phase.DayNightPhase.Phase;

/**
 * @author Steven Weston
 */
public abstract class PhaseChangeEvent implements Event {

	private final Phase changedPhase;

	protected PhaseChangeEvent(Phase changedPhase) {
		this.changedPhase = changedPhase;
	}

	public Phase getChangedPhase() {
		return changedPhase;
	}
}
