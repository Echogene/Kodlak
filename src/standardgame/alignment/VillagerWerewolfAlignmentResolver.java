package standardgame.alignment;

import model.alignment.Aligned;
import model.alignment.AlignmentResolver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Steven Weston
 */
@Component
public class VillagerWerewolfAlignmentResolver implements AlignmentResolver<VillagerWerewolfAlignment> {

	@Override
	public VillagerWerewolfAlignment resolveAlignment(List<? extends Aligned<VillagerWerewolfAlignment>> aligneds) {

		return VillagerWerewolfAlignment.resolve(aligneds, Aligned::getAlignment);
	}

	@Override
	public VillagerWerewolfAlignment resolveVisibleAlignment(List<? extends Aligned<VillagerWerewolfAlignment>> aligneds) {

		return VillagerWerewolfAlignment.resolve(aligneds, Aligned::getVisibleAlignment);
	}
}
