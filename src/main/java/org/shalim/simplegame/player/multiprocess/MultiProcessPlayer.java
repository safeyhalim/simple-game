package org.shalim.simplegame.player.multiprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.shalim.simplegame.player.AbstractPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link MultiProcessPlayer} Simple game player in the multiprocess game mode. <br>
 * The player can be either an initiator or receiver (server). <br>
 * The players communicate via sockets on localhost
 * 
 * @author Safey A.Halim
 *
 */
public class MultiProcessPlayer extends AbstractPlayer {
	private Logger LOGGER = LoggerFactory.getLogger(MultiProcessPlayer.class);
	private int port;
	private static final String LOCALHOST = "localhost";
	private Socket socket = null;
	private ServerSocket serverSocket = null;
	private BufferedReader reader = null; // for reading (receiving) messages from socket
	private BufferedWriter writer = null; // for sending messages (writing to socket)
	
	public MultiProcessPlayer(String name, int numMessages, boolean initiator, int port) {
		super(name, numMessages, initiator);
		this.port = port;
	}

	public void start() throws UnknownHostException, IOException {
		if (initiator) {
			send();
		} else {
			receive();
		}
	}
	
	@Override
	public void send() throws UnknownHostException, IOException {
		try {
			initSocket();
			for (int i = 0; i < numMessages; i++) {
				String msg = createSenderMessage();
				doSendMessage(msg);
				LOGGER.info(msg);
				String reply = reader.readLine();
				LOGGER.info("Reply from receiver: {}", reply);
			}
		}
		finally {
			cleanup();
		}
	}

	@Override
	public void receive() throws UnknownHostException, IOException {
		try {
			initSocket();
			for (int i = 1; i <= numMessages; i++) {
				String msg = reader.readLine();
				String reply = createReceiverMessage(i, msg);
				doSendMessage(reply);
			}
		}
		finally {
			cleanup();
		}
	}
	
	private void doSendMessage(String msg) throws IOException {
		writer.write(msg + System.getProperty("line.separator"));
		writer.flush();
	}
	
	/**
	 * Initialization of sockets and reader/writer depending on the player's role in the game (initiator or receiver)
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void initSocket() throws UnknownHostException, IOException {
		if (!initiator) { // receiver
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept(); 
		}
		else {
			socket = new Socket(LOCALHOST, port);
		}
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	private void cleanup() throws IOException {
		if (reader != null) {
			reader.close();
		}
		if (writer != null) {
			writer.close();
		}
		if (socket != null) {
			socket.close();
		}
		if (serverSocket != null) {
			serverSocket.close();
		}
	}
}
