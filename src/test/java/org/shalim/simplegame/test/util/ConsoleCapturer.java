package org.shalim.simplegame.test.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * {@link ConsoleCapturer} Utility class to capture the output on standard output/error
 * @author Safey A.Halim
 *
 */
public class ConsoleCapturer {
	/**
	 * Captures the standard error console in an {@link ByteArrayOutputStream}
	 * @return {@link ByteArrayOutputStream}
	 */
	public static ByteArrayOutputStream captureStdErr() {
		ByteArrayOutputStream stdErr = new ByteArrayOutputStream();
		System.setErr(new PrintStream(stdErr));
		return stdErr;
	}
	
	/**
	 * Restores back the standard error console
	 */
	public static void restoreStdErr() {
		System.setErr(System.err);
	}
}
