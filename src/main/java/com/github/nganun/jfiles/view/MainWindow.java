package com.github.nganun.jfiles.view;

import com.github.nganun.jfiles.controller.*;
import com.github.nganun.jfiles.main.RunExplorerThread;
import com.github.nganun.jfiles.model.*;
import jdk.nashorn.internal.scripts.JO;
import sun.security.mscapi.CPublicKey;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class MainWindow extends JFrame implements ActionListener, TreeSelectionListener, TreeWillExpandListener {

    private JFrame jFrame = this;
    public JPanel contPanel = new JPanel();
    public JPanel navPanel = new JPanel();
    public JPanel bodyPanel = new JPanel();
    public JPanel footPanel = new JPanel();

    private FileTablePanel tablePanel;
    private FileTreePanel treePanel;
    private MyMenuBar menuBar = new MyMenuBar();
    private NavigatePanel MyNavPanel = new NavigatePanel();
    private JLabel footLabel = new JLabel(" ▲ " + "0" + "个项目");
    public FileTreeController fileTreeController;
    public FileTableController fileTableController;
    public MyPopupMenu jPopupMenu = new MyPopupMenu();

    //
    private static String srcFilePath = null;
    private static String destFilePath = null;
    private static boolean copyFlag = true;

    class copyThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                new FileModelController().paste(srcFilePath, destFilePath, copyFlag);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                srcFilePath = null;
                destFilePath = null;
                copyFlag = true;
            }
        }
    }

    public MainWindow() {
        addListenerToMyMenuBar();
    }

    public void addListenerToMyMenuBar() {

        menuBar.newFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 用户选择了 新建文件 菜单项");
                String path = MyNavPanel.pathTextField.getText();
                FileModelController.createMyFile(path);
            }
        });

        menuBar.newDirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 新建文件夹");
                String path = MyNavPanel.pathTextField.getText();
                FileModelController.createMyDir(path);
            }
        });

        menuBar.newWindowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String path = MyNavPanel.pathTextField.getText();
//                FileModelController.createMyFile(path);
                System.out.println(">>> 用户选择了 打开新窗口 菜单项");
                new RunExplorerThread().start();
            }
        });

        menuBar.queryFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 查询文件");
                String keyword = JOptionPane.showInputDialog("Please input the file name you want to search:");
                if (keyword == null || keyword.equals("")) {
                    JOptionPane.showMessageDialog(null, "No input content", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    new NavPanelController(tablePanel).searchAction(keyword);
                }
            }
        });

        menuBar.exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 退出菜单");
                System.exit(1);
            }
        });

        menuBar.copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 复制文件");
                srcFilePath = JOptionPane.showInputDialog("Please input the source file path:");
                destFilePath = JOptionPane.showInputDialog("Please input the dest file path:");
                copyFlag = true;
                if (srcFilePath !=null && !srcFilePath.equals("") && destFilePath !=null && !destFilePath.equals("")) {
                    new copyThread().start();
                }
            }
        });

        menuBar.moveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 移动文件");
                srcFilePath = JOptionPane.showInputDialog("Please input source file path:");
                destFilePath = JOptionPane.showInputDialog("Please input dest file path:");
                copyFlag = false;
                if (srcFilePath != null && !srcFilePath.equals("") && destFilePath != null && !destFilePath.equals("")) {
                    new copyThread().start();
                }
            }
        });

        menuBar.deleteItem.addActionListener(new ActionListener() {
            @SuppressWarnings("static-access")
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = JOptionPane.showInputDialog("请输入查询属性的有效文件（夹）路径：");
                if (path == null || path.equals("")) {
                    // do nothing
                } else if (new File(path).isFile()) {
                    System.out.println(">> 删除文件");
                    if (new FileModelController().delete(path)) {
                        JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (new File(path).isDirectory()) {
                    System.out.println(">> 删除文件夹");
                    int confirm = JOptionPane.showConfirmDialog(null, "你确定要删除目录 " + path +
                            " 下所有文件吗？");
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (FileModelController.deleteDirectory(path)) {
                            JOptionPane.showMessageDialog(null, "删除成功");
                        } else {
                            JOptionPane.showMessageDialog(null, "删除失败");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败！请选择一个文件进行邮件！",
                            "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        menuBar.propertyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 文件属性");
                String path = JOptionPane.showInputDialog("请输入查询属性的有效文件（夹）路径：");
                if (path != null && !path.equals("")) {
                    PropertiesWindow propertiesWindow = new PropertiesWindow();
                    PropertiesController controller = new PropertiesController(propertiesWindow);
                    controller.update(path);
                }
            }
        });

        menuBar.listItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 列表显示");
            }
        });

        menuBar.tiledItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 平铺显示");
            }
        });

        menuBar.aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 关于本系统");
                new AboutWindow();
            }
        });

        menuBar.helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(">> 系统帮助");
//                new HelpWindow();
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

    }

    @Override
    public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {

    }

    @Override
    public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {

    }
}
