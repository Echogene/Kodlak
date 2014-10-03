package standardgame.role.villager;

import model.message.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standardgame.game.StandardGame;
import standardgame.player.StandardPlayer;
import standardgame.role.StandardRoleFactory;
import standardgame.server.components.dao.Identifier;
import standardgame.server.components.dao.choice.StandardSinglePlayerChoiceFactory;
import standardgame.server.components.dao.player.StandardPlayerDao;

/**
 * @author Steven Weston
 */
@Component
public class SeerFactory extends StandardRoleFactory<Seer> {

	public static final String SEER = "seer";
	private final StandardPlayerDao playerDao;
	private final StandardSinglePlayerChoiceFactory choiceFactory;
	private final StandardGame game;

	@Autowired
	public SeerFactory(
			Identifier identifier,
			StandardPlayerDao playerDao,
			StandardSinglePlayerChoiceFactory choiceFactory,
			StandardGame game
	) {

		super(identifier);
		this.playerDao = playerDao;
		this.choiceFactory = choiceFactory;
		this.game = game;
	}

	@Override
	public String getRoleName() {
		return SEER;
	}

	@Override
	public Seer create(StandardPlayer StandardPlayer) {
		MessageSender messageSender = null; //todo: make a message sender
		return new Seer(identifier.getNewId(), StandardPlayer, playerDao.getAll(), choiceFactory, messageSender);
	}
}
