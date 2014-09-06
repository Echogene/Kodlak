package standardgame.phase;

/**
 * @author Steven Weston
 */
public class PhaseStartEvent extends PhaseChangeEvent {

	protected PhaseStartEvent(DayNightPhase.Phase changedPhase) {
		super(changedPhase);
	}
}
