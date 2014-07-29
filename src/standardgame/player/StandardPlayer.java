package standardgame.player;

import model.alignment.AlignmentResolver;
import model.player.AbstractPlayer;
import server.components.dao.Identifiable;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.player.status.StandardStatus;
import standardgame.role.StandardRole;

/**
 * @author Steven Weston
 */
public class StandardPlayer
		extends AbstractPlayer<DayNightPhase, VillagerWerewolfAlignment, StandardStatus, StandardRole, StandardPlayer>
		implements Identifiable {

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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}
}
