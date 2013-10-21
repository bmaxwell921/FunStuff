package com.brandon.math;

import java.util.List;


/**
 * A class used to validate prime number
 * @author brandon
 *
 */
public class PrimeNumberValidator {
	
	private List<Long> primes;
	
	
	static {
		
	}
	/**
	 * Checks if the given number is prime by checking if the
	 * number is divisible by any number less than the given.
	 * @param num
	 * 				The number to check
	 * @return
	 * 			True if the number is prime, false otherwise
	 */
	public static boolean isPrimeNaive(long num) {
		if (num < 0) {
			num *= -1;
		}
		for (long i = 0; i < num; ++i) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if the given number is prime, only diving numbers
	 * up to the square root of the given number
	 * @param num
	 * 				The number to check
	 * @return
	 * 			True if the number is prime, false otherwise
	 */
	public static boolean isPrime(long num) {
		if (num < 0) {
			num *= -1;
		}
		
		final long CAP = (long) Math.ceil(Math.sqrt(num));
		
		for (long i = 1; i < CAP; ++i) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * A method to check whether the given number is prime using
	 * the idea of prime factorization.
	 * @param num
	 * 				The given number to check.
	 * @return
	 * 			True if the given number is prime, false otherwise
	 */
	public static boolean isPrimeUsingFactorization(long num) {
		return false;
	}
	
	
}
