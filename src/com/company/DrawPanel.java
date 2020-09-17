package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Drawable h = new House(960*4/5, 540*4/5, 1000, 10, 500, 10, 50, 30);
        h.draw(g2);
    }
}
