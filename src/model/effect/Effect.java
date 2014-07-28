package model.effect;

/**
 * @author Steven Weston
 */
@FunctionalInterface
public interface Effect {

	void perform() throws Exception;

	static Effect doNothing() {
		return () -> {};
	}
}
