package model.alignment;

import standardgame.alignment.VillagerWerewolfAlignment;
import standardgame.alignment.VillagerWerewolfAlignmentResolver;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;
import static standardgame.alignment.VillagerWerewolfAlignment.WEREWOLF;
import static org.junit.Assert.assertEquals;

public class VillagerWerewolfAlignmentResolverTest {

	private static VillagerWerewolfAlignmentResolver resolver = new VillagerWerewolfAlignmentResolver();

	@Test
	public void shouldResolveToVillager() throws Exception {

		List<Aligned<VillagerWerewolfAlignment>> alignedThings = new ArrayList<>();
		alignedThings.add(new Villager());
		alignedThings.add(new Villager());
		alignedThings.add(new Villager());

		assertEquals(VILLAGER, resolver.resolveAlignment(alignedThings));
	}

	@Test
	public void shouldResolveToWerewolf() throws Exception {
		List<Aligned<VillagerWerewolfAlignment>> alignedThings = new ArrayList<>();
		alignedThings.add(new Villager());
		alignedThings.add(new Villager());
		alignedThings.add(new Werewolf());

		assertEquals(WEREWOLF, resolver.resolveAlignment(alignedThings));
	}

	@Test
	public void shouldVisuallyResolveToVillager() throws Exception {

		List<Aligned<VillagerWerewolfAlignment>> alignedThings = new ArrayList<>();
		alignedThings.add(new Villager());
		alignedThings.add(new Villager());
		alignedThings.add(new Villager());

		assertEquals(VILLAGER, resolver.resolveVisibleAlignment(alignedThings));
	}

	@Test
	public void shouldVisuallyResolveToWerewolf() throws Exception {
		List<Aligned<VillagerWerewolfAlignment>> alignedThings = new ArrayList<>();
		alignedThings.add(new Villager());
		alignedThings.add(new Villager());
		alignedThings.add(new Werewolf());

		assertEquals(WEREWOLF, resolver.resolveVisibleAlignment(alignedThings));
	}

	private class Villager implements Aligned<VillagerWerewolfAlignment> {

		@Override
		public VillagerWerewolfAlignment getAlignment() {
			return VILLAGER;
		}

		@Override
		public VillagerWerewolfAlignment getVisibleAlignment() {
			return VILLAGER;
		}
	}

	private class Werewolf implements Aligned<VillagerWerewolfAlignment> {

		@Override
		public VillagerWerewolfAlignment getAlignment() {
			return WEREWOLF;
		}

		@Override
		public VillagerWerewolfAlignment getVisibleAlignment() {
			return WEREWOLF;
		}
	}
}