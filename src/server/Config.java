package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.components.dao.choice.StandardSinglePlayerChoiceFactory;
import server.components.dao.player.StandardPlayerDao;
import server.components.messagesender.LoggedMessageSenderFactory;
import server.components.messagesender.MessageLog;
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

	@Bean
	public StandardRoleAssigner getRoleFactory() {

		StandardRoleAssigner assigner = new StandardRoleAssigner();

		// Add more factories here:
		assigner.addRoleFactory(new WerewolfFactory(playerDao.getAll()));
		assigner.addRoleFactory(new VillagerFactory());
		assigner.addRoleFactory(new SeerFactory(playerDao.getAll(), singlePlayerChoiceFactory, getGame()));

		return assigner;
	}

	@Bean
	public StandardGame getGame() {
		return new StandardGame(getMessageFactory());
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
