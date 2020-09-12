package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Drawable h = new House(500, 500, 500, 100, 250, 30);
        h.draw(g2);
    }
}
