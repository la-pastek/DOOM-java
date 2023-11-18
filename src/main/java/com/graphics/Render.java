package com.graphics;

import com.fps.MyPanel;

public class Render {
    public final int width;
    public final int height;

    private MyPanel panel;
    public final int[] pixels;
    public Render(int width, int height) {
        this.width = width;
        this.height =height;
        pixels = new int[width*height];
    }

    public void draw(Render render, int xOffSet, int yOffSet){
        for (int i = 0; i < render.height; i++) {
            int yPix= i + yOffSet;
            for (int j = 0; j < render.width; j++) {
                int xPix = j+xOffSet;
                pixels[yPix+xPix*width]= render.pixels[j+i*render.width];
            }
        }
    }
}
