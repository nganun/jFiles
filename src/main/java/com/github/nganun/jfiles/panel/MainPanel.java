package com.github.nganun.jfiles.panel;

import javax.swing.*;
import java.awt.*;

public class MainPanel {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("文件管理器");

        jFrame.setLayout(new BorderLayout());

        JPanel jPanel = new JPanel();


        jFrame.add(new JTextArea("工具栏"), BorderLayout.NORTH);
        jFrame.add(new JTextArea("目录树"), BorderLayout.WEST);
        jFrame.add(new JTextArea("文件列表"), BorderLayout.CENTER);
        jFrame.add(new JTextArea("预览文件"), BorderLayout.EAST);
        jFrame.add(new JTextArea("快速命令"), BorderLayout.SOUTH);

        jFrame.setBounds(300, 200, 400, 400);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
