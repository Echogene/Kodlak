package standardgame.role;

import model.role.RoleFactory;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.phase.DayNightPhase;
import standardgame.player.StandardPlayer;
import standardgame.server.components.dao.Identifier;

/**
 * @author Steven Weston
 */
public abstract class StandardRoleFactory<
		R extends StandardRole
> implements RoleFactory<DayNightPhase, VillagerWerewolfAlignment, R, StandardPlayer> {

	protected final Identifier identifier;

	protected StandardRoleFactory(Identifier identifier) {
		this.identifier = identifier;
	}
}
