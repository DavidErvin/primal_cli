package us.ervin.impl.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import us.ervin.PrimeNumberGenerator;
import us.ervin.impl.PrimeNumberGeneratorImpl;

public class PrimeNumberGeneratorImplTest {
	
	private static final List<Integer> FIRST_26_PRIMES = Arrays.asList(
			new Integer[] {
					2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 
					37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
					79, 83, 89, 97, 101
			});

	private static PrimeNumberGenerator generator;
	
	@BeforeClass
	public static void initialize() {
		generator = new PrimeNumberGeneratorImpl();
	}
	
	
	// tests for the isPrime method
	@Test
	public void testNegative() {
		boolean prime = generator.isPrime(-5);
		assertFalse("-5 should not have been prime", prime);
	}
	
	
	@Test
	public void testZero() {
		boolean prime = generator.isPrime(0);
		assertFalse("0 should not have been prime", prime);
	}
	
	
	@Test
	public void testOne() {
		boolean prime = generator.isPrime(1);
		assertFalse("1 should not have been prime", prime);
	}
	
	
	@Test
	public void testTwo() {
		boolean prime = generator.isPrime(2);
		assertTrue("2 should have been prime", prime);
	}
	
	
	@Test
	public void testThree() {
		boolean prime = generator.isPrime(3);
		assertTrue("3 should have been prime", prime);
	}
	
	
	@Test
	public void testFirstTwentySixPrimes() {
		for (int i = 0; i < FIRST_26_PRIMES.get(FIRST_26_PRIMES.size() - 1); i++) {
			boolean prime = generator.isPrime(i);
			assertThat("Incorrect value for " + i, prime, equalTo(FIRST_26_PRIMES.contains(i)));
		}
	}
	
	
	// tests for the generate method
	@Test
	public void testGenerateZeroSizeRangeOfPrimes() {
		List<Integer> primes = generator.generate(0, 0);
		assertThat(primes, is(notNullValue()));
		assertThat(primes.size(), is(0));
		
		// also check a negative number range
		primes = generator.generate(-10, -5);
		assertThat(primes, is(notNullValue()));
		assertThat(primes.size(), is(0));
		
		// and that we can flip the order of those numbers
		primes = generator.generate(-5, -10);
		assertThat(primes, is(notNullValue()));
		assertThat(primes.size(), is(0));
		
		// and that some huge but non-prime number range also is zero size
		primes = generator.generate(200, 210);
		assertThat(primes, is(notNullValue()));
		assertThat(primes.size(), is(0));
	}
	
	
	@Test
	public void testGenerateFirstTwentySixPrimes() {
		// we know 0-101 contains 26 primes
		List<Integer> primes = generator.generate(0, 101);
		assertThat(primes, is(notNullValue()));
		assertThat(primes.size(), is(26));
		assertTrue("Did not find all expected primes", primes.containsAll(FIRST_26_PRIMES));
		
		// and again in reverse
		primes = generator.generate(101, 0);
		assertThat(primes, is(notNullValue()));
		assertThat(primes.size(), is(26));
		assertTrue("Did not find all expected primes", primes.containsAll(FIRST_26_PRIMES));
	}
}
