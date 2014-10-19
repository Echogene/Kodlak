package standardgame.role;

import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * @author Steven Weston
 */
@Component
public class RoleComparator implements Comparator<StandardRole> {

	@Override
	public int compare(StandardRole role1, StandardRole role2) {

		// todo: apply a sensible order
		return role1.getName().compareTo(role2.getName());
	}
}
