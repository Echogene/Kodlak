package model.choice;

import model.player.Player;

import java.util.Set;

/**
 * @author Steven Weston
 */
public interface Choice<P extends Player, T> {

	public Set<P> getChoosers();

	public T getChoice();
}
