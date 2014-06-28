package model.effect;

/**
 * @author Steven Weston
 */
@FunctionalInterface
public interface Effect {

	void perform();

	static Effect doNothing() {
		return () -> {};
	}
}
