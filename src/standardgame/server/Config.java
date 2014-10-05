package standardgame.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import standardgame.role.RoleDao;
import standardgame.role.villager.SeerFactory;
import standardgame.role.villager.VillagerFactory;
import standardgame.role.werewolf.WerewolfFactory;

import javax.annotation.PostConstruct;

/**
 * @author Steven Weston
 */
@Configuration
public class Config {

	@Autowired private RoleDao roleDao;
	@Autowired private WerewolfFactory werewolfFactory;
	@Autowired private SeerFactory seerFactory;
	@Autowired private VillagerFactory villagerFactory;

	@PostConstruct
	public void populateFactories() {
		// Add more factories here:
		roleDao.addRoleFactory(werewolfFactory);
		roleDao.addRoleFactory(villagerFactory);
		roleDao.addRoleFactory(seerFactory);
	}
}
