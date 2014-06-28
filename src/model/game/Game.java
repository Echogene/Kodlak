package model.game;

import model.alignment.Alignment;
import model.phase.Phase;
import model.player.Player;
import model.player.status.Status;

import java.util.Set;

/**
 * @author Steven Weston
 */
public interface Game<P extends Phase, A extends Alignment, S extends Status> {

	Set<Player<P, A, S>> getPlayers();
}
