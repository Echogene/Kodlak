package server.components.dao;

import model.player.Player;

/**
 * @author Steven Weston
 */
public interface PlayerDao<P extends Player<?, ?, ?, ?, ?> & Identifiable> extends Dao<P> {

}
