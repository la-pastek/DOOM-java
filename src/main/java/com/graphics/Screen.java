package com.graphics;


import com.fps.Optimize;

import java.util.Random;

public class Screen extends Render {

    private Render test;
    private Render3D render;
    public Screen(int width, int height){
        super(800,600);
        Random random = new Random();
        render = new Render3D(800,600);
        test =  new Render(256,256);
        for (int i = 0; i < 256*256; i++) {
            System.out.println(i);
            test.pixels[i] = random.nextInt()*(random.nextInt(5)/4);
        }
    }

    public void render(Optimize optimize){
        for (int i = 0; i < 600*800; i++) {
            pixels[i]=0;
        }
        for (int i = 0; i < 50; i++) {
            int anim = (int) (Math.sin((optimize.time+i*2)%1000.0/100)*100);
            int anim2 = (int) (Math.cos((optimize.time+i*2)%1000.0/100)*100);
        }
        render.floor(optimize);
        draw(render,0,0);
    }
}
