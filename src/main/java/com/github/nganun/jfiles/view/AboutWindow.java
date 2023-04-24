package com.github.nganun.jfiles.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AboutWindow extends JFrame {

    private JPanel contentPanel;

    public AboutWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 477, 347);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        JLabel nameLabel = new JLabel("软件名称：");
        nameLabel.setBounds(22, 20, 93, 29);

        JLabel versionLabel = new JLabel("版本：");
        versionLabel.setBounds(22, 47, 93, 34);

        JLabel releaseDateLabel = new JLabel("release date:");
        releaseDateLabel.setBounds(22, 78, 93, 29);

        JLabel authorLabel = new JLabel("作者：");
        authorLabel.setBounds(22, 108, 93, 27);

        JLabel descriptionLabel = new JLabel("详细：");
        descriptionLabel.setBounds(22, 145, 58, 15);

        contentPanel.add(nameLabel);
        contentPanel.add(versionLabel);
        contentPanel.add(releaseDateLabel);
        contentPanel.add(authorLabel);
        contentPanel.add(descriptionLabel);


        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("    本系统是一个使用 Java 编辑语言，通过 Swing 与美化包 beautyeye 结合构建 GUI，基于 MVC 架构" +
                "的文件资源管理器。通过近两个星期的努力，该系统已基本具备对文件的基本操作，包括复制，剪切，删除，查找，查看属性等相关信息" +
                "等等。MVC 的架构，加上多线程对耗资源时间的优化。该项目有比较好的易读着性和扩展性。一切功能还可以继续完善。");

        contentPanel.add(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(32, 170, 409, 129);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        textArea.setLineWrap(true);         // 自动换行
        textArea.setWrapStyleWord(true);    // 断行不断字

        contentPanel.add(scrollPane);

        JLabel nameValueLabel = new JLabel("FileExplorer");
        nameValueLabel.setBounds(172, 20, 254, 29);

        JLabel versionValueLabel = new JLabel("V1.0");
        versionValueLabel.setBounds(172, 47, 254, 34);

        JLabel releaseDateValueLabel = new JLabel("2019-07-04");
        releaseDateValueLabel.setBounds(172, 78, 254, 29);

        JLabel authorValueLabel = new JLabel("Qian KUn");
        authorValueLabel.setBounds(172, 108, 254, 27);

        contentPanel.add(nameValueLabel);
        contentPanel.add(versionValueLabel);
        contentPanel.add(releaseDateValueLabel);
        contentPanel.add(authorValueLabel);

        setTitle("About the System");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AboutWindow frame = new AboutWindow();
                frame.setVisible(true);
            }
        });
    }
}
