package org.shalim.simplegame.config;

/**
 * {@link ErrorMessage} a set or error message constants
 * @author Safey A.Halim
 *
 */
public class ErrorMessage {
	public static final String HELP = "simplegame --mode s | m [--player i | r] | [--port port_number]  \n s: single process (mutithreading game) \n m: multiprocess (players communicate via sockets)\n --player i to start the initiator player, --player r to start the receiver player, OPTIONAL: --port portnumber (default port 5555)";
	public static final String MISSING_ARGS = "Error: Missing arguments.\n" + HELP;
	public static final String MISSING_MODE = "simplegame takes the argument --mode\n" + HELP;
	public static final String WRONG_MODE = "Error: Invalid argument. \n" + HELP;
	public static final String SINGLE_PROCESS = "Starting simpleegame in single-process mode";
	public static final String MULTI_PROCESS = "Starting simplegame in multi-process mode";
	public static final String PLAYER = "Error: in multi-player mode --player argument is expected\n" + HELP;
	public static final String PLAYER_TYPE = "Error: Wrong player type. Use --player i (for initiator) or --player r (for receiver)\n" + HELP;
	public static final String WRONG_PORT = "Error: Unknown argument --port is expected\n" + HELP;
}
