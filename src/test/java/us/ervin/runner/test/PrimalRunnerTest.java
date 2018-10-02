package us.ervin.runner.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import us.ervin.runner.Exiter;
import us.ervin.runner.PrimalRunner;

@RunWith(MockitoJUnitRunner.class)
public class PrimalRunnerTest {
	
	private @Mock Exiter exiter;
	private AtomicInteger capturedExitCode = new AtomicInteger(0);
	
	private PrimalRunner runner;
	
	@Before
	public void initialize() {
		doAnswer((invocation) -> {
			capturedExitCode.set(invocation.getArgumentAt(0, int.class));
			return null;
		}).when(exiter).exit(any(int.class));
		runner = new PrimalRunner(exiter);
	}
	
	
	@Test
	public void testCliGoodRange() {
		runner.printRange(new String[] {"0", "20"});
		assertThat(capturedExitCode.get(), is(0));
	}
	
	
	@Test
	public void testCliNoRange() {
		runner.printRange(new String[0]);
		assertThat(capturedExitCode.get(), is(1));
	}
	
	
	@Test
	public void testCliBadRangeFirstArg() {
		runner.printRange(new String[] {"not an integer"});
		assertThat(capturedExitCode.get(), is(1));
	}
	
	
	@Test
	public void testCliBadRangeSecondArg() {
		runner.printRange(new String[] {"10", "not an integer"});
		assertThat(capturedExitCode.get(), is(1));
	}
}
