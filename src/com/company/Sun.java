package com.company;

import java.awt.*;

public class Sun implements Drawable {
    private int x = 100, y = 100, r = 50, R = 100, amountOfRays = 10000;
    private Color color = Color.YELLOW;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getR() {
        return r;
    }
    public int getAmountOfRays() {
        return amountOfRays;
    }
    public Color getColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setR(int r) {
        this.r = r;
    }
    public void setAmountOfRays(int amountOfRays) {
        this.amountOfRays = amountOfRays;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public Sun(int x, int y, int r, int r1, int amountOfRays) {
        this.x = x;
        this.y = y;
        this.r = r;
        R = r1;
        this.amountOfRays = amountOfRays;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval(x - r, y - r, r + r, r + r);
        Double da = 2*Math.PI / amountOfRays;
        for(int i = 0; i < amountOfRays; i++) {
            double dx1 = r * Math.cos(da * i) + x;
            double dx2 = R * Math.cos(da * i) + x;
            double dy1 = r * Math.sin(da * i) + y;
            double dy2 = R * Math.sin(da * i) + y;
            g2.drawLine((int)dx1, (int)dy1, (int)dx2 , (int)dy2);
        }
    }
}
