package model.player.status;

/**
 * @author Steven Weston
 */
public interface Status {

	boolean isAlive();

	boolean isDying();

	boolean isDead();

	void kill();

	void die();

	void stopDying();
}
