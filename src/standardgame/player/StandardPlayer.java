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

	private final String id;
	private double top;
	private double left;

	public StandardPlayer(
			String name,
			AlignmentResolver<VillagerWerewolfAlignment> resolver,
			StandardStatus startingStatus,
			String id
	) {
		super(name, resolver, startingStatus);
		this.id = id;
	}

	public double getTop() {
		return top;
	}

	public double getLeft() {
		return left;
	}

	public void setTop(double top) {
		this.top = top;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public String getId() {
		return id;
	}
}
