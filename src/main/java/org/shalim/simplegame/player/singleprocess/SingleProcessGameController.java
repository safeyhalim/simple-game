package org.shalim.simplegame.player.singleprocess;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.shalim.simplegame.IGameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link SingleProcessGameController} Game controller for the single process simple game
 * The game in this case runs in a single process and each player runs in a separate thread
 * @author Safey A.Halim
 *
 */
public class SingleProcessGameController implements IGameController {
	private Logger LOGGER = LoggerFactory.getLogger(SingleProcessGameController.class);
	private static int MSG_QUEUE_SIZE = 1;
	private BlockingQueue<String> senderOut = new ArrayBlockingQueue<String>(MSG_QUEUE_SIZE);
	private BlockingQueue<String> senderIn = new ArrayBlockingQueue<String>(MSG_QUEUE_SIZE);
	private SingleProcessPlayer sender = null;
	private SingleProcessPlayer receiver = null;
	
	@Override
	public void startGame(int numMessages) {
		sender = new SingleProcessPlayer("Initiator", true, numMessages, senderOut, senderIn);
		receiver = new SingleProcessPlayer("Receiver", false, numMessages, senderIn, senderOut);
		Thread senderThread = new Thread(sender);
		Thread receiverThread = new Thread(receiver);
		senderThread.start();
		receiverThread.start();
		try {
			senderThread.join();
			receiverThread.join();
		} catch (InterruptedException e) {
			LOGGER.error("Problem in thread's execution. {}", e);
		}
		if (hasGameSuccessfullyTerminated()) {
			LOGGER.info("Game terminated successfully");
		} else {
			LOGGER.error("Game did not succesffuly terminate!");
		}
	}
	
	public boolean hasGameSuccessfullyTerminated() {
		return sender.isExecutionSuccessful() && receiver.isExecutionSuccessful() && senderOut.size() == 0 && senderIn.size() == 0; 
	}

}
