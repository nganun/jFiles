package com.github.nganun.jfiles.controller;

import com.github.nganun.jfiles.model.FileModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.io.File;
import java.util.Vector;

public class FileTableController {

    public JTable jTable;
    private Vector<String> columnNames = new Vector<>();
    private Vector<Vector<String>> data = new Vector<>();

    public FileTableController(JTable jTable) {
        this.jTable = jTable;
    }

    public void setColumnNames() {
        columnNames.add(" ");
        columnNames.add("文件名");
        columnNames.add("文件类型");
        columnNames.add("大小");
    }

    public void setWidthSize() {
        TableColumnModel model = jTable.getColumnModel();
        model.getColumn(0).setPreferredWidth(0);
        model.getColumn(0).setMinWidth(0);
        model.getColumn(1).setPreferredWidth(360);
        model.getColumn(1).setMinWidth(30);
        model.getColumn(2).setPreferredWidth(120);
        model.getColumn(2).setMinWidth(30);
        model.getColumn(3).setPreferredWidth(120);
        model.getColumn(3).setMinWidth(30);
    }
    
    public void update_tab(File[] files) {
        data.clear();

        for (int i = 0; i < files.length; i++) {
            Vector<String> vector = new Vector<>();
            vector.add("");
            vector.add(files[i].getName());
            vector.add(files[i].isFile() ? "文件" : "文件夹");
            String size = null;
            size = new FileModel(files[i].getPath()).getFileSize();
            if (size != null && size != "") {
                vector.add(size);
            }

            data.add(vector);
        }
    }

    public void setTable(File[] files) {
        update_tab(files);

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            boolean[] columnsEditable = {false, false, false, false};

            public boolean isCellEditable(int row, int column) {
                return columnsEditable[column];
            }
        };

        jTable.setModel(model);
        setWidthSize();
    }


}
