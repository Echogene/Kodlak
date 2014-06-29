package model.player;

import model.alignment.Alignment;
import model.alignment.AlignmentResolver;
import model.phase.Phase;
import model.player.status.Status;
import model.role.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steven Weston
 */
public abstract class AbstractPlayer<P extends Phase, A extends Alignment, S extends Status>
		implements Player<P, A, S> {

	protected final String name;

	protected final List<Role<P, A>> roles = new ArrayList<>();

	private final AlignmentResolver<A> resolver;

	protected final S status;

	protected AbstractPlayer(
			String name,
			AlignmentResolver<A> resolver,
			S startingStatus
	) {
		this.name = name;
		this.resolver = resolver;
		this.status = startingStatus;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Role<P, A>> getRoles() {
		return roles;
	}

	@Override
	public void kill() {
		status.kill();
	}

	@Override
	public A getAlignment() {
		return resolver.resolveAlignment(getRoles());
	}

	@Override
	public A getVisibleAlignment() {
		return resolver.resolveVisibleAlignment(getRoles());
	}
}
