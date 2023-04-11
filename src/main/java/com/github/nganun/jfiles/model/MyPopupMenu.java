package com.github.nganun.jfiles.model;

import javax.swing.*;
import java.awt.*;

public class MyPopupMenu extends JPopupMenu {

    JPopupMenu jPopupMenu = this;
    public JMenuItem[] jMenuItems = new JMenuItem[100];
    private int itemCount = 0;

    public MyPopupMenu() {
        jMenuItems[0] = setPopupMenu("打开文件夹");
        jMenuItems[1] = setPopupMenu("复制");
        jMenuItems[2] = setPopupMenu("剪切");
        jMenuItems[3] = setPopupMenu("粘贴");
        jMenuItems[4] = setPopupMenu("剪切");
        jMenuItems[5] = setPopupMenu("新建文件");
        jMenuItems[6] = setPopupMenu("新建文件夹");
        jMenuItems[7] = setPopupMenu("文件属性");

        jPopupMenu.add(jMenuItems[0]);
        jPopupMenu.add(jMenuItems[1]);
        jPopupMenu.add(jMenuItems[2]);
        jPopupMenu.add(jMenuItems[3]);
        jPopupMenu.add(jMenuItems[4]);
        jPopupMenu.addSeparator();
        jPopupMenu.add(jMenuItems[5]);
        jPopupMenu.add(jMenuItems[6]);
        jPopupMenu.addSeparator();
        jPopupMenu.add(jMenuItems[7]);

        jMenuItems[0].setPreferredSize(new Dimension(160, 20));
        jMenuItems[3].setEnabled(false);

        itemCount = 8;
    }

    public static JMenuItem setPopupMenu(String name) {
        JMenuItem jMenuItem = new JMenuItem(name);
        jMenuItem.setName(name);
        return jMenuItem;
    }

    public int getMenuItemsCount() {
        return itemCount;
    }

}
