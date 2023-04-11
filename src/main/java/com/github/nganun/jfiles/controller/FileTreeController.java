package com.github.nganun.jfiles.controller;

import com.github.nganun.jfiles.model.FileModel;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.io.File;

public class FileTreeController {

    private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();

    private JTree jTree;

    public int itemNum = 0; // footLabel 的文件数量
    public String itemPath = null; // 导航栏内容

    public FileTreeController(JTree jTree, DefaultMutableTreeNode root) {
        this.rootNode = root;
        this.jTree = jTree;
    }

    public void InitTree() {
        rootNode.removeAllChildren();

        for(File file : File.listRoots()) {
            DefaultMutableTreeNode temp_node = new DefaultMutableTreeNode(file.toString().substring(0, 2));
            temp_node.add(new DefaultMutableTreeNode("(Empty)"));
            rootNode.add(temp_node);
        }
    }
    
    boolean findNode(DefaultMutableTreeNode currentNode, DefaultMutableTreeNode tempNode) {
        for (int i = 0; i < currentNode.getChildCount(); i++) {
            if (currentNode.getChildAt(i).toString().equals(tempNode.toString())) {
                return true;
            }
        }
        return false;
    }

    int findNode2(DefaultMutableTreeNode currentNode, DefaultMutableTreeNode tempNode) {
        for (int i = 0; i < currentNode.getChildCount(); i++) {
            if (currentNode.getChildAt(i).toString().equals(tempNode.toString())) {
                return i;
            }
        }
        return 0;
    }

    // 更新文件目录树的核心函数
    public boolean reloadTree(String path, DefaultMutableTreeNode currentNode) {
        FileModel fileDir = new FileModel(path);
        currentNode.removeAllChildren();

        if (fileDir.getMyFile().isDirectory()) {

            if (fileDir.getFileChildCount() > 0) {
                currentNode.removeAllChildren();
                // 该目录下有几个项目
                int count = fileDir.getFileChildCount();
                itemNum = count;

                for (int i = 0; i < count; i++) {
                    // 是目录
                    if (fileDir.getMyListFiles()[i].isDirectory() && fileDir.getMyListFiles()[i].canRead()) {
                        DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(fileDir.getMyListFiles()[i].getName());
                        tempNode.add(new DefaultMutableTreeNode("(Empty)"));
                        currentNode.insert(tempNode, findNode2(currentNode, tempNode));
                    }
                }

                for (int i = 0; i < count; i++) {
                    if (fileDir.getMyListFiles()[i].isFile()) {
                        currentNode.add(new DefaultMutableTreeNode(fileDir.getMyListFiles()[i].getName()));
                    }
                }

                return true;
            } else {
                JOptionPane.showMessageDialog(null, "该文件夹为空。", "提示", JOptionPane.INFORMATION_MESSAGE);
                itemNum = 0;
                return true;
            }
        } else if (currentNode != rootNode) {
            return true;
        }

        return false;
    }

    // 返回该文件是否能访问，并将选中文件（夹）下的文件个数传递给 itemNum (0 or count)
    public int CheckTree(String path, DefaultMutableTreeNode currentNode) {
        FileModel fileDir = new FileModel(path);

        if(fileDir.getMyFile().isDirectory()) {
            if(fileDir.getFileChildCount() > 0) {
                int count = fileDir.getFileChildCount();
                itemNum = count;
                return 1;
            } else {
                itemNum = 0;
                return 1;
            }
        } else if (currentNode != rootNode) {
            itemNum = 1;
            return 1; // 选中的是文件夹
        }

        return 0;
    }

    public String treeWillExpand(TreeExpansionEvent event) {
        TreePath selectedNodePath = event.getPath();

        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedNodePath.getLastPathComponent();
        String abs_path = "";
        if (selectedNode != null) {
            TreePath paths = selectedNodePath;
            for (int i = 0; i < paths.getPathCount(); i++) {
                abs_path += paths.getPath()[i] + File.separator;
            }
            if (abs_path != "" && abs_path != null) {
                if (reloadTree(abs_path, selectedNode)) {
                    return abs_path;
                }
            }
        } else {

        }
        return null;
    }

    public void treeWillCollapse(TreeExpansionEvent event) {
        TreePath selectedNode2 = event.getPath();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedNode2.getLastPathComponent();
        selectedNode.removeAllChildren();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jTree.updateUI();
            }
        });
        selectedNode.add(new DefaultMutableTreeNode("正在加载 ...."));
    }

    public String valueChanged(TreeSelectionEvent event) {
        String abs_path = "";
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
        if (selectedNode != null) {
            TreePath paths = jTree.getSelectionPath();
            for (int i = 0; i < paths.getPathCount(); i++) {
                abs_path += paths.getPath()[i] + File.separator;
            }
            if(abs_path != "" && abs_path != null) {
                if (CheckTree(abs_path, selectedNode) == 1) {
                    return abs_path;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {

        for(File file : File.listRoots()) {
            System.out.println(file.getAbsoluteFile());
        }
    }


}
