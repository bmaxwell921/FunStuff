package com.brandon.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	
	public static <T> void CSVSave(String filePath, List<T> list) {
		save(filePath, list, Constants.CSV_DELIMIT);
	}
	
	public static <T> void save(String filePath, List<T> list,  String delimit) {
		File dest = new File(filePath);
		
		if (!dest.exists()) {
			dest.getParentFile().mkdirs();
			try {
				dest.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			for (T type : list) {
				bw.write(type.toString());
				bw.write(Constants.CSV_DELIMIT);
			}
		} catch (IOException ioe) {
			System.out.println("Unable to save primes.");
			System.out.println(ioe.getMessage());
		}
	}
}
