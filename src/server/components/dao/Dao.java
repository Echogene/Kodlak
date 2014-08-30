package server.components.dao;

/**
 * @author Steven Weston
 */
public interface Dao<T extends Identifiable, B extends Builder<T>> extends Reader<T> {

	void delete(long id);

	B create();
}
