package com.brandon.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.brandon.common.Constants;

/**
 * Class used to read and write from and to files. 
 * @author brandon
 *
 */
public class FileUtil {

	/**
	 * Convenience method for reading CSV files
	 * @param filePath
	 * 					The path to the file to be read
	 * @param parser
	 * 					The parser object used to convert the fileContents to the generic type
	 * @return
	 * 			A list of generic types read from the file
	 */
	public static <T> List<T> readCSV(String filePath, Parser<T> parser) {
		return read(filePath, Constants.CSV_DELIMIT, parser);
	}
	
	/**
	 * Method for reading files
	 * @param filePath
	 * 					The path to the file to be read
	 * @param delimit
	 * 					The delimit for the objects found in the file 
	 * @param parser
	 * 					The parser object used to convert the fileContents to the generic type
	 * @return
	 */
	public static <T> List<T> read(String filePath, String delimit, Parser<T> parser) {
		String fileContents = FileUtil.fullyRead(filePath);
		return parse(fileContents, delimit, parser);
	}
	
	// Reads all of the contents of the file
	private static String fullyRead(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			StringBuilder sb = new StringBuilder();
			String read = "";
			while ((read = br.readLine()) != null) {
				sb.append(read);
			}
			return sb.toString();
		} catch (IOException ioe) {
			System.out.println("Unable to read file: ");
			System.out.println(ioe.getMessage());
			return null;
		}
	}
	
	// Parses the fileContents into a list
	private static <T> List<T> parse(String fileContents, String delimit, Parser<T> parser) {
		List<T> ret = new ArrayList<T>();
		if (fileContents == null) {
			return ret;
		}
		StringTokenizer st = new StringTokenizer(fileContents, delimit);
		
		while (st.hasMoreTokens()) {
			ret.add(parser.parse(st.nextToken()));
		}
		
		return ret;
	}
	
	public static <T> void save(List<T> list) {
		
	}
}
