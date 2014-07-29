package server.components;

import com.google.common.collect.Multiset;
import model.role.UnavailableRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.components.dao.StandardPlayerDao;
import standardgame.game.StandardGame;
import standardgame.player.StandardPlayer;
import standardgame.role.StandardRole;
import standardgame.role.StandardRoleAssigner;

import java.util.Iterator;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Steven Weston
 */
@Controller
public class RoleController {

	private final StandardGame game;
	private final StandardRoleAssigner roleFactory;
	private final StandardPlayerDao playerDao;

	@Autowired
	public RoleController(StandardGame game, StandardRoleAssigner roleFactory, StandardPlayerDao playerDao) {

		this.game = game;
		this.roleFactory = roleFactory;
		this.playerDao = playerDao;
	}

	@RequestMapping(value = "/addRoleToPlayer.do", method = POST)
	@ResponseBody
	public void addRoleToPlayer(
			@RequestParam("playerId") String playerId,
			@RequestParam("roleName") String roleName
	) throws UnavailableRoleException {

		roleFactory.addRoleToPlayer(playerDao.getById(playerId), roleName);
	}

	@RequestMapping(value = "/removeRoleFromPlayer.do", method = POST)
	@ResponseBody
	public void removeRoleFromPlayer(
			@RequestParam("playerId") String playerId,
			@RequestParam("roleName") String roleName
	) throws UnavailableRoleException {

		StandardPlayer player = playerDao.getById(playerId);
		for (Iterator<StandardRole> iterator = player.getRoles().iterator(); iterator.hasNext(); ) {
			StandardRole role = iterator.next();
			if (role.getName().equals(roleName)) {
				iterator.remove();
				break;
			}
		}
		addRole(roleName);
	}

	@RequestMapping(value = "/addRole.do", method = POST)
	@ResponseBody
	public void addRole(
			@RequestParam("roleName") String roleName
	) throws UnavailableRoleException {

		if (!roleFactory.isRoleSupported(roleName)) {
			throw new UnavailableRoleException();
		}
		roleFactory.addRole(roleName);
	}

	@RequestMapping(value = "/removeRole.do", method = POST)
	@ResponseBody
	public void removeRole(
			@RequestParam("roleName") String roleName
	) throws UnavailableRoleException {

		roleFactory.removeRole(roleName);
	}

	@RequestMapping(value = "/getAvailableRoles.do", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Multiset<String> getAvailableRoles() {
		return roleFactory.getAvailableRoles();
	}
}
