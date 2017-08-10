package com.mar.iotools.read;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import com.mar.iotools.string.StringUtils;

public class Read {

    /** Default size of the vector. */
    public static final int DEFAULTNBLINE = 100;

    private static Charset charset = StandardCharsets.UTF_8;

    /**
     * Reads a binary file containing a matrix of short values in little-endian ordering. The two first values in the
     * file are the number of rows and columns of the matrix. The following values are the elements of the matrix : M_00
     * M_01 ... M_10 M_11 ...
     * @param path
     * @return
     */
    public static short[][] readDoubleMatrixFromBinaryFile(final String path) {
        short[][] out = null;
        try {
            final DataInputStream stream = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));

            short row = 0;
            short col = 0;

            try {
                row = Short.reverseBytes(stream.readShort());
                col = Short.reverseBytes(stream.readShort());
            }
            catch (final IOException e) {
                e.printStackTrace();
            }

            out = new short[row][col];

            int count_row = 0;
            int count_col = 0;
            boolean endOfFile = false;
            while (!endOfFile) {
                try {
                    final short d = Short.reverseBytes(stream.readShort());
                    out[count_row][count_col] = d;
                    count_col++;
                    if (count_col == col) {
                        count_col = 0;
                        count_row++;
                    }
                }
                catch (final IOException e) {
                    endOfFile = true;
                }
            }

            stream.close();

        }
        catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }

        return out;
    }

    /**
     * Reads the specified file and returns the content as an array of double, one double value per line.
     * @param path
     *            the path to the file.
     * @return the content of the file as an array of strings.
     */
    public static double[] readDoubleVector(final String path) {
        return readDoubleVector(path, 0, Integer.MAX_VALUE);
    }

    /**
     * Reads the specified file and returns the content as an array of double, one double value per line. Only the
     * values from line linea to line lineb are returned.
     * @param path
     *            the path to the file.
     * @return the content of the file as an array of strings.
     */
    public static double[] readDoubleVector(final String path, final int linea, final int lineb) {
        final Vector<String> lines = new Vector<String>(DEFAULTNBLINE);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String current_line;
            int cpt_line = 0;

            while ((current_line = br.readLine()) != null) {
                if (cpt_line >= linea) {
                    lines.add(current_line);
                }
                cpt_line++;
                if (cpt_line > lineb) {
                    break;
                }
            }

        }
        catch (final IOException e) {
            e.printStackTrace();
        }

        final double[] out = new double[lines.size()];
        for (int i = 0; i < lines.size(); ++i) {
            out[i] = Double.parseDouble(lines.get(i));
        }

        return out;
    }

    /**
     * Reads a binary file containing a vector of short values in little-endian ordering.
     * @param path
     * @return
     */
    public static short[] readDoubleVectorFromBinaryFile(final String path) {
        return readDoubleVectorFromBinaryFile(path, 0, Integer.MAX_VALUE);
    }

    /**
     * Reads a binary file containing a vector of short values in little-endian ordering. Only the values from line
     * linea to line lineb are returned.
     * @param path
     * @param linea
     * @param lineb
     * @return
     */
    public static short[] readDoubleVectorFromBinaryFile(final String path, final int linea, final int lineb) {
        final Vector<Short> v = new Vector<Short>(DEFAULTNBLINE);

        try {
            final DataInputStream stream = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));

            boolean endOfFile = false;
            while (!endOfFile) {
                try {
                    v.add(Short.reverseBytes(stream.readShort()));
                }
                catch (final IOException e) {
                    endOfFile = true;
                }
            }

            stream.close();
        }
        catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }

        final short[] out = new short[v.size()];
        for (int i = 0; i < v.size(); ++i) {
            out[i] = v.get(i);
        }

        return out;
    }

    /**
     * Reads the specified file and returns the content as an array of strings, one string per line.
     * @param path
     *            the path to the file.
     * @return the content of the file as an array of strings.
     */
    public static String[] readText(final String path) {

        final Vector<String> lines = new Vector<String>(DEFAULTNBLINE);

        try {
            final InputStream input = new FileInputStream(path);
            final BufferedReader br = new BufferedReader(new InputStreamReader(input, charset));
            String current_line;

            while ((current_line = br.readLine()) != null) {
                lines.add(current_line);
            }

            br.close();

        }
        catch (final IOException e) {
            e.printStackTrace();
        }

        return StringUtils.vec2array(lines);
    }

    public static void setCharset(Charset pCharset) {
        charset = pCharset;
    }
}