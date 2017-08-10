package com.mar.iotools.write;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {

	private final String path;
	private FileWriter bw;

	private final boolean overwrite = true;
	private boolean isOpen = false;

	/**
	 * Constructs a new Writer object pointing to the specified file. If a
	 * previous file of this name already exists, it will be overwritten by the
	 * new data.
	 * 
	 * @param path
	 *            the path to the file to write to.
	 */
	public Writer(final String path) {
		this.path = path;
		open(true);
	}

	/**
	 * Constructs a new Writer object pointing to the specified file. The second
	 * parameter specifies if the new data should overwrite the previous file or
	 * not.
	 * 
	 * @param path
	 * @param erase
	 */
	public Writer(final String path, final boolean erase) {
		this.path = path;
		open(erase);
	}

	/**
	 * Clears the file.
	 */
	public void clearFile() {
		try {
			bw = new FileWriter(path, false);
			isOpen = true;
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the FileWriter.
	 */
	public void close() {
		try {
			bw.close();
			isOpen = false;
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens the FileWriter.
	 * 
	 * @parem erase
	 */
	public void open(final boolean erase) {
		try {
			bw = new FileWriter(path, !erase);
			isOpen = true;
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes the specified string at the end of the file.
	 * 
	 * @param s
	 *            the string to write.
	 */
	public void write(final String s) {
		if (isOpen == false)
			open(false);

		try {
			bw.write(s, 0, s.length());
		} catch (final IOException e) {
			e.printStackTrace();
		}

		close();
	}

	/**
	 * Writes the specified string at the end of the file. If "close" is set to
	 * fales, the FileWriter is not closed after writing the string.
	 * 
	 * @param s
	 * @param close
	 */
	public void write(final String s, final boolean close) {
		if (isOpen == false)
			open(false);

		try {
			bw.write(s, 0, s.length());
		} catch (final IOException e) {
			e.printStackTrace();
		}

		if (close == false)
			close();
	}
}