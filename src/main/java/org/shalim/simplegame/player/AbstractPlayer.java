package org.shalim.simplegame.player;

/**
 * {@link AbstractPlayer} represents the abstraction layer for game players.<br>
 * It encapsulates common player properties and functionality. <br>
 * 
 * @author Safey A.Halim
 */
public abstract class AbstractPlayer implements IPlayer {
	protected String name = null; // Player's name
	protected boolean initiator = false; // if true, the player is the sender (starts the communication with the other player)
	protected int numMessages = 0; // Number of messages after which the game terminates
	protected boolean executionSuccessful = true;
	
	
	public boolean isExecutionSuccessful() {
		return executionSuccessful;
	}

	/**
	 * Constructor
	 * @param name Player's name
	 * @param numMessages Number of messages for which the player should run
	 * @param initiator if <code>true</code>, the player is the game initiator (sender)
	 */
	public AbstractPlayer(String name, int numMessages, boolean initiator) {
		this.name = name;
		this.initiator = initiator;
		this.numMessages = numMessages;
	}
	
	public void setInitiator(boolean initiator) {
		this.initiator = initiator;
	}
	
	public boolean isInitiator() {
		return initiator;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Builds the sender's (initiator) message
	 * @return Sender's message
	 */
	protected String createSenderMessage() {
		return String.format("Message from %s", name);
	}
	
	/**
	 * Builds the receiver's message which is an echo of the sender's message amended with the message's order. <br>
	 * @param index Order of the message received from the initiator
	 * @param msg Message received from the initiator
	 * @return Receiver's message
	 */
	protected String createReceiverMessage(int index, String msg) {
		return String.format("%s. no. %s", msg, index);
	}
}
