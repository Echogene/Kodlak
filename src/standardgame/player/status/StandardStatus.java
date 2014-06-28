package standardgame.player.status;

import static standardgame.player.status.BasicStatus.*;

/**
 * @author Steven Weston
 */
public class StandardStatus implements model.player.status.Status {

	private BasicStatus status;

	public StandardStatus() {
		this(ALIVE);
	}

	private StandardStatus(BasicStatus status) {
		this.status = status;
	}

	@Override
	public boolean isAlive() {
		return ALIVE == status;
	}

	@Override
	public boolean isDying() {
		return DYING == status;
	}

	@Override
	public boolean isDead() {
		return DEAD == status;
	}

	@Override
	public void kill() {
		if (isDead()) {
			throw new IllegalStateException("Cannot kill something that is dead");
		}
		status = DYING;
	}

	@Override
	public void die() {
		if (!isDying()) {
			throw new IllegalStateException("Must be dying to die");
		}
		status = DEAD;
	}

	@Override
	public void stopDying() {
		if (!isDying()) {
			throw new IllegalStateException("Must be dying to stop dying");
		}
		status = ALIVE;
	}
}
