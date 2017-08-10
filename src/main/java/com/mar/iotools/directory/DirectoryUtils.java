package com.mar.iotools.directory;

import java.io.File;
import java.util.Vector;

import com.mar.iotools.string.PathUtils;

public class DirectoryUtils {
	
	/**
	 * Returns the list of files in the specified directory. If opensubfolder is
	 * set to true, the subfolders are also explored.
	 * 
	 * @param path
	 * @param opensubfolder
	 * @return
	 */
	public static Vector<String> getFilesFromDirectory( String path,
			boolean opensubfolder ) {
		File dir = new File(path);
		if( dir.exists() == false ) {
			System.out.println("Directory.getFilesFromDirectory - warning:"
					+ " the directory does not exist.");
			return null;
		}
		if( dir.isDirectory() == false ) {
			System.out.println("Directory.getFilesFromDirectory - warning:"
					+ " the path is not a directory.");
			return null;
		}
		
		Vector<String> listOfFiles = new Vector<String>(10);
		String[] children = dir.list();
		for( int i = 0; i < children.length; ++i ) {
			dir = new File(children[i]);
			if( dir.isDirectory() ) {
				listOfFiles.addAll(getFilesFromDirectory(children[i],
						opensubfolder));
			}
			else {
				listOfFiles.add(children[i]);
			}
		}
		
		return listOfFiles;
	}
	
	/**
	 * Returns the list of files in the specified directory that match the 
	 * specified extension. If opensubfolder is set to true, the subfolders
	 * are also explored.
	 * 
	 * @param path
	 * @param opensubfolder
	 * @param extension
	 * @return
	 */
	public static Vector<String> getFilesFromDirectory( String path,
			boolean opensubfolder, String extension ) {
		File dir = new File(path);
		if( dir.exists() == false ) {
			System.out.println("Directory.getFilesFromDirectory - warning:"
					+ " the directory does not exist.");
			return null;
		}
		if( dir.isDirectory() == false ) {
			System.out.println("Directory.getFilesFromDirectory - warning:"
					+ " the path is not a directory.");
			return null;
		}
		
		Vector<String> listOfFiles = new Vector<String>(10);
		String[] children = dir.list();
		for( int i = 0; i < children.length; ++i ) {
			dir = new File(children[i]);
			if( dir.isDirectory() ) {
				listOfFiles.addAll(getFilesFromDirectory(children[i],
						opensubfolder, extension));
			}
			else if( PathUtils.hasExtension(children[i], extension) ){
				listOfFiles.add(children[i]);
			}
		}
		
		return listOfFiles;
	}
}
