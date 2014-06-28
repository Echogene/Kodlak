package standardgame.role.werewolf;

import standardgame.alignment.VillagerWerewolfAlignment;
import model.choice.group.GroupChoice;
import model.effect.Effect;
import standardgame.phase.DayNightPhase;
import model.player.Player;
import model.role.AbstractRole;

import java.util.List;
import java.util.Set;

import static standardgame.alignment.VillagerWerewolfAlignment.WEREWOLF;

/**
 * @author Steven Weston
 */
public class Werewolf extends AbstractRole<DayNightPhase, VillagerWerewolfAlignment> {

	private final Set<Player> werewolves;
	private final List<Player> players;

	public Werewolf(Player owner, Set<Player> werewolves, List<Player> players) {
		super(owner);
		this.werewolves = werewolves;
		this.players = players;
	}

	@Override
	public Effect getEffect(DayNightPhase phase) {
		switch (phase) {
			case NIGHT:
				return this::nightAction;
			default:
				return Effect.doNothing();
		}
	}

	private void nightAction() {

		GroupChoice<Player> playerChoice = new GroupChoice<>(werewolves, players);
		Player victim = playerChoice.getChoice();
		victim.kill();
	}

	@Override
	public VillagerWerewolfAlignment getAlignment() {
		return WEREWOLF;
	}

	@Override
	public VillagerWerewolfAlignment getVisibleAlignment() {
		return WEREWOLF;
	}
}
