package org.shalim.simplegame;

import static org.junit.Assert.*;
import static org.shalim.simplegame.test.util.ConsoleCapturer.*;
import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.shalim.simplegame.config.ErrorMessage;
import org.shalim.simplegame.config.GameConfig;
import org.shalim.simplegame.config.GameMode;

/**
 * {@link ArgumentParser} test class
 * @author Safey A.Halim
 *
 */
public class ArgumentParserTest {
	private static final String WHITE_SPACE = " ";
	@Test
	public void shouldParseSingleProcessGameCommand() {
		// Given
		String command = "--mode s";
		// When
		GameConfig config = callParser(command);
		// Then
		assertNotNull(config);
		assertEquals(config.getGameMode(), GameMode.SINGLE_PROCESS);
		assertEquals(config.getPort(), GameConfig.DEFAULT_PORT);
	}
	
	@Test
	public void shouldParseMultiProcessInitiatorCommand() {
		// Given
		String command = "--mode m --player i";
		// When 
		GameConfig config = callParser(command);
		// Then
		assertNotNull(config);
		assertEquals(config.getGameMode(), GameMode.MULTI_PROCESS_INITIATOR);
		assertEquals(config.getPort(), GameConfig.DEFAULT_PORT);
	}
	
	@Test
	public void shouldParseMutilProcessReceiverCommand() {
		// Given
		String command = "--mode m --player r";
		// When 
		GameConfig config = callParser(command);
		// Then
		assertNotNull(config);
		assertEquals(config.getGameMode(), GameMode.MULTI_PROCESS_RECEIVER);
		assertEquals(config.getPort(), GameConfig.DEFAULT_PORT);
	}
	
	@Test
	public void shouldParseMutilProcessWithPort() {
		// Given
		int port = 1234;
		String command = "--mode m --player r --port " + port;
		// When 
		GameConfig config = callParser(command);
		// Then
		assertNotNull(config);
		assertEquals(config.getGameMode(), GameMode.MULTI_PROCESS_RECEIVER);
		assertEquals(config.getPort(), port);
	}
	
	@Test
	public void shouldComplainAboutMissingArguments() {
		// Given
		ByteArrayOutputStream stdErr = captureStdErr();
		// When 
		GameConfig config = callParser("");
		// Then
		restoreStdErr();
		String output = stdErr.toString().trim();
		assertEquals(output, ErrorMessage.MISSING_ARGS);
		assertUnsupported(config);
	}
	
	@Test
	public void shouldComplainAboutMissingPlayerArg() {
		// Given
		ByteArrayOutputStream stdErr = captureStdErr();
		String command = "--mode m";
		// When 
		GameConfig config = callParser(command);
		// Then
		restoreStdErr();
		String output = stdErr.toString().trim();
		assertEquals(output, ErrorMessage.PLAYER);
		assertUnsupported(config);
	}
	
	@Test
	public void shouldComplainAboutInvalidPort() {
		// Given
		ByteArrayOutputStream stdErr = captureStdErr();
		String command = "--mode m --player i --port blabla";
		// When 
		GameConfig config = callParser(command);
		// Then
		restoreStdErr();
		String output = stdErr.toString().trim();
		assertEquals(output, ErrorMessage.WRONG_PORT);
		assertUnsupported(config);
	}
	
	private void assertUnsupported(GameConfig config) {
		assertNotNull(config);
		assertEquals(config.getGameMode(), GameMode.UNSUPPORTED);
		assertEquals(config.getPort(), GameConfig.DEFAULT_PORT);
	}
	
	private GameConfig callParser(String command) {
		return ArgumentParser.parse(command.split(WHITE_SPACE));
	}
}
