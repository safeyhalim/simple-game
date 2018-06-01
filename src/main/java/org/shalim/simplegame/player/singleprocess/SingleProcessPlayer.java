package org.shalim.simplegame.player.singleprocess;

import java.util.concurrent.BlockingQueue;
import org.shalim.simplegame.player.AbstractPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link SingleProcessPlayer} is a game player that runs in a separate thread.<br>
 * It communicates to another {@link SingleProcessPlayer} via {@link BlockingQueue}'s for<br>
 * in and out messages<br>
 * 
 * @author Safey A.Halim
 */
public class SingleProcessPlayer extends AbstractPlayer
		implements Runnable {
	private Logger LOGGER = LoggerFactory.getLogger(SingleProcessPlayer.class);
	private BlockingQueue<String> msgOut; // contains messages sent to other player
	private BlockingQueue<String> msgIn; // contains messages received from the other player

	public SingleProcessPlayer(String name, boolean initiator, int numMessages,
			BlockingQueue<String> msgOut, BlockingQueue<String> msgIn) {
		super(name, numMessages, initiator);
		this.msgOut = msgOut;
		this.msgIn = msgIn;
	}

	@Override
	public void run() {
		if (initiator) {
			try {
				send();
			} catch (InterruptedException e) {
				executionSuccessful = false;
				LOGGER.error("Sender {} failed to send messages: {}", name, e);
			}
		} else {
			try {
				receive();
			} catch (InterruptedException e) {
				executionSuccessful = false;
				LOGGER.error("Receiver {} failed to receive messages: {}", name, e);
			}
		}
	}

	@Override
	public void send() throws InterruptedException {
		for (int i = 0; i < numMessages; i++) {
			String msg = createSenderMessage();
			msgOut.put(msg);
			LOGGER.info(msg);
			LOGGER.info("Reply from receiver: {}", msgIn.take());
		}
	}

	@Override
	public void receive() throws InterruptedException {
		for (int i = 1; i <= numMessages; i++) {
			String msg = msgIn.take();
			msgOut.put(createReceiverMessage(i, msg));
		}
	}

}
