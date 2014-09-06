package standardgame.choice;

import com.sun.istack.internal.NotNull;
import model.choice.Choice;
import model.choice.ChoiceException;
import model.player.Player;
import standardgame.server.components.dao.Identifiable;
import standardgame.player.StandardPlayer;

/**
 * @author Steven Weston
 */
public interface StandardChoice<P extends Player, T> extends Choice<P, T>, Identifiable {

	void choose(@NotNull StandardPlayer choice) throws ChoiceException;
}
