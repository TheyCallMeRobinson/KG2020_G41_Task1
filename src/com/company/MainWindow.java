package com.company;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        DrawPanel mp = new DrawPanel();
        this.add(mp);
    }
}
