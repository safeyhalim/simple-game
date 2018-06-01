package org.shalim.simplegame.config;

/**
 * {@link GameConfig} represents the simple game's configuration 
 * @author Safey A.Halim
 *
 */
public class GameConfig {
	public static final int DEFAULT_PORT = 5555;
	private GameMode gameMode;
	private int port = DEFAULT_PORT; // Default port (if no port number was passed for a multiprocess game)
	
	public GameConfig(GameMode gameMode, int port) {
		this.gameMode = gameMode;
		this.port = port;
	}
	
	public GameConfig(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public int getPort() {
		return port;
	}
}
