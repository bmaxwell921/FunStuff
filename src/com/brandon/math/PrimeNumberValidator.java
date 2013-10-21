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
			if (primeDivis(num, i)) {
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
		
		final long CAP = (long) Math.floor(Math.sqrt(num));
		
		for (long i = 1; i < CAP; ++i) {
			if (primeDivis(num, i)) {
				return false;
			}
		}
		
		return true;
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
		final long CAP = (long) Math.floor(Math.sqrt(num));
		
		/*
		 *  Making sure we've generated enough prime numbers. We need at 
		 *  least enough to get to the CAP
		 */
		if (primes.size() == 0 || primes.get(primes.size() - 1) < CAP) {
			// Generate more primes so we don't run into this problem again
			generatePrimesTo(CAP * EXTRA);
			savePrimes();
		}
		
		for (int i = 0; i < primes.size(); ++i) {
			long currentPrime = primes.get(i);
			
			/* Success case. Here we've checked all the lower primes
			 * and none of them have caused us to break
			*/
			if (currentPrime > CAP) {
				break;
			}
			
			/*
			 * Failure case, we've found a number the divides the given
			 */
			if (primeDivis(num, currentPrime)) {
				return false;
			}	
		}
		
		return true;
	}

	private static void generatePrimesTo(long max) {
		long num = primes.size() == 0 ? 1 : primes.get(primes.size() - 1);
		while (num < max) {
			if (isPrime(num)) {
				primes.add(num);
			}
			++num;
		}
	}
	
	// Multi-threaded because why not
	private static void savePrimes() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				FileUtil.save(primes);
			}	
		}).start();
	}
	
	// Used to check if a number is divisible, excluding 1
	private static boolean primeDivis(long num, long prime) {
		return prime != 1 && num % prime == 0;
	}
	
	public static void main(String[] args) {
		System.out.println(PrimeNumberValidator.isPrimeUsingFactorization(7));
	}
}
