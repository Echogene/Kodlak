package standardgame.role.villager;

import model.message.MessageSender;
import server.components.dao.choice.StandardSinglePlayerChoiceFactory;
import standardgame.game.StandardGame;
import standardgame.player.StandardPlayer;
import standardgame.role.StandardRoleFactory;

import java.util.Set;

/**
 * @author Steven Weston
 */
public class SeerFactory extends StandardRoleFactory<Seer> {

	public static final String SEER = "seer";
	private final Set<StandardPlayer> players;
	private final StandardSinglePlayerChoiceFactory choiceFactory;
	private final StandardGame game;

	public SeerFactory(
			Set<StandardPlayer> players,
			StandardSinglePlayerChoiceFactory choiceFactory,
			StandardGame game
	) {
		this.players = players;
		this.choiceFactory = choiceFactory;
		this.game = game;
	}

	@Override
	public String getRoleName() {
		return SEER;
	}

	@Override
	public Seer create(StandardPlayer StandardPlayer) {
		MessageSender messageSender = game.getMessageSender(StandardPlayer);
		return new Seer(StandardPlayer, players, choiceFactory, messageSender);
	}
}
