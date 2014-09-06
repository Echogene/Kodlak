package standardgame.server.components.dao.player;

import model.alignment.AlignmentResolver;
import standardgame.server.components.dao.Builder;
import standardgame.server.components.dao.IdentifiableBuilder;
import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.player.StandardPlayer;
import standardgame.player.status.StandardStatus;

import java.util.function.Consumer;

/**
 * @author Steven Weston
 */
public class StandardPlayerBuilder extends IdentifiableBuilder<StandardPlayer, StandardPlayerBuilder.PlayerBuilderRequiringName> {

	private AlignmentResolver<VillagerWerewolfAlignment> resolver;
	private final Consumer<StandardPlayer> onCreation;

	public StandardPlayerBuilder(int id, Consumer<StandardPlayer> onCreation) {
		super(id);
		this.onCreation = onCreation;
	}

	@Override
	public PlayerBuilderRequiringName build() {
		return new PlayerBuilderRequiringName();
	}

	public class PlayerBuilderRequiringName implements Builder<StandardPlayer> {

		public StandardPlayer createWithName(String name) {
			StandardPlayer player = new StandardPlayer(
					name,
					resolver,
					new StandardStatus(),
					id
			);

			onCreation.accept(player);
			return player;
		}
	}
}
