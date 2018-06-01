package org.shalim.simplegame.player;

/**
 * {@link IPlayer} an interface for simple game players
 * 
 * @author Safey A.Halim
 *
 */
public interface IPlayer {
	/**
	 * Responsible for sending messages (implementing the game initiator's logic)
	 * @throws Exception
	 */
	public void send() throws Exception;
	
	/**
	 * Responsible for receiving messages (implementing the game receiver's logic)
	 * @throws Exception
	 */
	public void receive() throws Exception;
}
