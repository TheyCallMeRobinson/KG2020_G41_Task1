package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(new Color(0x83D0FF));
        g2.fillRect(0,0, getWidth(), getHeight());
        int cloudsAmount = (int)(Math.random()*15 + 5);
        for(int i = 0; i < cloudsAmount; i++) {
            int RNG_x = (int)(Math.random() * getWidth());
            int RNG_y = (int)(Math.random() * getHeight()/5);
            Drawable cloud = new Cloud(RNG_x, RNG_y);
            cloud.draw(g2);
        }
        Drawable h = new House(960*4/5, 540*4/5, 1000, 120, 200, 5, 25, 30);
        h.draw(g2);

    }
}
