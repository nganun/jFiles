package com.github.nganun.jfiles.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

public class MyMenuBar extends JMenuBar {

    public static Dimension menuDimension = new Dimension(120, 30);
    public static Dimension itemDimension = new Dimension(170, 24);
    public static Font font = new Font("宋体", Font.PLAIN, 15);
    public static JMenuBar menuBar;
    public static JMenu fileMenu, operateMenu, viewMenu, aboutMenu;
    public static JMenuItem newDirItem, newFileItem, newWindowItem, queryFileItem, exitItem;
    public static JMenuItem copyItem, moveItem, deleteItem, propertyItem;
    public static JMenuItem listItem, tiledItem;
    public static JMenuItem aboutItem, helpItem;

    public MyMenuBar() {

        menuBar = new JMenuBar();
        // 文件
        fileMenu = new JMenu("文件");
        fileMenu.setFont(font);
        ImageIcon openIcon = new ImageIcon("images/icons/open.png");
        openIcon.setImage(openIcon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
        fileMenu.setIcon(openIcon);
        fileMenu.setMnemonic('Y');
        fileMenu.setPreferredSize(menuDimension);

        newFileItem = new JMenuItem("新建文件");
        newFileItem.setMnemonic('F');
        newFileItem.setPreferredSize(itemDimension);
        newFileItem.setAccelerator(KeyStroke.getKeyStroke('F', InputEvent.CTRL_MASK));

        newDirItem = new JMenuItem("新建文件夹");
        newDirItem.setMnemonic('N');
        newDirItem.setPreferredSize(itemDimension);
        newDirItem.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK));

        newWindowItem = MyJMenuItem("打开新窗口", 'W');
        queryFileItem = MyJMenuItem("查找文件", 'Q');
        exitItem = MyJMenuItem("退出", 'E');

        fileMenu.add(newFileItem);
        fileMenu.add(newDirItem);
        fileMenu.add(newWindowItem);
        fileMenu.add(queryFileItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        // 操作
        operateMenu = new JMenu("操作");
        operateMenu.setFont(font);
        ImageIcon operateIcon = new ImageIcon("images/icons/operate.png");
        operateIcon.setImage(operateIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        operateMenu.setIcon(operateIcon);
        operateMenu.setMnemonic('U');
        operateMenu.setPreferredSize(menuDimension);

        copyItem = MyJMenuItem("复制文件", 'C');
        moveItem = MyJMenuItem("移动文件", 'M');
        deleteItem = MyJMenuItem("删除文件", 'D');
        propertyItem = MyJMenuItem("文件属性", 'P');

        operateMenu.add(copyItem);
        operateMenu.add(moveItem);
        operateMenu.add(deleteItem);
        operateMenu.add(propertyItem);

        menuBar.add(operateMenu);

        // 查看
        viewMenu = MyJMenu("查看", 'I', "check.png");
        listItem = MyJMenuItem("列表显示", 'L');
        tiledItem = MyJMenuItem("平铺显示", 'T');

        viewMenu.add(listItem);
        viewMenu.add(tiledItem);

        menuBar.add(viewMenu);

        // 关于
        aboutMenu = MyJMenu("关于", 'O', "about.png");
        aboutItem = MyJMenuItem("关于本系统", 'A');
        helpItem = MyJMenuItem("系统帮助", 'H');

        aboutMenu.add(aboutItem);
        aboutMenu.add(helpItem);

        menuBar.add(aboutMenu);

        Color menuColor = new Color(236, 248, 255);
        fileMenu.setOpaque(true);
        fileMenu.setBackground(menuColor);
        operateMenu.setOpaque(true);
        operateMenu.setBackground(menuColor);
        viewMenu.setOpaque(true);
        viewMenu.setBackground(menuColor);
        aboutMenu.setOpaque(true);
        aboutMenu.setBackground(menuColor);

        this.add(menuBar);

//        JFrame jFrame = new JFrame();
//        jFrame.add(this);
//        jFrame.setSize(300, 400);
//        jFrame.setVisible(true);
    }
    
    public static JMenuItem MyJMenuItem(String itemName, Character mnemonic) {
        JMenuItem item = new JMenuItem(itemName);
        item.setMnemonic(mnemonic);
        item.setPreferredSize(itemDimension);
        item.setAccelerator(KeyStroke.getKeyStroke(mnemonic, InputEvent.CTRL_MASK));
        return item;
    }

    public static JMenu MyJMenu(String menuName, Character mnemonic, String image) {
        JMenu menu = new JMenu(menuName);
        menu.setFont(font);
        ImageIcon imageIcon = new ImageIcon("image/icons/" + image);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        menu.setIcon(imageIcon);
        menu.setMnemonic(mnemonic);
        menu.setPreferredSize(menuDimension);
        return menu;
    }

    public static void main(String[] args) {
        new MyMenuBar();
    }

}
