package com.brandon.util;

/**
 * Interface documenting behavior for parsing string
 * @author brandon
 *
 * @param <T>
 */
public interface Parser<T> {
	
	/**
	 * A method to parse the given string into some object
	 * @param str
	 * 				The string to be parsed
	 * @return
	 * 			The object representation of the string
	 */
	public T parse(String str);
}
