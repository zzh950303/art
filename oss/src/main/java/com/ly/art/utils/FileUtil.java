package com.ly.art.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public final class FileUtil {

    public static String getFileType(final String fileName) {
        String fileType = "";
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            fileType = fileName.substring(index + 1);
        }
        return fileType;
    }

    public static void response(File file, HttpServletResponse response) throws Exception {
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis)) {
            String fileName = new String(file.getName().getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            response.setContentType("application/force-download;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            throw new Exception();
        }
        file.delete();
    }
}
