package us.ervin.impl.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

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
		assertTrue("1 should have been prime", prime);
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
}
