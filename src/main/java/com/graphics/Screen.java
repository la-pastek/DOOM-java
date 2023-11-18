package com.graphics;


import com.Display.Optimize;

import java.util.Random;

public class Screen extends Render {

    private Render3D render;
    public Screen(int width, int height){
        super(width,height);
        render = new Render3D(width,height);
    }

    public void render(Optimize optimize){
        for (int i = 0; i < width*height; i++) {
            pixels[i]=0;
        }
        render.floor(optimize);
        draw(render,0,0);
    }
}
