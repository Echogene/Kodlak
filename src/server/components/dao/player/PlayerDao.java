package server.components.dao.player;

import model.player.Player;
import server.components.dao.Builder;
import server.components.dao.Dao;
import server.components.dao.Identifiable;

/**
 * @author Steven Weston
 */
public interface PlayerDao<P extends Player<?, ?, ?, ?, ?> & Identifiable, B extends Builder<P>> extends Dao<P, B> {

}
