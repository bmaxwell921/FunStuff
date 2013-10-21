package com.brandon.math;

import com.brandon.common.Constants;

/**
 * Class used to generate Square Numbers
 * @author brandon
 *
 */
public class SquareNumberGenerator {
	
	/**
	 * A method to generate the square number that comes after
	 * the given square.
	 * 
	 * Defined Inductively
	 * 	Base Case: n = 0
	 * 		sqrt(0) = 0
	 * 		sqrtAnd = 1
	 * 		result = 0 + 0 + 1 = 1
	 * 	Inductive Step: Assume we have a number n^2
	 * 		sqrt(n^2) = n
	 * 		sqrtAnd = n + 1
	 * 		result = n^2 + n + n + 1
	 * 			   = n^2 + 2n + 1
	 * 			   = (n + 1) ^ 2
	 * 
	 * Algorithm taken from Alex
	 * 
	 * @param square
	 * @return
	 */
	public static long generateNext(long square) {
		if (square < 0) throw new IllegalArgumentException("Negative Number");
		
		double sqrt = Math.sqrt(square);
		double sqrtAnd = sqrt + 1;
		
		if (sqrt != Math.floor(sqrt) || Double.isInfinite(sqrt)) {
			throw new IllegalArgumentException(String.format("%d is not a square number.", square));
		}
		
		return (long) (square + sqrt + sqrtAnd);
	}
}
