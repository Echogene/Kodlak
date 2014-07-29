package server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.components.messagesender.LoggedMessageSenderFactory;
import server.components.messagesender.MessageLog;
import standardgame.choice.ChoiceLock;
import standardgame.choice.StandardSinglePlayerChoiceFactory;
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

	@Bean
	public StandardRoleAssigner getRoleFactory() {

		StandardRoleAssigner assigner = new StandardRoleAssigner();
		StandardGame game = getGame();

		// Add more factories here:
		assigner.addRoleFactory(new WerewolfFactory(game.getPlayers()));
		assigner.addRoleFactory(new VillagerFactory());
		assigner.addRoleFactory(new SeerFactory(game.getPlayers(), getSinglePlayerChoiceFactory(), game));

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
	public StandardSinglePlayerChoiceFactory getSinglePlayerChoiceFactory() {
		return new StandardSinglePlayerChoiceFactory(getChoiceLock());
	}

	@Bean
	public ChoiceLock getChoiceLock() {
		return new ChoiceLock();
	}
}
