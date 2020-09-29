package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Color skyColor = new Color(0x83D0FF);
        Color groundColor = new Color(0x94A5B4);
        g2.setColor(skyColor);
        g2.fillRect(0,0, getWidth(), getHeight());
        g2.setColor(groundColor);
        g2.fillRect(0, getHeight() - getHeight()/2, getWidth(), getHeight()/2);
        int cloudsAmount = (int)(Math.random()*15 + 5);
        Sun sun = new Sun(100, 100, 50, 100, 20);
        sun.draw(g2);
        for(int i = 0; i < cloudsAmount; i++) {
            int RNG_x = (int)(Math.random() * getWidth());
            int RNG_y = (int)(Math.random() * getHeight()/5);
            Drawable cloud = new Cloud(RNG_x, RNG_y);
            cloud.draw(g2);
        }
        Drawable h = new House(960*4/5, 540*4/5, 1000, 120, 200, 3, 15, 30);
        h.draw(g2);
        Drawable f = new Flowerbed(900, 600, 40);
        f.draw(g2);
        Curbs curbs = new Curbs(0, 700, 30, 1600, 40, 10, 5);
        curbs.draw(g2);
    }
}
