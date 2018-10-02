package us.ervin.impl.test;

import static org.hamcrest.CoreMatchers.equalTo;
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

	private static PrimeNumberGenerator generator;
	
	@BeforeClass
	public static void initialize() {
		generator = new PrimeNumberGeneratorImpl();
	}
	
	
	// tests for the isPrime method
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
		List<Integer> knownPrimes = Arrays.asList(
			new Integer[] {
				2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 
				37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
				79, 83, 89, 97, 101
		});
		for (int i = 0; i < knownPrimes.get(knownPrimes.size() - 1); i++) {
			boolean prime = generator.isPrime(i);
			assertThat("Incorrect value for " + i, prime, equalTo(knownPrimes.contains(i)));
		}
	}
}
