package com.graphics;


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

    public void render(){
        for (int i = 0; i < 600*800; i++) {
            pixels[i]=0;
        }
        render.floor();
        draw(render,0,0);
    }
}
