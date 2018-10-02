package us.ervin.impl;

import java.util.List;

import us.ervin.PrimeNumberGenerator;

public class PrimeNumberGeneratorImpl implements PrimeNumberGenerator {

	@Override
	public List<Integer> generate(int startingValue, int endingValue) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public boolean isPrime(int value) {
		boolean prime = false;
		if (value <= 0) {
			// negative numbers and 0 are not prime (can't divide 0 by itself...)
			prime = false;
		} else if (value == 1 || value == 2 || value == 3) {
			// special case small numbers
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
			double max = Math.floor(Math.sqrt(value));
			for (int i = 3; i <= max && !prime; i++) {
				double check = value / (double) i;
				// see if the value of `check` is an integer
				double rounded = Math.floor(check);
				prime = check == rounded;
			}
		}
		return prime;
	}
}
