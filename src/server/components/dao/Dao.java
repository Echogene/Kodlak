package server.components.dao;

/**
 * @author Steven Weston
 */
public interface Dao<T extends Identifiable, B extends Builder<T>> {

	T getById(String id);

	void delete(String id);

	B create();
}
