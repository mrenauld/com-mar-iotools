package com.mar.iotools.string;

import java.util.Vector;

public class StringUtils {
	/**
	 * Returns a vector of String containing the elements of the specified
	 * array.
	 * 
	 * @param a
	 * @return
	 */
	public static Vector<String> array2vec(final String[] a) {
		final Vector<String> vector = new Vector<String>(a.length);
		for (int i = 0; i < a.length; ++i)
			vector.add(a[i]);
		return vector;
	}

	/**
	 * Returns true if the specified string is empty (null or "").
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(final String s) {
		if (s == null)
			return true;
		if (s.equals(""))
			return true;
		return false;
	}

	/**
	 * Returns an array of String containing the elements of the specified
	 * vector.
	 * 
	 * @param v
	 * @return
	 */
	public static String[] vec2array(final Vector<String> v) {
		final String[] array = new String[v.size()];
		for (int i = 0; i < array.length; ++i)
			array[i] = v.get(i);
		return array;
	}
}