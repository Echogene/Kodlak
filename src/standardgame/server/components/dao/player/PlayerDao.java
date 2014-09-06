package standardgame.server.components.dao.player;

import model.player.Player;
import standardgame.server.components.dao.Builder;
import standardgame.server.components.dao.Dao;
import standardgame.server.components.dao.Identifiable;

/**
 * @author Steven Weston
 */
public interface PlayerDao<P extends Player<?, ?, ?, ?, ?> & Identifiable, B extends Builder<P>> extends Dao<P, B> {

}
