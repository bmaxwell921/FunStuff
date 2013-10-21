package com.brandon.common;

import java.io.File;

public class Constants {
	
	public static final String MATH_DIR = "math";
	
	// The location of already calculated primes
	public static final String PRIMES_PATH = "." + File.separator + MATH_DIR 
			+ File.separator + "primes.csv";
	
	public static final String CSV_DELIMIT = ",";
	
	public static final long ZERO = 0l;
}
