package standardgame.phase;

/**
 * @author Steven Weston
 */
public class PhaseEndEvent extends PhaseChangeEvent {

	protected PhaseEndEvent(DayNightPhase.Phase changedPhase) {
		super(changedPhase);
	}
}
