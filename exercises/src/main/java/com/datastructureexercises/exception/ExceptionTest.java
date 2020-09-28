package com.datastructureexercises.exception;

import com.jcraft.jsch.ChannelSftp;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Administrator
 */
public class ExceptionTest {
    ChannelSftp sftp = null;

    @Test
    public void exception1Test() {
        String a = "";
        if (a == null) {
            throw new RuntimeException("抛出异常");
        }
    }

    @Test
    public void inputStream() {
        File file = new File("hello.txt");
        try {
            InputStream fileInputStream = new FileInputStream(file);
            sftp.put(fileInputStream, "hello.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
