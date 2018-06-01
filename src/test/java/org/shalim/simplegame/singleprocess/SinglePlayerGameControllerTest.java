package org.shalim.simplegame.singleprocess;

import static org.junit.Assert.assertEquals;
import static org.shalim.simplegame.test.util.ConsoleCapturer.*;
import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.shalim.simplegame.GameControllerFactory;
import org.shalim.simplegame.IGameController;
import org.shalim.simplegame.config.GameConfig;
import org.shalim.simplegame.config.GameMode;

public class SinglePlayerGameControllerTest {
	
	private static final String consoleExpected =
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Message from Initiator\n" +
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Reply from receiver: Message from Initiator. no. 1\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Message from Initiator\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Reply from receiver: Message from Initiator. no. 2\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Message from Initiator\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Reply from receiver: Message from Initiator. no. 3\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Message from Initiator\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Reply from receiver: Message from Initiator. no. 4\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Message from Initiator\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Reply from receiver: Message from Initiator. no. 5\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Message from Initiator\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Reply from receiver: Message from Initiator. no. 6\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Message from Initiator\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Reply from receiver: Message from Initiator. no. 7\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Message from Initiator\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Reply from receiver: Message from Initiator. no. 8\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Message from Initiator\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Reply from receiver: Message from Initiator. no. 9\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Message from Initiator\n"+
"[Thread-0] INFO org.shalim.simplegame.player.singleprocess.SingleProcessPlayer - Reply from receiver: Message from Initiator. no. 10\n"+
"[main] INFO org.shalim.simplegame.player.singleprocess.SingleProcessGameController - Game terminated successfully\n";
	
	@Test
	public void testSingleProcessGameController() {
		// Given
		GameConfig config = new GameConfig(GameMode.SINGLE_PROCESS);
		IGameController controller = GameControllerFactory.createGameController(config);
		int numMessages = 10;
		ByteArrayOutputStream stdErr = captureStdErr();
		// When 
		controller.startGame(numMessages);
		// Then 
		restoreStdErr();
		assertEquals(consoleExpected, stdErr.toString());
	}
}
