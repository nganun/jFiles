package com.github.nganun.jfiles.view;

import com.github.nganun.jfiles.controller.FileModelController;
import com.github.nganun.jfiles.controller.FileTableController;
import com.github.nganun.jfiles.controller.FileTreeController;
import com.github.nganun.jfiles.model.*;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MainWindow() {
        addListenerToMyMenuBar();
    }

    public void addListenerToMyMenuBar() {
        menuBar.newWindowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = MyNavPanel.pathTextField.getText();
                FileModelController.createMyFile(path);
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
