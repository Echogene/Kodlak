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
public abstract class AbstractGame<P extends Phase, A extends Alignment, S extends Status> implements Game<P, A, S> {

	/**
	 * The set of players in the game.
	 */
	protected final Set<Player<P, A, S>> players = new HashSet<>();

	/**
	 * A list of roles in order of priority.
	 */
	protected final List<Role> roles = new ArrayList<>();


	public abstract void addPlayer(String name);

	@Override
	public Set<Player<P, A, S>> getPlayers() {
		return players;
	}
}
