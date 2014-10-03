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
import standardgame.role.RoleComparator;
import standardgame.role.RoleDao;
import standardgame.role.StandardRole;
import standardgame.server.components.messagesender.SystemMessageSender;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

import static standardgame.phase.DayNightPhase.Phase.NIGHT;
import static standardgame.phase.PhaseStartEvent.NIGHT_START;
import static util.CollectionUtils.asSortedList;

/**
 * @author Steven Weston
 */
@Component
public class StandardGame
		extends AbstractGame<DayNightPhase, VillagerWerewolfAlignment, StandardStatus, StandardRole, StandardPlayer> {

	private final SystemMessageSender messageSender;
	private final RoleDao roleDao;
	private final RoleComparator roleComparator;

	@Autowired
	public StandardGame(SystemMessageSender messageSender, RoleDao roleDao, RoleComparator roleComparator) {
		super(new DayNightPhase(NIGHT));

		this.messageSender = messageSender;
		this.roleDao = roleDao;
		this.roleComparator = roleComparator;

		currentPhase.observe(NIGHT_START, this::onNightStart);
	}

	private void onNightStart(PhaseChangeEvent phaseChangeEvent) {
		messageSender.send(new SystemMessage("Night has begun."));

		List<StandardRole> rolesInPriorityOrder = asSortedList(roleDao.getAll(), roleComparator);

		for (StandardRole role : rolesInPriorityOrder) {
			try {
				role.getEffect(currentPhase).perform();
			} catch (Exception e) {
				// todo: think about what should happen here
				e.printStackTrace();
				throw new NotImplementedException();
			}
		}
	}
}
