package us.ervin.runner.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import us.ervin.runner.PrimalRunner;

public class PrimalRunnerMainTest {
	
	private static String[] args(String... args) {
		return args;
	}
	
	private PrintStream overridingOutputStream;
	private PrintStream overridingErrorStream;
	
	private ByteArrayOutputStream capturedStdout;
	private ByteArrayOutputStream capturedStderr;
	
	
	@Before
	public void setUp() {
		capturedStdout = new ByteArrayOutputStream();
		capturedStderr = new ByteArrayOutputStream();
		// making both auto flush for the test to pick up content more reliably
		overridingOutputStream = spy(new PrintStream(capturedStdout, true));
		overridingErrorStream = spy(new PrintStream(capturedStderr, true));
		// Pick up here - check for content in output and err
		System.setOut(overridingOutputStream);
		System.setErr(overridingErrorStream);
	}
	
	
	private void invokeRunnerAndExpect(final String[] args,
			final Optional<String> stdOutContent, final Optional<String> stdErrContent) {
		// run the tool
		PrimalRunner.main(args);
		// check what it printed
		if (stdOutContent.isPresent()) {
			String stdout = capturedStdout.toString();
			assertTrue(stdout.contains(stdOutContent.get()));
		} else {
			verifyZeroInteractions(overridingOutputStream);
		}
		if (stdErrContent.isPresent()) {
			String stderr = capturedStderr.toString();
			assertTrue(stderr.contains(stdErrContent.get()));
		} else {
			verifyZeroInteractions(overridingErrorStream);
		}
	}
	

	@Test
	public void testMainWithValidArgs() {
		invokeRunnerAndExpect(args("10", "20"), Optional.of("11"), Optional.empty());
	}
	
	
	@Test
	public void testCliNoRange() {
		invokeRunnerAndExpect(new String[0], Optional.of("Usage"), Optional.empty());
	}
	
	
	@Test
	public void testCliBadRangeFirstArg() {
		invokeRunnerAndExpect(args("nope"), Optional.of("Usage"), Optional.empty());
	}
	
	
	@Test
	public void testCliBadRangeSecondArg() {
		invokeRunnerAndExpect(args("10", "nope"), Optional.of("Usage"), Optional.of("Error parsing"));
	}
	
	
	@Test
	public void testCliBadRangeBadFirstGoodSecondArg() {
		invokeRunnerAndExpect(args("lol", "10"), Optional.of("Usage"), Optional.of("Error parsing"));
	}
	
	
	@Test
	public void testCli7900to7920() {
		invokeRunnerAndExpect(args("7900", "7920"), Optional.of("7901"), Optional.empty());
	}
}
