package standardgame.role.villager;

import model.choice.single.FirstSingleChoiceFactory;
import model.effect.Effect;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import model.player.Player;

import java.util.Arrays;
import java.util.Collections;

import static standardgame.alignment.VillagerWerewolfAlignment.VILLAGER;
import static standardgame.alignment.VillagerWerewolfAlignment.WEREWOLF;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static standardgame.phase.DayNightPhase.NIGHT;

public class SeerTest {

	@Test
	public void testSeerCanSeeThemselves() throws Exception {
		Player player = mock(Player.class);
		when(player.getName()).thenReturn("Player");
		when(player.getVisibleAlignment()).thenReturn(VILLAGER);

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Seer seerRole = new Seer(player, Collections.singletonList(player), new FirstSingleChoiceFactory<>());
		Effect effect = seerRole.getEffect(NIGHT);
		effect.perform();

		verify(player).sendMessage(captor.capture());
		String messageSentToSeer = captor.getValue();
		assertTrue(messageSentToSeer.contains("VILLAGER"));
	}

	@Test
	public void testSeerCanSeeVillager() throws Exception {
		Player seer = mock(Player.class);
		when(seer.getName()).thenReturn("Seer");

		Player otherPlayer = mock(Player.class);
		when(otherPlayer.getName()).thenReturn("Player");
		when(otherPlayer.getVisibleAlignment()).thenReturn(VILLAGER);

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Seer seerRole = new Seer(
				seer,
				Arrays.asList(otherPlayer, seer),
				new FirstSingleChoiceFactory<>()
		);
		Effect effect = seerRole.getEffect(NIGHT);
		effect.perform();

		verify(seer).sendMessage(captor.capture());
		String messageSentToSeer = captor.getValue();
		assertTrue(messageSentToSeer.contains("VILLAGER"));
	}

	@Test
	public void testSeerCanSeeWerewolf() throws Exception {
		Player seer = mock(Player.class);
		when(seer.getName()).thenReturn("Seer");

		Player otherPlayer = mock(Player.class);
		when(otherPlayer.getName()).thenReturn("Player");
		when(otherPlayer.getVisibleAlignment()).thenReturn(WEREWOLF);

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		Seer seerRole = new Seer(
				seer,
				Arrays.asList(otherPlayer, seer),
				new FirstSingleChoiceFactory<>()
		);
		Effect effect = seerRole.getEffect(NIGHT);
		effect.perform();

		verify(seer).sendMessage(captor.capture());
		String messageSentToSeer = captor.getValue();
		assertTrue(messageSentToSeer.contains("WEREWOLF"));
	}

	@Test
	public void testSeerIsVillager() throws Exception {
		Player seer = mock(Player.class);
		when(seer.getName()).thenReturn("Seer");

		Seer seerRole = new Seer(
				seer,
				Arrays.asList(seer),
				new FirstSingleChoiceFactory<>()
		);

		assertEquals(VILLAGER, seerRole.getAlignment());
		assertEquals(VILLAGER, seerRole.getVisibleAlignment());
	}
}