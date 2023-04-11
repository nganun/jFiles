package com.github.nganun.jfiles.model;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class FileModel extends File {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    File file = this;
    File[] files;

    public FileModel(String pathname) {
        super(pathname);

        files = file.listFiles();
    }

    public String getFileName() {
        return super.getAbsolutePath();
    }

    public File getMyFile() {
        return file;
    }

    public File[] getMyListFiles() {
        return files;
    }

    public int getFileChildCount() {
        return files.length;
    }

    public String getFileSize() {
        String fileSize = "";
        if (super.exists() && super.isFile()) {
            long length = super.length();
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            if (length < (2 << 10)) {
                fileSize = decimalFormat.format(length) + "B";
            } else if (length < (1024 * 1024)) {
                fileSize = decimalFormat.format(length / 1024) + "KB";
            } else if (length < (1024 * 1024 * 1024)) {
                fileSize = decimalFormat.format(length / (1024 * 1024)) + "MB";
            } else {
                fileSize = decimalFormat.format(length / (1024 * 1024 * 1024)) + "MB";
            }
        } else if (super.exists() && super.isDirectory()) {
            fileSize = "";
        } else {
            fileSize = "0B";
        }
        return fileSize;
    }

    public String getFilePath() {
        return super.getAbsolutePath();
    }

    public ImageIcon getSmallFileIcon() {
        return null;
    }

    public ImageIcon getLargeFileIcon() {
        return null;
    }

    // 读取文件创建时间
    public static String getCreateTime(String filePath) {
        Path path = Paths.get(filePath);
        Calendar calendar = Calendar.getInstance();
        BasicFileAttributes attr;

        try {
            attr = Files.readAttributes(path, BasicFileAttributes.class);
            calendar.setTimeInMillis(attr.creationTime().toMillis());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getModifiedTime(String filePath) {
        Calendar calendar = Calendar.getInstance();
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getLatestAccessTime(String filePath) {
        Path path = Paths.get(filePath);
        Calendar calendar = Calendar.getInstance();
        BasicFileAttributes attrs;
        try {
            attrs = Files.readAttributes(path, BasicFileAttributes.class);
            calendar.setTimeInMillis(attrs.lastAccessTime().toMillis());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return simpleDateFormat.format(calendar.getTime());
    }

    public static void main(String[] args) {
        FileModel fileModel = new FileModel("C:/Users/ZZ0C0D672/");
        for(File file: fileModel.files) {
            System.out.println(file.getAbsoluteFile() + "\t\t" + file.length());
            try {
                BasicFileAttributes attr = Files.readAttributes(Paths.get(file.getAbsolutePath()), BasicFileAttributes.class);
                System.out.println(attr.creationTime());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
