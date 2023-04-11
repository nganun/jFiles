package com.github.nganun.jfiles.model;

import javax.swing.*;
import java.awt.*;

public class NavigatePanel extends JPanel {

    JPanel panel = this;
    public JButton preBtn = new JButton("上一页");
    public JButton nextBtn = new JButton("下一页");
    public JTextField pathTextField = new JTextField();
    public JButton goBtn = new JButton("Go");
    public JTextField searchField = new JTextField();
    public JButton searchBtn = new JButton("Search");

    public NavigatePanel() {
//        setLayout(new GridLayout(1, 1, 0, 0));
        this.setBackground(Color.white);
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());

        preBtn.setBounds(10, 21, 70, 28);
        preBtn.setOpaque(false);
        nextBtn.setBounds(85, 21, 70, 28);
        nextBtn.setOpaque(false);

        pathTextField.setFont(new Font("宋体", Font.PLAIN, 16));
        pathTextField.setColumns(50);

        goBtn.setBounds(717, 21, 70, 28);
        goBtn.setOpaque(false);

        searchField.setFont(new Font("宋体", Font.PLAIN, 16));
        searchField.setColumns(20);

        searchBtn.setBounds(717, 21, 56, 28);
        ImageIcon imageIcon = new ImageIcon("image/icons/search.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));
        searchBtn.setIcon(imageIcon);
        searchBtn.setOpaque(false);

        panel.add(preBtn);
        panel.add(nextBtn);
        panel.add(pathTextField);
        panel.add(goBtn);
        panel.add(searchField);
        panel.add(searchBtn);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(200, 300);
        jFrame.add(new NavigatePanel());
        jFrame.setVisible(true);
    }

}
