package com.mar.iotools.directory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.mar.framework.core.logging.LogUtils;
import com.mar.framework.core.utils.ObjectUtils;

public class DirectoryUtils {

    /**
     * Returns the list of file paths in the specified folder (and not in the
     * subfolders).
     *
     * @param pFolderPath
     * @return
     */
    public static ArrayList<String> listFilesInFolder(String pFolderPath) {
        return listFilesInFolder(pFolderPath, false, null);
    }

    /**
     * Returns the list of file paths in the specified folder. If
     * pIncludeSubfolders is set to true, the subfolders are also explored.
     *
     * @param pFolderPath
     * @param pIncludeSubfolders
     * @return
     */
    public static ArrayList<String> listFilesInFolder(String pFolderPath, boolean pIncludeSubfolders) {
        return listFilesInFolder(pFolderPath, pIncludeSubfolders, null);
    }

    /**
     * Returns the list of files in the specified folder that match the
     * specified extension. If pIncludeSubfolders is set to true, the subfolders
     * are also explored.
     *
     * @param pFolderPath
     * @param pIncludeSubfolders
     * @param pExtension
     * @return
     */
    public static ArrayList<String> listFilesInFolder(String pFolderPath, boolean pIncludeSubfolders,
            String pExtension) {
        ArrayList<String> list = new ArrayList<String>();

        /*
         * depth = 0 -> only the specified file/folder is returned. To get all
         * files inside a folder, depth must be set to 1.
         */
        int depth = 1;
        if (pIncludeSubfolders) {
            depth = Integer.MAX_VALUE;
        }

        String matcherString = "glob:**";
        if (!ObjectUtils.isObjectEmpty(pExtension)) {
            matcherString += "." + pExtension;
        }
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(matcherString);

        try (Stream<Path> paths = Files.walk(Paths.get(pFolderPath), depth)) {
            // paths.filter(Files::isRegularFile).filter(path ->
            // matcher.matches(path))
            // .forEach(path ->
            // System.out.println(path.normalize().toString()));
            paths.filter(Files::isRegularFile).filter(path -> matcher.matches(path))
                    .forEach(path -> list.add(path.normalize().toString()));
        } catch (IOException e) {
            LogUtils.logError(DirectoryUtils.class, "IOException while listing files in folder [" + pFolderPath + "]",
                    e);
        }

        return list;
    }
}
