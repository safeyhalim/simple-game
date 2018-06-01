package org.shalim.simplegame.config;

/**
 * {@link GameMode} an enumeration of simple game's modes of operation <br>
 * The game can be: a single process game (players run in the same process but in different threads), <br> 
 * or as a multiprocess game. In that case, the game can run either for an initiator player or for a receiver player <br>
 * @author Safey A.Halim
 *
 */
public enum GameMode {
	SINGLE_PROCESS,
	MULTI_PROCESS_INITIATOR,
	MULTI_PROCESS_RECEIVER,
	UNSUPPORTED;
}
