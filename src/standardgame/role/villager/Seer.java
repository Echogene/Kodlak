package standardgame.role.villager;

import model.alignment.Alignment;
import standardgame.alignment.VillagerWerewolfAlignment;
import model.choice.Choice;
import model.choice.single.SingleChoice;
import model.choice.single.SingleChoiceFactory;
import model.effect.Effect;
import standardgame.phase.DayNightPhase;
import model.player.Player;

import java.text.MessageFormat;
import java.util.List;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;

/**
 * @author Steven Weston
 */
public class Seer extends AbstractVillagerRole {

	private final List<Player> players;
	private final SingleChoiceFactory<Player, ? extends SingleChoice<Player>> choiceFactory;

	public Seer(
			Player owner,
			List<Player> players,
			SingleChoiceFactory<Player, ? extends SingleChoice<Player>> choiceFactory
	) {
		super(owner);
		this.players = players;
		this.choiceFactory = choiceFactory;
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
		Choice<Player> playerChoice = choiceFactory.create(owner, players);
		Player target = playerChoice.getChoice();
		revealAlignment(target, owner);
	}

	private void revealAlignment(Player target, Player seer) {

		Alignment alignment = target.getVisibleAlignment();
		seer.sendMessage(
				MessageFormat.format(
						"{0} is {1} {2}.",
						target.getName(),
						alignment.getIndefiniteArticle(),
						alignment.toString()
				)
		);
	}

	@Override
	public VillagerWerewolfAlignment getVisibleAlignment() {
		return VILLAGER;
	}
}
