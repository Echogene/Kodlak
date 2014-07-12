package standardgame.role.villager;

import model.choice.single.FirstSingleChoiceFactory;
import model.effect.Effect;
import model.message.ListMessageSender;
import org.junit.Before;
import org.junit.Test;
import standardgame.player.StandardPlayer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;
import static standardgame.alignment.VillagerWerewolfAlignment.WEREWOLF;
import static standardgame.phase.DayNightPhase.NIGHT;

public class SeerTest {

	private ListMessageSender messageSender;

	@Before
	public void setUp() throws Exception {
		messageSender = new ListMessageSender();
	}

	@Test
	public void testSeerCanSeeThemselves() throws Exception {
		StandardPlayer player = mock(StandardPlayer.class);
		when(player.getName()).thenReturn("Player");
		when(player.getVisibleAlignment()).thenReturn(VILLAGER);

		Seer seerRole = new Seer(
				player,
				Collections.singleton(player),
				new FirstSingleChoiceFactory<>(),
				messageSender
		);
		Effect effect = seerRole.getEffect(NIGHT);
		effect.perform();

		String messageSentToSeer = messageSender.getSentMessages().get(0);
		assertTrue(messageSentToSeer.contains("VILLAGER"));
	}

	@Test
	public void testSeerCanSeeVillager() throws Exception {
		StandardPlayer seer = mock(StandardPlayer.class);
		when(seer.getName()).thenReturn("Seer");

		StandardPlayer otherPlayer = mock(StandardPlayer.class);
		when(otherPlayer.getName()).thenReturn("Player");
		when(otherPlayer.getVisibleAlignment()).thenReturn(VILLAGER);

		Seer seerRole = new Seer(
				seer,
				new HashSet<>(Arrays.asList(otherPlayer, seer)),
				new FirstSingleChoiceFactory<>(),
				messageSender
		);
		Effect effect = seerRole.getEffect(NIGHT);
		effect.perform();

		String messageSentToSeer = messageSender.getSentMessages().get(0);
		assertTrue(messageSentToSeer.contains("VILLAGER"));
	}

	@Test
	public void testSeerCanSeeWerewolf() throws Exception {
		StandardPlayer seer = mock(StandardPlayer.class);
		when(seer.getName()).thenReturn("Seer");

		StandardPlayer otherPlayer = mock(StandardPlayer.class);
		when(otherPlayer.getName()).thenReturn("Player");
		when(otherPlayer.getVisibleAlignment()).thenReturn(WEREWOLF);

		Seer seerRole = new Seer(
				seer,
				new HashSet<>(Arrays.asList(otherPlayer, seer)),
				new FirstSingleChoiceFactory<>(),
				messageSender
		);
		Effect effect = seerRole.getEffect(NIGHT);
		effect.perform();

		String messageSentToSeer = messageSender.getSentMessages().get(0);
		assertTrue(messageSentToSeer.contains("WEREWOLF"));
	}

	@Test
	public void testSeerIsVillager() throws Exception {
		StandardPlayer seer = mock(StandardPlayer.class);
		when(seer.getName()).thenReturn("Seer");

		Seer seerRole = new Seer(
				seer,
				Collections.singleton(seer),
				new FirstSingleChoiceFactory<>(),
				messageSender
		);

		assertEquals(VILLAGER, seerRole.getAlignment());
		assertEquals(VILLAGER, seerRole.getVisibleAlignment());
	}
}