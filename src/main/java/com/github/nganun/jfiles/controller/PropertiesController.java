package com.github.nganun.jfiles.controller;

import com.github.nganun.jfiles.model.FileModel;
import com.github.nganun.jfiles.model.PropertiesWindow;

import java.io.File;

public class PropertiesController {

    private PropertiesWindow window;

    public PropertiesController(PropertiesWindow window) {
        this.window = window;
    }

    void init() {
        window.setVisible(true);
    }

    public void update(String path) {
        FileModel file = new FileModel(path);
        if (path.charAt(1) == ':') {
            String disk = "本地磁盘（" + path.charAt(0) + "）";
            window.nameField.setText(disk);
        } else {
            window.nameField.setText(file.getName());
        }

        window.type.setText(file.isDirectory() ? "文件夹" : "文件");
        window.size.setText(file.getFileSize());
        window.location.setText(path);

        window.createTime.setText(FileModel.getCreateTime(path));
        window.modifyTime.setText(FileModel.getModifiedTime(path));
        window.accessTime.setText(FileModel.getLatestAccessTime(path));
    }
}
