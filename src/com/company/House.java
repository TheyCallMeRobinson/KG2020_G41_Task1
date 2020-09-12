package com.company;

import java.awt.*;
/*
 * This is working and drawing a black triangle with blue filling, there is a guide being opened in the browser
 * Polygon p = new Polygon();
 * p.addPoint(200, 200);
 * p.addPoint(250, 250);
 * p.addPoint(300, 220);
 * g.drawPolygon(p);
 * g.setColor(Color.blue);
 * g.fillPolygon(p);
 */
/* this is also working
 * g.fillPolygon(new Polygon(new int []{100, 200, 300}, new int [] {100, 200,150}, 3));

 */

// check what you have in data. The values are strange
// and this is working too
/*
        int [] array1 = Arrays.copyOf(xPoints, xPoints.length);
        int [] array2 = Arrays.copyOf(yPoints, yPoints.length);
        Polygon p2 = new Polygon(array1, array2, array2.length);
        g.fillPolygon(p2);
*/
public class House implements Drawable {
    private int x, y;
    private int l, w, h;
    private double angle;
    private int floorCount;
    private int windowsPerFloor;
    private int antennaCount = 3;
    private int[] xPoints = new int[6];
    private int[] yPoints = new int[6];

    private void calculateReferencePoints() {
        xPoints[0] = x - l/2;
        xPoints[1] = (int)(x - l/2 + w*Math.cos(angle));
        xPoints[2] = (int)(x + l/2 + w*Math.cos(angle));
        xPoints[3] = x + l/2;
        xPoints[4] = x + l/2;
        xPoints[5] = (int)(x + l/2 + w*Math.cos(angle));

        yPoints[0] = y - h/2;
        yPoints[1] = (int)(y - h/2 - w*Math.sin(angle));
        yPoints[2] = (int)(y - h/2 - w*Math.sin(angle));
        yPoints[3] = y - h/2;
        yPoints[4] = y + h/2;
        yPoints[5] = (int)(y + h/2 - w*Math.sin(angle));
    }

    public House(int x, int y, int l, int w, int h, int angle) {
        this.x = x;
        this.y = y;
        this.l = l;
        this.w = w;
        this.h = h;
        this.angle = angle*Math.PI/180;
        floorCount = h/50;
        windowsPerFloor = l/50;
    }

    public void draw(Graphics2D g) {
        calculateReferencePoints();
        g.setColor(new Color(134, 121, 121));
        g.fillRect(xPoints[0], yPoints[0], l, h);
        g.setColor(new Color(182, 175, 175));
        g.fillPolygon(new Polygon(new int[]{xPoints[0], xPoints[1], xPoints[2], xPoints[3]}, new int[]{yPoints[0], yPoints[1], yPoints[2], yPoints[3]}, 4));
        g.setColor(new Color(80, 73, 73));
        g.fillPolygon(new int[]{xPoints[2], xPoints[3], xPoints[4], xPoints[5]}, new int[]{yPoints[2], yPoints[3], yPoints[4], yPoints[5]}, 4);
        int windowLength = l/windowsPerFloor;
        int windowHeight = h/floorCount;
        for(int i = 0; i < floorCount; i++)
            for(int j = 0; j < windowsPerFloor; j++) {
                HouseWindow hw = new HouseWindow(xPoints[0] + windowLength*(2*j + 1)/2, yPoints[0] + windowHeight*(2*i + 1)/2);
                hw.draw(g);
            }
    }

    private class HouseWindow implements Drawable {
        private int x, y;
        private int border;
        private int sizeX;
        private int sizeY;
        private Color glassColor = new Color(128, 159, 255);
        private Color borderColor = Color.WHITE;

        public HouseWindow(int x, int y) {
            this.x = x;
            this.y = y;
            sizeX = 40;
            sizeY = 40;
            border = 3;
        }

        @Override
        public void draw(Graphics2D g) {
            g.setColor(Color.BLACK);
            g.drawRect(x - sizeX/2 - 1, y - sizeY/2 - 1, sizeX + 2, sizeY + 2);
            g.setColor(borderColor);
            g.fillRect(x - sizeX/2, y - sizeY/2, sizeX, sizeY);
            g.setColor(glassColor);
            g.fillRect(x - sizeX/2 + border, y - sizeY/2 + border, (sizeX - border*3)/2, (sizeY - border*3)/2);
            g.fillRect(x - sizeX/2 + border, y + border/2, (sizeX - border*3)/2, (sizeY - border*3)/2);
            g.fillRect(x + border/2, y - sizeY/2 + border, (sizeX - border*3)/2, sizeY - border*10/4);
        }
    }
}
