package server.components.dao;

import java.util.Set;

/**
 * @author Steven Weston
 */
public interface Reader<I extends Identifiable> {

	I getById(long id);

	Set<I> getAll();
}
