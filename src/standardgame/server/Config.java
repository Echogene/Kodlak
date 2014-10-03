package standardgame.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import standardgame.choice.ChoiceLock;
import standardgame.role.RoleDao;
import standardgame.role.StandardRoleAssigner;
import standardgame.role.villager.SeerFactory;
import standardgame.role.villager.VillagerFactory;
import standardgame.role.werewolf.WerewolfFactory;
import standardgame.server.components.messagesender.LoggedMessageSenderFactory;
import standardgame.server.components.messagesender.MessageLog;
import standardgame.server.components.messagesender.SystemMessageSender;

/**
 * @author Steven Weston
 */
@Configuration
public class Config {

	@Autowired private RoleDao roleDao;
	@Autowired private WerewolfFactory werewolfFactory;
	@Autowired private SeerFactory seerFactory;
	@Autowired private VillagerFactory villagerFactory;

	@Bean
	public StandardRoleAssigner getRoleFactory() {

		StandardRoleAssigner assigner = new StandardRoleAssigner(roleDao);

		// Add more factories here:
		assigner.addRoleFactory(werewolfFactory);
		assigner.addRoleFactory(villagerFactory);
		assigner.addRoleFactory(seerFactory);

		return assigner;
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
