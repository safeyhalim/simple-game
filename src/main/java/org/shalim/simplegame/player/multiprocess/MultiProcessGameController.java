package org.shalim.simplegame.player.multiprocess;

import java.io.IOException;
import java.net.ConnectException;

import org.shalim.simplegame.IGameController;
import org.shalim.simplegame.config.GameConfig;
import org.shalim.simplegame.config.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link MultiProcessGameController} Game controller for the multi process simple game
 * @author Safey A.Halim
 *
 */
public class MultiProcessGameController implements IGameController {
	private Logger LOGGER = LoggerFactory
			.getLogger(MultiProcessGameController.class);
	private GameMode mode;
	private int port;

	/**
	 * Constructor
	 * @param gameConfig Simple game configuration 
	 */
	public MultiProcessGameController(GameConfig gameConfig) {
		mode = gameConfig.getGameMode();
		port = gameConfig.getPort();
	}

	@Override
	public void startGame(int numMessages) {
		MultiProcessPlayer player = null;
		if (mode == GameMode.MULTI_PROCESS_RECEIVER) {
			player = new MultiProcessPlayer("Receiver", numMessages, false,
					port);
		} else {
			player = new MultiProcessPlayer("Initiator", numMessages, true,
					port);
		}
		try {
			LOGGER.info("Startin player: {}", player.getName());
			player.start();
		} catch (ConnectException e) {
			LOGGER.error(
					"Error: Problem occured while running player {}. Connection refused. Make sure both players are running on the same port",
					player.getName());
		} catch (IOException e) {
			LOGGER.error("Error: Problem occured while running player {}.\n",
					player.getName(), e);
		}

	}

}
