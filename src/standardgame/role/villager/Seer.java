package standardgame.role.villager;

import model.alignment.Alignment;
import model.choice.Choice;
import model.choice.single.SingleChoice;
import model.choice.single.SingleChoiceFactory;
import model.effect.Effect;
import model.message.Message;
import model.message.MessageSender;
import model.player.Player;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;

import java.text.MessageFormat;
import java.util.List;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;

/**
 * @author Steven Weston
 */
public class Seer extends AbstractVillagerRole {

	private final List<Player> players;
	private final SingleChoiceFactory<Player, ? extends SingleChoice<Player>> choiceFactory;
	private final MessageSender messageSender;

	public Seer(
			Player owner,
			List<Player> players,
			SingleChoiceFactory<Player, ? extends SingleChoice<Player>> choiceFactory,
			MessageSender messageSender
	) {
		super(owner);
		this.players = players;
		this.choiceFactory = choiceFactory;
		this.messageSender = messageSender;
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
		revealAlignmentOf(target);
	}

	private void revealAlignmentOf(Player target) {

		Alignment alignment = target.getVisibleAlignment();
		messageSender.send(new SeerMessage(target, alignment));
	}

	@Override
	public VillagerWerewolfAlignment getVisibleAlignment() {
		return VILLAGER;
	}

	private static class SeerMessage implements Message {

		private final Player target;
		private final Alignment alignment;

		private SeerMessage(Player target, Alignment alignment) {
			this.target = target;
			this.alignment = alignment;
		}

		@Override
		public String getMessage() {

			return MessageFormat.format(
					"{0} is {1} {2}.",
					target.getName(),
					alignment.getIndefiniteArticle(),
					alignment.toString()
			);
		}
	}
}
