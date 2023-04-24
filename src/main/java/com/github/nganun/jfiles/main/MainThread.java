package com.github.nganun.jfiles.main;

public class MainThread {

    public static void main(String[] args) {
        RunExplorerThread runExplorerThread = new RunExplorerThread();
        runExplorerThread.start();
    }
}
