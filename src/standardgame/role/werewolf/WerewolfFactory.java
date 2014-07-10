package standardgame.role.werewolf;

import standardgame.role.StandardRoleFactory;
import standardgame.player.StandardPlayer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Steven Weston
 */
public class WerewolfFactory extends StandardRoleFactory<Werewolf> {

	public static final String WEREWOLF = "werewolf";
	private final Set<StandardPlayer> werewolves = new HashSet<>();
	private final Set<StandardPlayer> players;

	public WerewolfFactory(Set<StandardPlayer> players) {

		this.players = players;
	}

	@Override
	public String getRoleName() {
		return WEREWOLF;
	}

	@Override
	public Werewolf create(StandardPlayer player) {

		werewolves.add(player);
		return new Werewolf(player, werewolves, players);
	}
}
