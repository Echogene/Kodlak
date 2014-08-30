package server.components.dao.choice;

import model.choice.group.GroupChoice;
import model.choice.group.GroupChoiceFactory;
import standardgame.player.StandardPlayer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Set;

/**
 * @author Steven Weston
 */
public class StandardGroupPlayerChoiceFactory
		implements GroupChoiceFactory<StandardPlayer,StandardPlayer,GroupChoice<StandardPlayer,StandardPlayer>> {

	@Override
	public GroupChoice<StandardPlayer, StandardPlayer> create(
			Set<StandardPlayer> choosers, Collection<StandardPlayer> choices
	) {
		throw new NotImplementedException();
	}
}
