package org.shalim.simplegame;

import static org.shalim.simplegame.config.ErrorMessage.*;
import org.shalim.simplegame.config.GameConfig;
import org.shalim.simplegame.config.GameMode;

/**
 * {@link ArgumentParser} is responsible for parsing command line arguments
 * @author Safey A.Halim
 *
 */
public class ArgumentParser {
	
	/**
	 * Parses the command line arguments and returns a game configuration object. <br>
	 * Prints out error messages on the standard error in case of missing or wrong arguments. <br>
	 * @param args An array of command line arguments
	 * @return {@link GameConfig} Game configuration object
	 */
	public static GameConfig parse(String[] args) {
		if (args == null || args.length < 2) {
			System.err.println(MISSING_ARGS);
			return new GameConfig(GameMode.UNSUPPORTED);
		}
		if (!args[0].equals("--mode")) {
			System.err.println(MISSING_MODE);
			return new GameConfig(GameMode.UNSUPPORTED);
		}
		if (args[1].equals("s")) {
			System.out.println(SINGLE_PROCESS);
			return new GameConfig(GameMode.SINGLE_PROCESS);
		}
		if (args[1].equals("m")) {
			System.out.println(MULTI_PROCESS);
			return parseMultiProcess(args);
		}
		System.err.println(WRONG_MODE);
		return new GameConfig(GameMode.UNSUPPORTED);
	}
	
	private static GameConfig parseMultiProcess(String[] args) {
		if (args.length < 4 || !args[2].equals("--player")) {
			System.err.println(PLAYER);
			return new GameConfig(GameMode.UNSUPPORTED);
		}
		if (args[3].equals("i")) {
			return parsePort(GameMode.MULTI_PROCESS_INITIATOR, args);
		}
		if (args[3].equals("r")) {
			return parsePort(GameMode.MULTI_PROCESS_RECEIVER, args);
		}
		System.err.println(PLAYER_TYPE);
		return new GameConfig(GameMode.UNSUPPORTED);
	}
	
	private static GameConfig parsePort(GameMode gameMode, String[] args) {
		if (args.length < 5) {
			return new GameConfig(gameMode); // No error because port number is optional
		}
		if (args[4].equals("--port") && args.length >= 6) {
			if (isInt(args[5])) {
				return new GameConfig(gameMode, Integer.parseInt(args[5]));
			}
		}
		System.err.println(WRONG_PORT);
		return new GameConfig(GameMode.UNSUPPORTED);
	}
	
	private static boolean isInt(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
