package server.components.dao.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.components.dao.Identifier;
import server.components.messagesender.LoggedMessageSender;
import server.components.messagesender.LoggedMessageSenderFactory;
import standardgame.player.StandardPlayer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Steven Weston
 */
@Component
public class StandardPlayerDao implements PlayerDao<StandardPlayer, StandardPlayerBuilder.PlayerBuilderRequiringName> {

	private final Set<StandardPlayer> players;
	private final Identifier identifier;

	// todo: move these to a LoggedMessageSenderDao
	private final LoggedMessageSenderFactory messageSenderFactory;
	private final Map<StandardPlayer, LoggedMessageSender> messageSenders = new HashMap<>();

	@Autowired
	public StandardPlayerDao(Identifier identifier, LoggedMessageSenderFactory messageSenderFactory) {

		this.messageSenderFactory = messageSenderFactory;
		this.players = new HashSet<>();
		this.identifier = identifier;
	}

	@Override
	public StandardPlayer getById(long id) {

		// todo: use a map
		for (StandardPlayer player : players) {
			if (id == player.getId()) {
				return player;
			}
		}
		return null;
	}

	@Override
	public Set<StandardPlayer> getAll() {
		return players;
	}

	@Override
	public void delete(long id) {
		players.remove(getById(id));
	}

	@Override
	public StandardPlayerBuilder.PlayerBuilderRequiringName create() {
		return new StandardPlayerBuilder(
				identifier.getNewId(),
				(player) -> {
					players.add(player);

					LoggedMessageSender messageSender = messageSenderFactory.create(player);
					messageSenders.put(player, messageSender);
				}
		).build();
	}
}
