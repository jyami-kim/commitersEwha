package com.jyami.commitersewha.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jyami on 2020/10/08
 */
public class TestResourceLoader {
    public static byte[] readFile(String filePath) {
        InputStream resourceAsStream = TestResourceLoader.class.getClassLoader()
                .getResourceAsStream(filePath);
        try {
            assert resourceAsStream != null;
            return IOUtils.toByteArray(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("file IO 실패");
        }
    }
}
