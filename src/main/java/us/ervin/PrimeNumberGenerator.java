package us.ervin;

import java.util.List;

/**
 * Describes a prime number generator which can also test a given
 * number to determine if it is prime
 * 
 * @author dervin
 */
public interface PrimeNumberGenerator {

	/**
	 * Generates a list of prime numbers in the given range, inclusive of
	 * both the starting and ending value.  Note that the starting and ending
	 * values can be in any order of smaller or larger
	 * 
	 * @param startingValue
	 * 	The starting bound of the range in which to return prime numbers
	 * @param endingValue
	 * 	The ending bound of the range in which to return prime numbers
	 * @return
	 * 	A non-null list of all prime integers within the given range
	 */
	List<Integer> generate(int startingValue, int endingValue);

	
	/**
	 * Determines if a number is prime
	 * 
	 * @param value
	 * 	The integer to check
	 * @return
	 * 	<code>true</code> if the value is prime, <code>false</code> otherwise
	 */
	boolean isPrime(int value);
}
