package com.github.nganun.jfiles.controller;

import com.github.nganun.jfiles.model.FileModel;
import com.github.nganun.jfiles.view.MessageWindow;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.text.DecimalFormat;

public class FileModelController {

    FileModel fileModel;

    File file = new File("");
    private MessageWindow messageWindow;
    private double percent = 0;

    public void paste(String from, String to, boolean operation) {
        String option = operation ? "复制" : "移动";

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(from);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(to);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        DataInputStream dis = new DataInputStream(fis);
        DataOutputStream dos = new DataOutputStream(fos);

        FileInputStream finalFis = fis;
        FileOutputStream finalFos = fos;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {

                    messageWindow = new MessageWindow(option);
                    messageWindow.info2.setText(" FROM: " + from);
                    messageWindow.info3.setText(" TO: " + to);
                    messageWindow.setLocationRelativeTo(null);
                    messageWindow.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {

                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            try {
                                dis.close();
                                dos.close();
                                finalFis.close();
                                finalFos.close();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                        @Override
                        public void windowIconified(WindowEvent e) {

                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {

                        }

                        @Override
                        public void windowActivated(WindowEvent e) {

                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        SwingUtilities.invokeLater(runnable);


        long copiedSize = 0;
        int size = 1024 * 10;
        byte[] buffer = new byte[size];
        File srcFile = new File(from);
        percent = 0;
        DecimalFormat df = new DecimalFormat("#.00");

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                messageWindow.info1.setText(" 正在" + option + "文件... 已完成 " + df.format(percent) + "%");
                messageWindow.jProgressBar.setValue((int) percent);
            }
        };

        double memoryInUsed;

        try {
            while (dis.read(buffer, 0, size) != -1) {
                dos.write(buffer, 0, size);
                copiedSize += size;
                if (srcFile.length() / size != 0) {
                    memoryInUsed = (srcFile.length() / size + 1) * size * 1.0;  // 文件实际占用空间
                } else {
                    memoryInUsed = srcFile.length();
                }

                percent = copiedSize * 100.0 / memoryInUsed;

                SwingUtilities.invokeLater(runnable1);
            }
            dis.close();
            dos.close();
            fis.close();
            fos.close();

            if (operation == false) {
                if (delete(from)) {
                    JOptionPane.showMessageDialog(null, "剪切成功!", "成功", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "文件已复制，删除源文件失败!", "剪切失败", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "复制成功!", "成功", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "复制失败!", "失败", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean delete(String src) {
        File delFile = new File(src);
        if (delFile.exists()) {
            return delFile.delete();
        }
        return false;
    }

    public static void createMyFile(String path) {
        if (new File(path).isFile()) {
            JOptionPane.showMessageDialog(null, "请选择一个目录创建文件", "提示", JOptionPane.INFORMATION_MESSAGE);
        }

        String newFileName = JOptionPane.showInputDialog("请输入文件唤名:");
        if (newFileName == null || newFileName.equals("")) {
            return;
        }

        File file = new File(path + File.separator + newFileName);
        if (file.canRead()) {
            JOptionPane.showMessageDialog(null, "已存在该文件，尝试重新输入新文件名", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                file.createNewFile();
                JOptionPane.showMessageDialog(null, file.getAbsolutePath() + "创建成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                throw new RuntimeException(e);
//                JOptionPane.showMessageDialog(null, "文件创建失败!", "异常", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void createMyDir(String path) {
        if (new File(path).isFile()) {
            JOptionPane.showMessageDialog(null, "请选择一个目录创建文件", "提示", JOptionPane.INFORMATION_MESSAGE);
        }

        String newDirName = JOptionPane.showInputDialog("请输入文件夹名：");
        if (newDirName == null || newDirName.equals("")) {
            return;
        }


        File f = new File(path + File.separator + newDirName);
        if(f.exists()) {
            JOptionPane.showMessageDialog(null, "已存在该文件夹，尝试重新输入新文件夹名", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            f.mkdirs();
            JOptionPane.showMessageDialog(null, f.getAbsoluteFile() +  " 创建成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static boolean deleteDirectory(String path) {
        boolean flag = true;
        File dirFile = new File(path);
        if (!dirFile.isDirectory()) {
            return flag;
        }

        File[] files = dirFile.listFiles();
        for (File file: files) {
            if (file.isFile()) {
                flag = delete(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                flag = deleteDirectory(file.getAbsolutePath());
            }

            if (!flag) {
                break;
            }
        }

        flag = dirFile.delete();
        return flag;
    }

}
