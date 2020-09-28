package com.company;

import java.awt.*;

public class Cloud implements Drawable {
    private int x, y;
    private int height, width;

    public Cloud(int x, int y) {
        this.x = x;
        this.y = y;
        this.height = (int)(Math.random() * 40 + 30);
        this.width = (int)(Math.random() * 80 + 70);
    }

    @Override
    public void draw(Graphics2D g) {
        int RNG_RGBColor = (int)(Math.random() * 55 + 200);
        g.setColor(new Color(RNG_RGBColor, RNG_RGBColor, RNG_RGBColor));
        int RNG_countOfCircles = (int)(Math.random() * 30 + 20);
        for(int i = 0; i < RNG_countOfCircles; i++) {
            int RNG_x = (int)(Math.random() * width + x);
            int RNG_y = (int)(Math.random() * height + y);
            int RNG_r = (int)(Math.random() * Math.max(width, height)/2 + Math.min(width, height)/2);
            g.fillOval(RNG_x, RNG_y, RNG_r, RNG_r);
        }
    }
}
