package standardgame.choice;

import model.choice.Choice;
import model.player.Player;
import server.components.dao.Identifiable;

/**
 * @author Steven Weston
 */
public interface StandardChoice<P extends Player, T> extends Choice<P, T>, Identifiable {

}
