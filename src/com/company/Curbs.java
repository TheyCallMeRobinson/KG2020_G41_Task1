package com.company;

import java.awt.*;

public class Curbs implements Drawable {
    private int x, y;
    private int length, curbLength, height = 15, width = 10;
    private double angle;

    public Curbs(int x, int y, double angle, int length, int curbLength, int height, int width) {
        this.x = x;
        this.y = y;
        this.angle = angle*Math.PI/180;;
        this.length = length;
        this.curbLength = curbLength;
        this.height = height;
        this.width = width;
    }

    @Override
    public void draw(Graphics2D g) {
        for(int i = 0; i < length/curbLength; i++) {
            if (i % 2 == 0)
                g.setColor(Color.BLACK);
            else
                g.setColor(Color.WHITE);
            int x = this.x + i * curbLength;
            g.fillRect(x, y, curbLength, height);
            g.fillPolygon(
                    new int[]{x, (int) (x + width * Math.cos(angle)), (int) (x + curbLength + width * Math.cos(angle)), x + curbLength},
                    new int[]{y, (int) (y - width * Math.sin(angle)), (int) (y - width * Math.sin(angle)), y},
                    4
            );
            if(i == length/curbLength - 1) {
                g.fillPolygon(
                        new int[]{x + curbLength, (int) (x + curbLength + width * Math.cos(angle)), (int) (x + curbLength + width * Math.cos(angle)), x + curbLength},
                        new int[]{y, (int) (y - width * Math.sin(angle)), (int) (y - width * Math.sin(angle) + height), y + height},
                        4
                );
                g.setColor(Color.BLACK);
                g.drawPolygon(
                        new int[]{x + curbLength, (int) (x + curbLength + width * Math.cos(angle)), (int) (x + curbLength + width * Math.cos(angle)), x + curbLength},
                        new int[]{y, (int) (y - width * Math.sin(angle)), (int) (y - width * Math.sin(angle) + height), y + height},
                        4
                );
            }
            g.setColor(Color.BLACK);
            g.drawRect(x, y, curbLength, height);
        }
    }
}
