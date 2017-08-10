package com.mar.iotools.read;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadHex {
	
	public static String readHexFile( String path, int nCol ) {
		String s = null;
		try {
			InputStream input = new FileInputStream(path);
			s = printHexStream(input, nCol);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public static String printHexStream(final InputStream inputStream, final int numberOfColumns) throws IOException{
		long streamPtr = 0;
		String s = "";
		while( inputStream.available() > 0 ) { 
			final long col = streamPtr++ % numberOfColumns;
			//System.out.printf("%02x ", inputStream.read());
			s += String.format("%02x ", inputStream.read());
			if (col == (numberOfColumns-1)) {
				s += "\n";
	        }
	    }
		return s;
	}
}