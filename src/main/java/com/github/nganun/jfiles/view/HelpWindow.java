package com.github.nganun.jfiles.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelpWindow  extends JFrame {

    private JTextArea area;
    private JScrollPane panel;
    private Font font;

    public HelpWindow() {
        setTitle("系统帮助");
        setSize(480, 300);
        setLocationRelativeTo(null);
        init();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
    }

    private void init() {
        area = new JTextArea();
        area.setEditable(false);

        File file = new File(".\\docs\\Help");
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];

        try (FileInputStream fis = new FileInputStream(file);) {
            fis.read(fileContent);
            area.setText(new String(fileContent, "UTF-8"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        font = new Font("宋体", Font.PLAIN, 12);
        area.setFont(font);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        panel = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(panel);
    }

    public static void main(String[] args) {
        new HelpWindow();
    }
}
