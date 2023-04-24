package com.github.nganun.jfiles.controller;

import com.github.nganun.jfiles.model.FileTablePanel;
import com.github.nganun.jfiles.model.FileTreePanel;

import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.Vector;

public class NavPanelController {

    FileTreePanel treePanel;
    FileTablePanel tablePanel;

    public NavPanelController(FileTreePanel treePanel) {
        this.treePanel = treePanel;
    }

    public NavPanelController(FileTablePanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    public void queryNode(DefaultMutableTreeNode findNode, String nodeName, boolean ifExpand) {
        for (int i = 0; i < findNode.getChildCount(); i ++) {
            if (findNode.getChildAt(i).toString().equals(nodeName)) {
                findNode = (DefaultMutableTreeNode) findNode.getChildAt(i);
                break;
            }
        }

        treePanel.tree.clearSelection();    // ***
        treePanel.tree.setSelectionPath(new TreePath(findNode.getPath()));
        if (ifExpand) {
            treePanel.tree.expandPath(new TreePath(findNode.getPath()));
        } else {
            treePanel.tree.collapsePath(new TreePath(findNode.getPath()));
        }
    }

    public void goAction(String inputPath, boolean ifExpand) {
        if (inputPath == "" || inputPath == null || inputPath.equals("")) {
            return;
        }

        int tempIndex = 0, i;
        DefaultMutableTreeNode findNode = treePanel.rootNode;
        String procdStr = null;

        for (i = 0; i < inputPath.length(); i++) {
            String temp = null;
            if (inputPath.charAt(i) == File.separatorChar) {
                temp = inputPath.substring(0, i + 1);
                procdStr = temp;
                String nodeName = temp.substring(tempIndex, temp.length() - 1);
                tempIndex = i + 1;
                for (int j = 0; j < findNode.getChildCount(); j++) {
                    findNode = (DefaultMutableTreeNode) findNode.getChildAt(j);
                }
            }
        }

        String leftStr = inputPath.substring(procdStr.length());
        if (!leftStr.equals("") && leftStr != null) {
            queryNode(findNode, leftStr, ifExpand);
        }
    }

    public void preAction(String inputPath) {
        if (inputPath == "" || inputPath == null || inputPath.equals("") || inputPath.length() <= 1) {
            return;
        }

        if (inputPath.charAt(inputPath.length() - 1) == File.separatorChar) {
            if (inputPath.charAt(inputPath.length() - 2) != ':') {
                inputPath = inputPath.substring(0, inputPath.length() - 1);
            }
        }

        int i = inputPath.length() - 1;
        for (i = inputPath.length() - 1; i >=0; i--) {
            if (inputPath.charAt(i) == File.separatorChar) {
                break;
            }
        }

        // Extract string, the last char is File.separatorChar
        inputPath = inputPath.substring(0, i + 1);

        goAction(inputPath, false);
    }

    // nextButton action
    public void nextAction(String inputPath) {
        // DONE expand current node
        goAction(inputPath, true);
        // TODO (feature expansion) 将此功能改成找到当前已选中节点的下一个相信兄弟节点
    }
    
    // findBtn action, 需要使用 FileTablePanel 构造并调用
    public void searchAction(String sname) {
        // Find the file in current folder and location it
        Vector<Vector<String>> data = new Vector<>();

        for (int i = 0; i < tablePanel.jTable.getRowCount(); i++) {
            Vector<String> retRow = new Vector<>();
            if (tablePanel.jTable.getValueAt(i, 1).equals(sname)) {
                for (int j = 0; j < tablePanel.jTable.getColumnCount(); j++) {
                    retRow.add((String) tablePanel.jTable.getValueAt(i, j));
                }
                data.add(retRow);
            }
        }

        Vector<String> columnNames1 = new Vector<>();
        columnNames1.add("  ");
        columnNames1.add("文件名");
        columnNames1.add("文件类型");
        columnNames1.add("大小");

        tablePanel.jTable.removeAll();
        DefaultTableModel model = new DefaultTableModel(data, columnNames1) {
            boolean[] columnEditables = new boolean[] {false, false, false, false};
        };

        tablePanel.jTable.setModel(model);
        tablePanel.jTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        tablePanel.jTable.getColumnModel().getColumn(0).setMinWidth(0);
        tablePanel.jTable.getColumnModel().getColumn(1).setPreferredWidth(360);
        tablePanel.jTable.getColumnModel().getColumn(1).setMinWidth(30);
        tablePanel.jTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        tablePanel.jTable.getColumnModel().getColumn(2).setMinWidth(30);
        tablePanel.jTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        tablePanel.jTable.getColumnModel().getColumn(3).setMinWidth(30);
        tablePanel.jTable.updateUI();
    }

}
