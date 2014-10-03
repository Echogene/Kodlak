package standardgame.role;

import org.springframework.stereotype.Component;
import standardgame.player.StandardPlayer;
import standardgame.server.components.dao.Builder;
import standardgame.server.components.dao.Dao;
import standardgame.server.components.dao.IdentifiableMap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Steven Weston
 */
@Component
public class RoleDao implements Dao<StandardRole, RoleDao.RoleBuilder> {

	private final IdentifiableMap<StandardRole> roles = new IdentifiableMap<>();
	private final Map<String, StandardRoleFactory<?>> factories = new HashMap<>();

	public void addRoleFactory(StandardRoleFactory<?> factory) {
		factories.put(factory.getRoleName(), factory);
	}

	@Override
	public void delete(long id) {

		throw new NotImplementedException();
	}

	@Override
	public RoleBuilder create() {
		return new RoleBuilder();
	}

	@Override
	public StandardRole getById(long id) {
		return roles.get(id);
	}

	@Override
	public Set<StandardRole> getAll() {
		return new HashSet<>(roles.values());
	}

	public class RoleBuilder implements Builder<StandardRole> {

		public StandardRole create(String roleName, StandardPlayer player) {
			StandardRoleFactory<?> factory = factories.get(roleName);
			StandardRole role = factory.create(player);
			player.getRoles().add(role);
			roles.put(role);
			return role;
		}
	}
}
