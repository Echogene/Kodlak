package standardgame.role.villager;

import model.alignment.Alignment;
import model.choice.single.SingleChoice;
import model.choice.single.SingleChoiceFactory;
import model.effect.Effect;
import model.message.Message;
import model.message.MessageSender;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.player.StandardPlayer;

import java.text.MessageFormat;
import java.util.Collection;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;

/**
 * @author Steven Weston
 */
public class Seer extends AbstractVillagerRole {

	private final Collection<StandardPlayer> players;

	private final SingleChoiceFactory<
			StandardPlayer,
			StandardPlayer,
			? extends SingleChoice<StandardPlayer, StandardPlayer>
	> choiceFactory;

	private final MessageSender messageSender;

	public Seer(
			StandardPlayer owner,
			Collection<StandardPlayer> players,
			SingleChoiceFactory<
					StandardPlayer,
					StandardPlayer,
					? extends SingleChoice<StandardPlayer, StandardPlayer>
			> choiceFactory,
			MessageSender messageSender
	) {
		super(owner);
		this.players = players;
		this.choiceFactory = choiceFactory;
		this.messageSender = messageSender;
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
		SingleChoice<StandardPlayer, StandardPlayer> playerChoice = choiceFactory.create(owner, players);
		StandardPlayer target = playerChoice.getChoice();
		revealAlignmentOf(target);
	}

	private void revealAlignmentOf(StandardPlayer target) {

		Alignment alignment = target.getVisibleAlignment();
		messageSender.send(new SeerMessage(target, alignment));
	}

	@Override
	public VillagerWerewolfAlignment getVisibleAlignment() {
		return VILLAGER;
	}

	private static class SeerMessage implements Message {

		private final StandardPlayer target;
		private final Alignment alignment;

		private SeerMessage(StandardPlayer target, Alignment alignment) {
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
