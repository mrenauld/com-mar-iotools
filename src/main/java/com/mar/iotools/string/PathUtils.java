package com.mar.iotools.string;

public class PathUtils {

	/**
	 * Returns the directory corresponding to the specified path.
	 * 
	 * @param path
	 * @return
	 */
	public static String getDirectory(final String path) {
		final String[] splitted = path.split("[\\\\/]");
		String dir = "";
		for (int i = 0; i < splitted.length - 1; ++i)
			dir += splitted[i] + "/";
		if (path.substring(path.length() - 1, path.length()).equals("/"))
			dir += splitted[splitted.length - 1];
		return dir;
	}

	/**
	 * Returns only the file name from the specific path.
	 * 
	 * @param path
	 * @return
	 */
	public static String getFile(final String path) {
		final String[] splitted = path.split("[\\\\/]");
		return splitted[splitted.length - 1];
	}

	/**
	 * Returns the extension of the file that would have the specified path. The
	 * file does not have to actually exist. Returns null if there is no
	 * extension.
	 * 
	 * @param path
	 * @return the extension
	 */
	public static String getFileExtension(final String path) {
		String[] splitted = path.split("[\\\\/]");
		splitted = splitted[splitted.length - 1].split("[.]");
		if (splitted.length <= 1)
			return "";
		return splitted[splitted.length - 1];
	}

	/**
	 * Returns true if the specified file has the specified extension.
	 * 
	 * @param path
	 * @param extension
	 * @return
	 */
	public static boolean hasExtension(final String path, final String extension) {
		final String fileExt = getFileExtension(path);
		return extension.equals(fileExt);
	}

	/**
	 * Adds a '/' at the end of the path, if not already present.
	 * 
	 * @param dir
	 * @return
	 */
	public static String normalizeDirectory(String dir) {
		final char end = dir.charAt(dir.length() - 1);
		if (end != '\\' || end != '/')
			dir += '/';
		return dir;
	}

	/**
	 * Removes the extensions of the specified file name.
	 * 
	 * @param file
	 * @return
	 */
	public static String removeExtension(final String file) {
		String out = "";
		String[] splitted = file.split("[\\\\/]");
		for (int i = 0; i < splitted.length - 1; ++i)
			out += splitted[i];
		splitted = splitted[splitted.length - 1].split("[.]");
		if (splitted.length == 1)
			out += splitted[0];
		else {
			for (int i = 0; i < splitted.length - 1; ++i)
				out += splitted[i];
		}
		return out;
	}

	/**
	 * Removes the extensions of the specified file names.
	 * 
	 * @param files
	 * @return
	 */
	public static String[] removeExtension(final String[] files) {
		final String[] out = new String[files.length];
		for (int i = 0; i < files.length; ++i)
			out[i] = removeExtension(files[i]);
		return out;
	}
}