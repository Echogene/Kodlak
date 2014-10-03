package standardgame.role;

import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;

/**
 * @author Steven Weston
 */
@Component
public class RoleComparator implements Comparator<StandardRole> {

	@Override
	public int compare(StandardRole role1, StandardRole role2) {

		throw new NotImplementedException();
	}
}
