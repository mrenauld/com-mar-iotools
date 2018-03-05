package com.mar.iotools.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TestFilepathUtils {

    @Test
    public void testGetFileExtension() {
        String path = "somehting/something/test.txt";
        String ext = FilepathUtils.getExtension(path);
        assertThat(ext).isEqualTo("txt");

        path = "somehting/something/test.bu.txt";
        ext = FilepathUtils.getExtension(path);
        assertThat(ext).isEqualTo("txt");

        path = "somehting/something/test";
        ext = FilepathUtils.getExtension(path);
        assertThat(ext).isEqualTo("");

        path = "somehting/some.thing/test";
        ext = FilepathUtils.getExtension(path);
        assertThat(ext).isEqualTo("");
    }

    @Test
    public void testGetFilename() {
        String path = "somehting/something/test.txt";
        String filename = FilepathUtils.getName(path);
        assertThat(filename).isEqualTo("test.txt");
    }

    @Test
    public void testRemoveExtension() {
        String path = "somehting/something/test.txt";
        String pathWithoutExt = FilepathUtils.removeExtension(path);
        assertThat(pathWithoutExt).isEqualTo("somehting/something/test");

        path = "somehting/something/test.bu.txt";
        pathWithoutExt = FilepathUtils.removeExtension(path);
        assertThat(pathWithoutExt).isEqualTo("somehting/something/test.bu");

        path = "somehting/something/test";
        pathWithoutExt = FilepathUtils.removeExtension(path);
        assertThat(pathWithoutExt).isEqualTo("somehting/something/test");

        path = "somehting/some.thing/test";
        pathWithoutExt = FilepathUtils.removeExtension(path);
        assertThat(pathWithoutExt).isEqualTo("somehting/some.thing/test");
    }

}
