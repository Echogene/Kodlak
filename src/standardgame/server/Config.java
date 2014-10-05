package standardgame.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import standardgame.choice.ChoiceLock;
import standardgame.role.RoleDao;
import standardgame.role.villager.SeerFactory;
import standardgame.role.villager.VillagerFactory;
import standardgame.role.werewolf.WerewolfFactory;
import standardgame.server.components.messagesender.LoggedMessageSenderFactory;
import standardgame.server.components.messagesender.MessageLog;
import standardgame.server.components.messagesender.SystemMessageSender;

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

	@Bean
	public LoggedMessageSenderFactory getMessageFactory() {
		return new LoggedMessageSenderFactory(getMessageLog());
	}

	@Bean
	public MessageLog getMessageLog() {
		return new MessageLog();
	}

	@Bean
	public ChoiceLock getChoiceLock() {
		return new ChoiceLock();
	}

	@Bean
	public SystemMessageSender getSystemMessageSender() {
		return new SystemMessageSender(getMessageLog());
	}
}
