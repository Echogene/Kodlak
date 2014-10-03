package standardgame.role.werewolf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standardgame.player.StandardPlayer;
import standardgame.role.StandardRoleFactory;
import standardgame.server.components.dao.Identifier;
import standardgame.server.components.dao.choice.StandardGroupPlayerChoiceFactory;
import standardgame.server.components.dao.player.StandardPlayerDao;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Steven Weston
 */
@Component
public class WerewolfFactory extends StandardRoleFactory<Werewolf> {

	public static final String WEREWOLF = "werewolf";
	private final Set<StandardPlayer> werewolves = new HashSet<>();
	private final StandardPlayerDao playerDao;
	private final StandardGroupPlayerChoiceFactory choiceFactory;

	@Autowired
	public WerewolfFactory(
			Identifier identifier,
			StandardPlayerDao playerDao,
			StandardGroupPlayerChoiceFactory choiceFactory
	) {

		super(identifier);
		this.playerDao = playerDao;
		this.choiceFactory = choiceFactory;
	}

	@Override
	public String getRoleName() {
		return WEREWOLF;
	}

	@Override
	public Werewolf create(StandardPlayer player) {

		werewolves.add(player);
		return new Werewolf(identifier.getNewId(), player, werewolves, playerDao.getAll(), choiceFactory);
	}
}
