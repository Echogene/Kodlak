package server.components.dao;

/**
 * @author Steven Weston
 */
public interface Dao<T extends Identifiable> {

	T getById(String id);

	void delete(String id);
}
