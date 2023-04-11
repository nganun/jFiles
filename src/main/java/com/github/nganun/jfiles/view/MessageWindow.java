package com.github.nganun.jfiles.view;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class MessageWindow extends JFrame {

    public JLabel info1;
    public JLabel info2 = new JLabel(" FROM: ");
    public JLabel info3 = new JLabel(" TO: ");

    public JProgressBar jProgressBar;

    public MessageWindow(String option) {
        info1 = new JLabel(" 正在" + option + "文件...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 465, 226);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayout(4, 1));

        info1 = new JLabel(" 正在" + option + "文件... 已完成 0%");
        info1.setFont(new Font("宋体", Font.PLAIN, 18));
        rootPanel.add(info1);

        jProgressBar = new JProgressBar();
        jProgressBar.setValue(20);
        jProgressBar.setForeground(new Color(50, 205, 50));
        jProgressBar.setFont(new Font("宋体", Font.PLAIN, 16));
        jProgressBar.setPreferredSize(new Dimension(320, 20));
        jProgressBar.setEnabled(false);
        jProgressBar.setOpaque(false);

        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        jPanel.add(jProgressBar);
        rootPanel.add(jPanel);

        info2.setFont(new Font("宋体", Font.PLAIN, 14));
        info3.setFont(new Font("宋体", Font.PLAIN, 14));
        rootPanel.add(info2);
        rootPanel.add(info3);

        rootPanel.setOpaque(true);
        setTitle("正在" + option + "文件");
        setResizable(false);
        rootPanel.setBackground(Color.WHITE);
        add(rootPanel);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        MessageWindow frame = new MessageWindow("(复制)");
        frame.setVisible(true);
    }
}
