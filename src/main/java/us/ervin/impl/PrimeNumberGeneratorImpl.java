package us.ervin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.ervin.PrimeNumberGenerator;

public class PrimeNumberGeneratorImpl implements PrimeNumberGenerator {

	@Override
	public List<Integer> generate(int startingValue, int endingValue) {
		List<Integer> primes = new ArrayList<Integer>();
		// determine low and high values so we can walk through the range
		int low = Math.min(startingValue, endingValue);
		int high = Math.max(startingValue, endingValue);
		// make a stream of all numbers `low` to `high` inclusive
		// keeping only the prime integers
		primes = IntStream.rangeClosed(low, high)
			.filter(i -> isPrime(i)).boxed().collect(Collectors.toList());
		return primes;
	}
	

	@Override
	public boolean isPrime(int value) {
		boolean prime = false;
		if (value <= 1) {
			// negative numbers, 0, and 1 are not prime (can't divide 0 by itself...)
			prime = false;
		} else if (value == 2) {
			// special case for exactly 2
			prime = true;
		} else if (value % 2 == 0) {
			// even numbers != 2 are trivial to eliminate
			prime = false;
		} else {
			/*
			 * must check only up to the square root of the value.
			 * consider splitting value into two factors a and b
			 * value = a * b.  if both a and b are greater than 
			 * sqrt (value), the result must be greater than value.
			 */
			// assume the number IS prime until we prove otherwise
			prime = true;
			double max = Math.floor(Math.sqrt(value));
			for (int i = 2; i <= max && prime; i++) {
				double check = value / (double) i;
				// see if the value of `check` is an integer
				double rounded = Math.floor(check);
				if (check == rounded) {
					// found a divisor
					prime = false;
				}
			}
		}
		return prime;
	}
}
