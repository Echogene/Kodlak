package standardgame.game;

import model.game.AbstractGame;
import model.message.MessageSenderFactory;
import model.message.PlayerMessageSender;
import model.player.Player;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.alignment.VillagerWerewolfAlignmentResolver;
import standardgame.phase.DayNightPhase;
import standardgame.player.StandardPlayer;
import standardgame.player.status.StandardStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steven Weston
 */
public class StandardGame<S extends PlayerMessageSender>
		extends AbstractGame<DayNightPhase, VillagerWerewolfAlignment, StandardStatus, StandardPlayer> {

	private final VillagerWerewolfAlignmentResolver resolver = new VillagerWerewolfAlignmentResolver();
	private final MessageSenderFactory<S> messageSenderFactory;
	private final Map<Player<?, ?, ?>, S> messageSenders = new HashMap<>();

	public StandardGame(MessageSenderFactory<S> messageSenderFactory) {
		this.messageSenderFactory = messageSenderFactory;
	}

	@Override
	public void addPlayer(String name) {

		StandardPlayer player = new StandardPlayer(
				name,
				resolver,
				new StandardStatus()
		);
		players.add(player);

		S messageSender = messageSenderFactory.create(player);
		messageSenders.put(player, messageSender);
	}
}
