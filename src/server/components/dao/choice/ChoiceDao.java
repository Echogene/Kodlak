package server.components.dao.choice;

import model.choice.Choice;
import server.components.dao.Builder;
import server.components.dao.Dao;
import server.components.dao.Identifiable;

/**
 * @author Steven Weston
 */
public interface ChoiceDao<C extends Choice & Identifiable, B extends Builder<C>> extends Dao<C, B> {

}
