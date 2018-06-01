package org.shalim.simplegame;

import org.shalim.simplegame.config.GameConfig;
import org.shalim.simplegame.player.multiprocess.MultiProcessGameController;
import org.shalim.simplegame.player.singleprocess.SingleProcessGameController;

/**
 * {@link GameControllerFactory} is a factory class responsible for the creation of game controllers
 * @author Safey A.Halim
 *
 */
public class GameControllerFactory {
	
	/**
	 * Creates a game controller object based on a certain game configuration. <br>
	 * @param gameConfig {@link GameConfig} a game configuration object. <br>
	 * @return {@link IGameController} object depending on the passed configuration
	 */
	public static IGameController createGameController(GameConfig gameConfig) {
		switch (gameConfig.getGameMode()) {
		case SINGLE_PROCESS:
			return new SingleProcessGameController();
		default:
			return new MultiProcessGameController(gameConfig);
		}
	}
}
