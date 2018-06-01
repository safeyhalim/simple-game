package org.shalim.simplegame;

import org.shalim.simplegame.config.GameConfig;
import org.shalim.simplegame.config.GameMode;

/**
 * Simple game's entry point. Responsible for parsing the command line arguments
 * and instantiating the game controller. <br>
 * 
 * @author Safey A.Halim
 * 
 */
public class Main {
	private static int NUM_MSG = 10;

	public static void main(String[] args) {
		GameConfig gameConfig = ArgumentParser.parse(args);
		if (gameConfig.getGameMode() == GameMode.UNSUPPORTED) {
			System.exit(1);
		}
		IGameController controller = GameControllerFactory
				.createGameController(gameConfig);
		controller.startGame(NUM_MSG);
	}
}
