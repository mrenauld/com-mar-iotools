package com.mar.iotools.string;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;

import org.apache.commons.io.FilenameUtils;

import com.mar.framework.core.utils.ObjectUtils;

/**
 * Utility class for manipulating filenames and paths, based on
 * org.apache.commons.io.FilenameUtils. We use the same nomenclature as
 * FilenameUtils - we define six components within a filename (example
 * C:\dev\project\file.txt):
 * <ul>
 * <li>the prefix - C:\</li>
 * <li>the path - dev\project\</li>
 * <li>the full path - C:\dev\project\</li>
 * <li>the name - file.txt</li>
 * <li>the base name - file</li>
 * <li>the extension - txt</li>
 * </ul>
 *
 * @author mrenauld
 *
 */
public class FilepathUtils {

    /**
     * Adds the specified extension to the filename.
     *
     * @param pFilename
     * @param pExtension
     * @return
     */
    public static String addExtension(String pFilename, String pExtension) {
        return pFilename + "." + pExtension;
    }

    /**
     * Adds the specified extension to the filename, if the filename does not
     * have the extension already.
     *
     * @param pFilename
     * @param pExtension
     * @return
     */
    public static String addExtensionIfNotPresent(String pFilename, String pExtension) {
        return pFilename + "." + pExtension;
    }

    /**
     * Adds a '/' at the end of the path, if not already present.
     *
     * @param pPath
     * @return
     */
    public static String addTrailingSeparator(String pPath) {
        char end = pPath.charAt(pPath.length() - 1);
        if (end != '\\' || end != '/') {
            pPath += '/';
        }
        return pPath;
    }

    /**
     * Returns the directory corresponding to the specified filename.
     *
     * @param pFilename
     * @return
     */
    public static String getDirectory(String pFilename) {
        String[] splitted = pFilename.split("[\\\\/]");
        String dir = "";
        for (int i = 0; i < splitted.length - 1; ++i) {
            dir += splitted[i] + "/";
        }
        if (pFilename.substring(pFilename.length() - 1, pFilename.length()).equals("/")) {
            dir += splitted[splitted.length - 1];
        }
        return dir;
    }

    /**
     * Returns the extension for the specified filename
     *
     * @param pFilename
     * @return the extension
     */
    public static String getExtension(String pFilename) {
        return FilenameUtils.getExtension(pFilename);
    }

    /**
     * Returns the name from the specified filename.
     *
     * @param pFilename
     * @return
     */
    public static String getName(String pFilename) {
        return FilenameUtils.getName(pFilename);
    }

    /**
     * Returns true if the specified filename has the specified extension.
     *
     * @param pFilename
     * @param pExtension
     * @return
     */
    public static boolean hasExtension(String pFilename, String pExtension) {
        String fileExt = getExtension(pFilename);
        return pExtension.equals(fileExt);
    }

    /**
     * Returns the normalized filename, that is the path without any '.' or '..'
     * and with only system separators.
     *
     * @param pFilename
     * @return
     */
    public static String normalize(String pFilename) {
        return FilenameUtils.normalize(pFilename);
    }

    /**
     * Removes the extensions of the specified filenames.
     *
     * @param files
     * @return
     */
    public static ArrayList<String> removeExtension(ArrayList<String> pFilenameList) {
        ArrayList<String> out = new ArrayList<String>();
        for (int i = 0; i < pFilenameList.size(); ++i) {
            out.add(removeExtension(pFilenameList.get(i)));
        }
        return out;
    }

    /**
     * Removes the extension from the specified filename.
     *
     * @param pFilename
     * @return
     */
    public static String removeExtension(String pFilename) {
        return FilenameUtils.removeExtension(pFilename);
    }

    /**
     * Removes the extensions from the specified filenames.
     *
     * @param pFilenames
     * @return
     */
    public static String[] removeExtension(String[] pFilenames) {
        String[] out = new String[pFilenames.length];
        for (int i = 0; i < pFilenames.length; ++i) {
            out[i] = removeExtension(pFilenames[i]);
        }
        return out;
    }

    /**
     * Returns the filename with all forward slashes "/" replaced with the
     * system file separators.
     *
     * @param pFilename
     * @return
     */
    public static String replaceWithFileSeparator(String pFilename) {
        return pFilename.replaceAll("/", Matcher.quoteReplacement(File.separator));
    }

    /**
     * Returns the specified name with the specified extension.
     * 
     * @param pFilename
     * @param pExtension
     * @return
     */
    public static String setExtension(String pName, String pExtension) {
        String name = FilenameUtils.getBaseName(pName);
        if (!ObjectUtils.isObjectEmpty(pExtension)) {
            name += "." + pExtension;
        }
        return name;
    }
}