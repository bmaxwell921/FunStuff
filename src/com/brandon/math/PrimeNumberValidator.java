package com.brandon.math;

import java.util.List;

import com.brandon.common.Constants;
import com.brandon.util.FileUtil;
import com.brandon.util.Parser;


/**
 * A class used to validate prime number
 * @author brandon
 *
 */
public class PrimeNumberValidator {
	
	private static List<Long> primes;
	private static int EXTRA = 2;
	
	static {
		primes = FileUtil.readCSV(Constants.PRIMES_PATH, new Parser<Long>() {
			@Override
			public Long parse(String str) {
				return Long.parseLong(str);
			}	
		});
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
		if (num < 0) {
			num *= -1;
		}
		final long CAP = (long) Math.ceil(Math.sqrt(num));
		
		/*
		 *  Making sure we've generated enough prime numbers. We need at 
		 *  least enough to get to the CAP
		 */
		if (primes.size() == 0 || primes.get(primes.size() - 1) < CAP) {
			// Generate more primes so we don't run into this problem again
			generatePrimesTo(num * EXTRA);
			savePrimes();
		}
		
		boolean done = false;
		int i = 0;
		while (!done) {
			long currentPrime = primes.get(i);
			
			/* Success case. Here we've checked all the lower primes
			 * and none of them have caused us to break
			*/
			if (currentPrime > CAP) {
				done = true;
			}
			
			/*
			 * Failure case, we've found a number the divides the given
			 */
			if (num % currentPrime == 0) {
				return false;
			}		
		}
		
		return true;
	}

	private static void generatePrimesTo(long max) {
		// TODO
	}
	
	// Multi-threaded because why not
	private static void savePrimes() {
		new Runnable() {
			@Override
			public void run() {
				FileUtil.save(primes);
			}	
		};
	}
	
}
