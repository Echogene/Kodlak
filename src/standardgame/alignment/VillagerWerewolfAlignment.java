package standardgame.alignment;

import model.alignment.Aligned;
import model.alignment.Alignment;

import java.util.Collection;
import java.util.function.Function;

/**
 * @author Steven Weston
 */
public enum VillagerWerewolfAlignment implements Alignment {

	VILLAGER, WEREWOLF;

	@Override
	public String getIndefiniteArticle() {
		// It's always "a".
		return "a";
	}

	public static VillagerWerewolfAlignment resolve(
			Collection<? extends Aligned<VillagerWerewolfAlignment>> aligneds,
			Function<Aligned<VillagerWerewolfAlignment>, VillagerWerewolfAlignment> getAlignment
	) {

		for (Aligned<VillagerWerewolfAlignment> aligned : aligneds) {
			if (WEREWOLF == getAlignment.apply(aligned)) {
				return WEREWOLF;
			}
		}
		return VILLAGER;
	}
}
