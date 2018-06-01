package org.shalim.simplegame.singleprocess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;
import org.shalim.simplegame.player.IPlayer;
import org.shalim.simplegame.player.singleprocess.SingleProcessPlayer;

/**
 * {@link SingleProcessPlayer} test class
 * @author Safey A.Halim
 *
 */
public class SingleProcessPlayerTest {
	private static final int numMessages = 2;
	private static final String message = "Message";
			
	@Test
	public void sendTest() throws Exception {
		// Given
		String senderName = "TestSender";
		BlockingQueue<String> msgOut = new ArrayBlockingQueue<String>(numMessages);
		BlockingQueue<String> msgIn = createMockQueue();
		IPlayer sender = new SingleProcessPlayer(senderName, true, numMessages, msgOut, msgIn);
		// When
		sender.send();
		// Then 
		assertEquals(msgOut.size(), numMessages);
		for (int i = 0; i < numMessages; i++) {
			assertEquals(msgOut.take(), message + " from TestSender");
		}
	}
	
	@Test
	public void receiveTest() throws Exception {
		// Given
		String receiverName = "TestReceiver";
		BlockingQueue<String>  msgIn = createMessageQueue();
		BlockingQueue<String> msgOut = new ArrayBlockingQueue<String>(numMessages);
		IPlayer receiver = new SingleProcessPlayer(receiverName, false, numMessages, msgOut, msgIn);
		// When
		receiver.receive();
		// Then
		assertTrue(msgIn.isEmpty());
		for (int i = 0; i < numMessages; i++) {
			assertEquals(msgOut.take(), String.format("%s. no. %s", message, i + 1));
		}
	}
	
	private BlockingQueue<String> createMockQueue() throws InterruptedException {
		@SuppressWarnings("unchecked")
		BlockingQueue<String> msgQueue = mock(ArrayBlockingQueue.class);
		when(msgQueue.take()).thenReturn(message);
		return msgQueue;
	}
	
	private BlockingQueue<String> createMessageQueue() {
		BlockingQueue<String> msgIn = new ArrayBlockingQueue<String>(numMessages);
		for (int i = 0; i < numMessages; i++) {
			msgIn.add(message);
		}
		return msgIn;
	}
}
