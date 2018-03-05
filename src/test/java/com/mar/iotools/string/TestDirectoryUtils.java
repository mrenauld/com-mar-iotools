package com.mar.iotools.string;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.mar.iotools.directory.DirectoryUtils;

public class TestDirectoryUtils {

    @Test
    public void testListFilesInFolder() {
        ArrayList<String> list = DirectoryUtils.listFilesInFolder("./src/main/resources/testfolder/");
        Assertions.assertThat(list.size()).isEqualTo(2);

        list = DirectoryUtils.listFilesInFolder("./src/main/resources/testfolder/", true);
        Assertions.assertThat(list.size()).isEqualTo(8);

        list = DirectoryUtils.listFilesInFolder("./src/main/resources/testfolder/", true, "1");
        Assertions.assertThat(list.size()).isEqualTo(3);

        list = DirectoryUtils.listFilesInFolder("./src/main/resources/testfolder/", true, "3");
        Assertions.assertThat(list.size()).isEqualTo(1);

    }

}
