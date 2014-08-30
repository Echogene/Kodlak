package model.choice;

import model.player.Player;
import server.components.dao.Identifiable;

import java.util.Set;

/**
 * @author Steven Weston
 */
public interface Choice<P extends Player, T> extends Identifiable {

	public Set<P> getChoosers();

	public T getChoice() throws ChoiceException;

	boolean hasChosen();
}
