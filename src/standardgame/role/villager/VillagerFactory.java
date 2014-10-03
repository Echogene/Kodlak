package standardgame.role.villager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import standardgame.player.StandardPlayer;
import standardgame.role.StandardRoleFactory;
import standardgame.server.components.dao.Identifier;

/**
 * @author Steven Weston
 */
@Component
public class VillagerFactory extends StandardRoleFactory<Villager> {

	public static final String VILLAGER = "villager";

	@Autowired
	public VillagerFactory(Identifier identifier) {

		super(identifier);
	}

	@Override
	public String getRoleName() {
		return VILLAGER;
	}

	@Override
	public Villager create(StandardPlayer player) {
		return new Villager(identifier.getNewId(), player);
	}
}
