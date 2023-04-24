package com.github.nganun.jfiles.model;

import javax.naming.NameNotFoundException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class PropertiesWindow extends JFrame {

    private JPanel contentPanel;
    public static PropertiesWindow _instance = null;
    public String path;
    public JTextField nameField, location;
    public JLabel imageLabel, sizeLabel, size, locationLabel, createTimeLabel, createTime, modifyTimeLabel,
                    modifyTime, accessTimeLabel, accessTime, typeLabel, type, includeLabel, include, fileSysLabel,
                    fileSys, usedSizeLabel, usedSize, availSizeLabel, availSize, totalSizeLabel, totalSize;
    public JPanel topPanel, middlePanel, bottomPanel;
    public JButton ensureBtn;
    public double used, available, total;

    private void init() {   // 文件界面初始化
        topPanel = new JPanel();
        topPanel.setBounds(10, 10, 330, 100);
        topPanel.setOpaque(false);
        topPanel.setLayout(null);

        nameField = new JTextField();
        nameField.setText("null");
        nameField.setFont(new Font("宋体", Font.PLAIN, 14));
        nameField.setEditable(false);
        nameField.setOpaque(false);
        nameField.setBounds(50, 40, 240, 30);

        topPanel.add(nameField);
        topPanel.setOpaque(true);
        topPanel.setBackground(Color.white);
        topPanel.setBorder(BorderFactory.createTitledBorder("文件名称"));
        getContentPane().add(topPanel);


        middlePanel = new JPanel();
        middlePanel.setOpaque(false);
        middlePanel.setBounds(10, 100, 330, 190);
        middlePanel.setLayout(null);
        middlePanel.setBorder(BorderFactory.createTitledBorder("大小及位置"));

        typeLabel = new JLabel();
        typeLabel.setLocation(10, 30);
        typeLabel.setSize(50, 30);
        typeLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        typeLabel.setOpaque(false);
        typeLabel.setText("类型：");

        type = new JLabel();
        type.setLocation(60, 30);
        type.setSize(300, 30);
        type.setFont(new Font("宋体", Font.PLAIN, 18));
        type.setOpaque(false);
        type.setText("文件夹");

        sizeLabel = new JLabel();
        sizeLabel.setLocation(10, 76);
        sizeLabel.setSize(50, 30);
        sizeLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        sizeLabel.setOpaque(false);
        sizeLabel.setText("大小");

        size = new JLabel();
        size.setLocation(60, 76);
        size.setSize(300, 30);
        size.setOpaque(false);
        size.setFont(new Font("宋体", Font.PLAIN, 18));
        size.setText("0");

        locationLabel = new JLabel("位置：");
        locationLabel.setBounds(10, 130, 60, 25);
        locationLabel.setOpaque(false);
        locationLabel.setFont(new Font("宋体", Font.PLAIN, 18));

        location = new JTextField();
        location.setSize(260, 20);
        location.setLocation(60, 120);
        location.setEditable(false);
        location.setOpaque(false);
        location.setText("C:\\Windows\\System32\\asd\\sdsd");
        location.setFont(new Font("宋体", Font.PLAIN, 18));

        middlePanel.add(typeLabel);
        middlePanel.add(type);
        middlePanel.add(sizeLabel);
        middlePanel.add(size);
        middlePanel.add(locationLabel);
        middlePanel.add(location);
        middlePanel.setOpaque(true);
        middlePanel.setBackground(Color.white);
        getContentPane().add(middlePanel);


        bottomPanel = new JPanel();
        bottomPanel.setBounds(10, 300, 300, 200);
        bottomPanel.setLayout(null);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("相关时间"));

        createTimeLabel = new JLabel("创建时间：");
        createTimeLabel.setBounds(10, 50, 90, 20);
        createTimeLabel.setFont(new Font("宋体", Font.PLAIN, 14));

        createTime = new JLabel("2015年10月30日，06:15:27");
        createTime.setBounds(100, 50, 200, 20);
        createTime.setFont(new Font("宋体", Font.PLAIN, 14));


        modifyTimeLabel = new JLabel("修改时间：");
        modifyTimeLabel.setBounds(10, 50, 90, 20);
        modifyTimeLabel.setFont(new Font("宋体", Font.PLAIN, 14));

        modifyTime = new JLabel("2015年10月30日，06:15:27");
        modifyTime.setBounds(100, 50, 200, 20);
        modifyTime.setFont(new Font("宋体", Font.PLAIN, 14));

        accessTimeLabel = new JLabel("访问时间：");
        accessTimeLabel.setBounds(10, 50, 90, 20);
        accessTimeLabel.setFont(new Font("宋体", Font.PLAIN, 14));

        accessTime = new JLabel("2015年10月30日，06:15:27");
        accessTime.setBounds(100, 50, 200, 20);
        accessTime.setFont(new Font("宋体", Font.PLAIN, 14));

        bottomPanel.add(createTimeLabel);
        bottomPanel.add(createTime);
        bottomPanel.add(modifyTimeLabel);
        bottomPanel.add(modifyTime);
        bottomPanel.add(accessTimeLabel);
        bottomPanel.add(accessTime);
        bottomPanel.setOpaque(true);
        bottomPanel.setBackground(Color.white);
        getContentPane().add(bottomPanel);

        ensureBtn = new JButton("确定");
        ensureBtn.setBounds(240, 510, 90, 30);
        ensureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        getContentPane().add(ensureBtn);
    }

    public PropertiesWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPanel);

        this.setTitle("文件属性");
        this.setBounds(500, 500, 366, 590);
        this.getContentPane().setLayout(null);
        init();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PropertiesWindow frame = new PropertiesWindow();
                frame.setVisible(true);
            }
        });
    }

}
