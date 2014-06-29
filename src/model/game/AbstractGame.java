package model.game;

import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;
import model.player.status.Status;
import model.role.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Steven Weston
 */
public abstract class AbstractGame<P extends Phase, A extends Alignment, S extends Status, Y extends Player<P, A, S>>
		implements Game<P, A, S, Y> {

	/**
	 * The set of players in the game.
	 */
	protected final Set<Y> players = new HashSet<>();

	/**
	 * A list of roles in order of priority.
	 */
	protected final List<Role> roles = new ArrayList<>();


	public abstract Y addPlayer(String name);

	@Override
	public Set<Y> getPlayers() {
		return players;
	}
}
