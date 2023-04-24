package com.github.nganun.jfiles.main;

import com.github.nganun.jfiles.Main;
import com.github.nganun.jfiles.view.MainWindow;

public class RunExplorerThread extends Thread {

    @Override
    public void run() {
        super.run();

        new MainWindow();
    }
}
