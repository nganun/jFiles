package com.github.nganun.jfiles.model;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;

public class FileTreePanel {
    public JTree tree;
    public DefaultMutableTreeNode rootNode;

    public FileTreePanel() {
        // 获得系统文件
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        File root = fileSystemView.getFiles(fileSystemView.getHomeDirectory(), true)[0];
        rootNode = new DefaultMutableTreeNode(root);
        tree = new JTree(rootNode);
        tree.setFont(new Font("Consolas", Font.PLAIN, 14));
        tree.setRowHeight(20);
        tree.setOpaque(true);
        // 设置仅单选
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setExpandsSelectedPaths(false);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
        jFrame.setBounds(200, 200, 300, 400);
        JScrollPane jScrollPane = new JScrollPane(new FileTreePanel().tree);
        jFrame.add(jScrollPane, BorderLayout.CENTER);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
