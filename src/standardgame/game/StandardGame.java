package standardgame.game;

import model.game.AbstractGame;
import model.message.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.phase.PhaseChangeEvent;
import standardgame.player.StandardPlayer;
import standardgame.player.status.StandardStatus;
import standardgame.role.StandardRole;
import standardgame.server.components.messagesender.MessageLog;
import standardgame.server.components.messagesender.SystemMessageSender;

import java.text.MessageFormat;

import static standardgame.phase.DayNightPhase.Phase.NIGHT;
import static standardgame.phase.PhaseStartEvent.NIGHT_START;

/**
 * @author Steven Weston
 */
@Component
public class StandardGame
		extends AbstractGame<DayNightPhase, VillagerWerewolfAlignment, StandardStatus, StandardRole, StandardPlayer> {

	private final SystemMessageSender messageSender;

	@Autowired
	public StandardGame(SystemMessageSender messageSender) {
		super(new DayNightPhase(NIGHT));
		this.messageSender = messageSender;
		currentPhase.observe(NIGHT_START, this::onNightStart);
	}

	private void onNightStart(PhaseChangeEvent phaseChangeEvent) {
		messageSender.send(new SystemMessage("Night has begun."));
	}
}
