package com.company;

import java.awt.*;

public class Flowerbed implements Drawable {
    private int x, y;
    private int height;
    private int lesserWidth = 65, biggerWidth = 100;

    public Flowerbed(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g) {
        Color mainColor = new Color(0x744FB4);
        Color innerColor = new Color(0x2EB727);
        int[] xPoints = new int[]{x - biggerWidth / 2, x + biggerWidth / 2, x + lesserWidth / 2, x - lesserWidth / 2};
        int[] yPoints = new int[]{y - height / 2, y - height / 2, y + height / 2, y + height / 2};
        int amountOfFlowers = (int)(Math.random() * 15 + 10);
        g.setColor(mainColor);
        g.fillOval(x - lesserWidth / 2, y + height / 2 - 15 / 2, lesserWidth, 15);
        g.fillPolygon(xPoints, yPoints, 4);
        g.setColor(innerColor);
        g.fillOval(x - biggerWidth / 2, y - height / 2 - 15 / 2, biggerWidth, 15);
        for(int i = 0; i < amountOfFlowers; i++) {
            int RNG_x = (int) (Math.random() * biggerWidth + x - biggerWidth/2);
            int RNG_y = (int) (Math.random() * 15 + y - height/2 - 15/2);
            new Flower(RNG_x, RNG_y).draw(g);
        }
    }

    private class Flower implements Drawable {
        private int x, y;
        private int size;
        private int amountOfPetals;

        public Flower(int x, int y) {
            size = (int) (Math.random() * 10 + 7);
            amountOfPetals = (int) (Math.random() * 50 + 30);
            this.x = x - size/2;
            this.y = y - size/2;
        }

        @Override
        public void draw(Graphics2D g) {
            int RNG_colorRGB_1 = (int) (Math.random() * 255);
            int RNG_colorRGB_2 = (int) (Math.random() * 255);
            int RNG_colorRGB_3 = (int) (Math.random() * 255);
            Color flowerColor = new Color(RNG_colorRGB_1, RNG_colorRGB_2, RNG_colorRGB_3);
            g.setColor(flowerColor);
            for (int i = 0; i < amountOfPetals; i++) {
                int RNG_x = (int) (Math.random() * size + x);
                int RNG_y = (int) (Math.random() * size + y);
                int RNG_r = (int) (Math.random() * size / 3 + size / 4);
                g.fillOval(RNG_x, RNG_y, RNG_r, RNG_r);
            }
            g.setColor(Color.yellow);
            g.fillOval(x + size/2 - 2, y + size/2 - 2, 5, 5);
        }
    }
}
