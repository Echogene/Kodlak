package model.player;

import model.alignment.Alignment;
import model.alignment.AlignmentResolver;
import model.phase.Phase;
import model.player.status.Status;
import model.role.Role;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steven Weston
 */
public abstract class AbstractPlayer<
		P extends Phase,
		A extends Alignment,
		S extends Status,
		R extends Role<P, A, Y>,
		Y extends Player<P, A, S, R, Y>
> implements Player<P, A, S, R, Y> {

	protected String name;

	@JsonSerialize(using = RoleListSerializer.class)
	protected final List<R> roles = new ArrayList<>();

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

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<R> getRoles() {
		return roles;
	}

	@Override
	public void kill() {
		status.kill();
	}

	@Override
	@JsonIgnore
	public A getAlignment() {
		return resolver.resolveAlignment(getRoles());
	}

	@Override
	@JsonIgnore
	public A getVisibleAlignment() {
		return resolver.resolveVisibleAlignment(getRoles());
	}
}
