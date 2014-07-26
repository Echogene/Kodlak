package standardgame.phase;

import model.phase.Phase;

/**
 * @author Steven Weston
 */
public class DayNightPhase implements Phase {

	private Phase phase;

	public DayNightPhase(Phase phase) {
		this.phase = phase;
	}

	@Override
	public void advance() {

		Phase newPhase;
		if (Phase.NIGHT == phase) {
			newPhase = Phase.DAY;
		} else {
			newPhase = Phase.NIGHT;
		}
		phase = newPhase;
	}

	public static enum Phase {DAY, NIGHT}
}
