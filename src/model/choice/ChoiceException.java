package model.choice;

/**
 * @author Steven Weston
 */
public class ChoiceException extends Exception {

	public ChoiceException(String message) {
		super(message);
	}

	public ChoiceException(Exception e) {
		super(e);
	}
}
