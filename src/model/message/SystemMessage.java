package model.message;

/**
 * @author Steven Weston
 */
public class SystemMessage implements Message {

	private final String string;

	public SystemMessage(String string) {
		this.string = string;
	}

	@Override
	public String getMessage() {
		return null;
	}

	@Override
	public String getSystemMessage() {
		return string;
	}
}
