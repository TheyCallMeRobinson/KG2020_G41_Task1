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
    private int antennaCount = 5;
    private int[] xPoints = new int[6];
    private int[] yPoints = new int[6];
    private int windowLength;
    private int windowHeight;

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
        windowLength = l/windowsPerFloor;
        windowHeight = h/floorCount;
    }
    public House(int x, int y, int l, int w, int h, int floorCount, int windowsPerFloor, int angle) {
        this.x = x;
        this.y = y;
        this.l = l;
        this.w = w;
        this.h = h;
        this.angle = angle * Math.PI / 180;
        this.floorCount = floorCount;
        this.windowsPerFloor = windowsPerFloor;
        windowLength = l / windowsPerFloor;
        windowHeight = h / floorCount;
    }

    public void draw(Graphics2D g) {
        calculateReferencePoints();
        g.setColor(new Color(134, 121, 121));
        g.fillRect(xPoints[0], yPoints[0], l, h);
        g.setColor(new Color(182, 175, 175));
        g.fillPolygon(new Polygon(new int[]{xPoints[0], xPoints[1], xPoints[2], xPoints[3]}, new int[]{yPoints[0], yPoints[1], yPoints[2], yPoints[3]}, 4));
        g.setColor(new Color(80, 73, 73));
        g.fillPolygon(new int[]{xPoints[2], xPoints[3], xPoints[4], xPoints[5]}, new int[]{yPoints[2], yPoints[3], yPoints[4], yPoints[5]}, 4);

        for(int i = 0; i < floorCount; i++)
            for(int j = 0; j < windowsPerFloor; j++)
                if(j == windowsPerFloor/2 && i == floorCount - 1)
                     new HouseDoor(xPoints[0] + windowLength * (2 * j + 1) / 2, yPoints[0] + windowHeight * (2 * i + 1) / 2).draw(g);
                else new HouseWindow(xPoints[0] + windowLength * (2 * j + 1) / 2, yPoints[0] + windowHeight * (2 * i + 1) / 2, 0.8, 0.8, 3).draw(g);

        for(int i = 0; i < antennaCount; i++)
            new HouseAntenna((int)(xPoints[0] + (i+1)*l/(antennaCount + 1) + w*Math.cos(angle)/2), (int)(yPoints[0] - w*Math.sin(angle)/2)).draw(g);

        g.setColor(new Color(0x00FEFB));
        g.rotate(-angle, xPoints[3], yPoints[3]);
        g.drawString("ОБЩЕЖИТИЕ ВГУ", xPoints[3], yPoints[3] + 10);
        g.rotate(angle, xPoints[3], yPoints[3]);
    }

    private class HouseWindow implements Drawable {
        private int x, y;
        private int innerBorder;
        private int sizeX;
        private int sizeY;
        private Color glassColor = new Color(128, 159, 255);
        private Color borderColor = Color.WHITE;

        public HouseWindow(int x, int y) {
            this.x = x;
            this.y = y;
            sizeX = 40;
            sizeY = 40;
            innerBorder = 3;
        }
        public HouseWindow(int x, int y, double scaleX, double scaleY, int innerBorder) {
            this.x = x;
            this.y = y;
            this.sizeY = (int)(windowHeight*scaleY);
            this.sizeX = (int)(windowLength*scaleX);
            this.innerBorder = innerBorder;
        }

        @Override
        public void draw(Graphics2D g) {
            g.setColor(Color.BLACK);
            g.drawRect(x - sizeX/2 - 1, y - sizeY/2 - 1, sizeX + 2, sizeY + 2);
            g.setColor(borderColor);
            g.fillRect(x - sizeX/2, y - sizeY/2, sizeX, sizeY);
            g.setColor(glassColor);
            g.fillRect(x - sizeX/2 + innerBorder, y - sizeY/2 + innerBorder, (sizeX - innerBorder *3)/2, (sizeY - innerBorder *3)/2);
            g.fillRect(x - sizeX/2 + innerBorder, y + innerBorder /2+1, (sizeX - innerBorder *3)/2, (sizeY - innerBorder *3)/2);
            g.fillRect(x + innerBorder /2, y - sizeY/2 + innerBorder, (sizeX - innerBorder *3)/2, sizeY - 2* innerBorder);
        }
    }

    private class HouseAntenna implements Drawable {
        int x, y;
        int h, w;
        int antennaW;

        public HouseAntenna(int x, int y) {
            this.x = x;
            this.y = y;
            this.h = 50;
            this.w = 50;
            this.antennaW = 30;
        }

        public void draw(Graphics2D g) {
            g.setColor(Color.black);
            g.drawLine(x, y, x, y - h);
            g.drawLine(x - h/2, y - h, x + h/2, y - h);
            g.drawLine((int)(x - h/2 - antennaW*Math.cos(angle)), (int)(y - h + antennaW*Math.sin(angle)), (int)(x - h/2 + antennaW*Math.cos(angle)), (int)(y - h - antennaW*Math.sin(angle)));
            g.drawLine((int)(x - h/4 - antennaW*Math.cos(angle)), (int)(y - h + antennaW*Math.sin(angle)), (int)(x - h/4 + antennaW*Math.cos(angle)), (int)(y - h - antennaW*Math.sin(angle)));
            g.drawLine((int)(x + h/4 - antennaW*Math.cos(angle)), (int)(y - h + antennaW*Math.sin(angle)), (int)(x + h/4 + antennaW*Math.cos(angle)), (int)(y - h - antennaW*Math.sin(angle)));
            g.drawLine((int)(x + h/2 - antennaW*Math.cos(angle)), (int)(y - h + antennaW*Math.sin(angle)), (int)(x + h/2 + antennaW*Math.cos(angle)), (int)(y - h - antennaW*Math.sin(angle)));
        }
    }

    private class HouseDoor implements Drawable {
        private int x, y;
        private int length, height;

        public HouseDoor(int x, int y) {
            this.x = x - windowLength / 3;
            this.y = y - windowHeight * 3 / 8;
            this.length = windowLength * 2 / 3;
            this.height = windowHeight*7/8;
        }

        @Override
        public void draw(Graphics2D g) {
            g.setColor(new Color(0xC65100));
            g.fillRect(x, y, length, height);
            g.setColor(new Color(0x743300));
            g.fillRect(x + 3, y + 3, length - 6, height - 6);
            g.setColor(Color.YELLOW);
            g.fillOval(x + 4, y + height/2, length/10, height/10);
        }
    }
}
