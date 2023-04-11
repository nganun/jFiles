package com.github.nganun.jfiles.model;

import com.github.nganun.jfiles.controller.FileTableController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FileTablePanel extends JPanel {

    public JTable jTable;
    public FileTableController controller;

    public FileTablePanel() {
        setLayout(new BorderLayout(0, 0));
        jTable = new JTable();
        jTable.setModel(new DefaultTableModel(new Object[][]{{"test0", "test1", "test2", "test3"}}, new String[] {
                " ", "\u6587\u4EF6\u540D", "\u6587\u4EF6\u7C7B\u578B", "\u5927\u5C0F" // 文件名， 文件类型， 大小
        }) {
            boolean[] columnsEditable = {false, false, false, false};
            public boolean isCellEditable(int row, int column) {return columnsEditable[column];};
        });

        add(jTable, BorderLayout.CENTER);
        jTable.setSelectionBackground(new Color(142, 225, 253));
        jTable.setSelectionForeground(Color.BLACK);
        jTable.setBorder(null);
        jTable.setFont(new Font("Consolas", Font.PLAIN, 14));
        jTable.setShowGrid(false);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
        jFrame.setSize(600, 400);
        JScrollPane jScrollPane = new JScrollPane(new FileTablePanel().jTable);
        jFrame.add(jScrollPane, BorderLayout.CENTER);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
