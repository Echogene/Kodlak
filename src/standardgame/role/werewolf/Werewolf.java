package standardgame.role.werewolf;

import model.choice.group.GroupChoice;
import model.choice.group.GroupChoiceFactory;
import model.effect.Effect;
import model.player.Player;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.player.StandardPlayer;
import standardgame.role.StandardRole;

import java.util.Set;

import static standardgame.alignment.VillagerWerewolfAlignment.WEREWOLF;

/**
 * @author Steven Weston
 */
public class Werewolf extends StandardRole {

	private final Set<StandardPlayer> werewolves;
	private final Set<StandardPlayer> players;

	private final GroupChoiceFactory<
			StandardPlayer,
			StandardPlayer,
			? extends GroupChoice<StandardPlayer, StandardPlayer>
	> choiceFactory;

	public Werewolf(
			long id,
			StandardPlayer owner,
			Set<StandardPlayer> werewolves,
			Set<StandardPlayer> players,
			GroupChoiceFactory<
					StandardPlayer,
					StandardPlayer,
					? extends GroupChoice<StandardPlayer, StandardPlayer>
			> choiceFactory
	) {
		super(id, owner);
		this.werewolves = werewolves;
		this.players = players;
		this.choiceFactory = choiceFactory;
	}

	@Override
	public Effect getEffect(DayNightPhase phase) {
		switch (phase.getPhase()) {
			case NIGHT:
				return this::nightAction;
			default:
				return Effect.doNothing();
		}
	}

	private void nightAction() {

		GroupChoice<StandardPlayer, StandardPlayer> playerChoice = choiceFactory.create(werewolves, players);
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
