package standardgame.game;

import model.game.AbstractGame;
import model.message.MessageSender;
import server.components.messagesender.LoggedMessageSender;
import server.components.messagesender.LoggedMessageSenderFactory;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.alignment.VillagerWerewolfAlignmentResolver;
import standardgame.phase.DayNightPhase;
import standardgame.player.StandardPlayer;
import standardgame.player.status.StandardStatus;
import standardgame.role.StandardRole;

import java.util.HashMap;
import java.util.Map;

import static standardgame.phase.DayNightPhase.Phase.NIGHT;

/**
 * @author Steven Weston
 */
public class StandardGame
		extends AbstractGame<DayNightPhase, VillagerWerewolfAlignment, StandardStatus, StandardRole, StandardPlayer> {

	private final VillagerWerewolfAlignmentResolver resolver = new VillagerWerewolfAlignmentResolver();
	private final LoggedMessageSenderFactory messageSenderFactory;
	private final Map<StandardPlayer, LoggedMessageSender> messageSenders = new HashMap<>();

	private int currentId = 0;

	public StandardGame(LoggedMessageSenderFactory messageSenderFactory) {

		super(new DayNightPhase(NIGHT));
		this.messageSenderFactory = messageSenderFactory;
	}

	@Override
	public StandardPlayer addPlayer(String name) {

		StandardPlayer player = new StandardPlayer(
				name,
				resolver,
				new StandardStatus(),
				Integer.toString(currentId++)
		);
		players.add(player);

		LoggedMessageSender messageSender = messageSenderFactory.create(player);
		messageSenders.put(player, messageSender);
		return player;
	}

	public MessageSender getMessageSender(StandardPlayer player) {
		return messageSenders.get(player);
	}
}
