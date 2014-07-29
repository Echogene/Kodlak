package server.components.dao;

import model.choice.Choice;

/**
 * @author Steven Weston
 */
public interface ChoiceDao<C extends Choice & Identifiable> extends Dao<C> {

}
