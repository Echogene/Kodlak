package server.components.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standardgame.game.StandardGame;
import standardgame.player.StandardPlayer;

import java.util.Set;

/**
 * @author Steven Weston
 */
@Component
public class StandardPlayerDao implements PlayerDao<StandardPlayer> {

	private final Set<StandardPlayer> players;

	@Autowired
	public StandardPlayerDao(StandardGame game) {
		this.players = game.getPlayers();
	}

	@Override
	public StandardPlayer getById(String id) {

		for (StandardPlayer player : players) {
			if (id.equals(player.getId())) {
				return player;
			}
		}
		return null;
	}

	@Override
	public void delete(String id) {

		players.remove(getById(id));
	}
}
