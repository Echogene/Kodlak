package server.components.dao;

/**
 * @author Steven Weston
 */
public interface Reader<I extends Identifiable> {

	I getById(long id);
}
