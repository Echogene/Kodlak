package standardgame.server.components.dao.player;

import model.alignment.AlignmentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.server.components.dao.Identifier;
import standardgame.server.components.messagesender.PlayerMessageSender;
import standardgame.server.components.messagesender.LoggedMessageSenderFactory;
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
	private final AlignmentResolver<VillagerWerewolfAlignment> resolver;

	// todo: move these to a LoggedMessageSenderDao
	private final LoggedMessageSenderFactory messageSenderFactory;
	private final Map<StandardPlayer, PlayerMessageSender> messageSenders = new HashMap<>();

	@Autowired
	public StandardPlayerDao(
			Identifier identifier,
			AlignmentResolver<VillagerWerewolfAlignment> resolver,
			LoggedMessageSenderFactory messageSenderFactory
	) {

		this.resolver = resolver;
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
				resolver,
				(player) -> {
					players.add(player);

					PlayerMessageSender messageSender = messageSenderFactory.create(player);
					messageSenders.put(player, messageSender);
				}
		).build();
	}
}
