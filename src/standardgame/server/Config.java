package standardgame.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import standardgame.server.components.dao.choice.StandardSinglePlayerChoiceFactory;
import standardgame.server.components.dao.player.StandardPlayerDao;
import standardgame.server.components.messagesender.LoggedMessageSenderFactory;
import standardgame.server.components.messagesender.MessageLog;
import standardgame.choice.ChoiceLock;
import standardgame.game.StandardGame;
import standardgame.role.StandardRoleAssigner;
import standardgame.role.villager.SeerFactory;
import standardgame.role.villager.VillagerFactory;
import standardgame.role.werewolf.WerewolfFactory;

/**
 * @author Steven Weston
 */
@Configuration
public class Config {

	@Autowired private StandardSinglePlayerChoiceFactory singlePlayerChoiceFactory;
	@Autowired private StandardPlayerDao playerDao;
	@Autowired private StandardGame game;

	@Bean
	public StandardRoleAssigner getRoleFactory() {

		StandardRoleAssigner assigner = new StandardRoleAssigner();

		// Add more factories here:
		assigner.addRoleFactory(new WerewolfFactory(playerDao.getAll()));
		assigner.addRoleFactory(new VillagerFactory());
		assigner.addRoleFactory(new SeerFactory(playerDao.getAll(), singlePlayerChoiceFactory, game));

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
}
