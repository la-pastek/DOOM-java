package com.graphics;


import java.util.Random;

public class Screen extends Render {

    private Render test;
    public Screen(int width, int height){
        super(800,600);
        Random random = new Random();
        test =  new Render(256,256);
        for (int i = 0; i < 256*256; i++) {
            test.pixels[i] = random.nextInt();
        }
    }

    public void render(){
        for (int i = 0; i < 600*800; i++) {
            pixels[i]=0;
        }
        int anim = (int)(Math.sin(System.currentTimeMillis()%2000.0/1000*Math.PI*2)*100);
        draw(test,250,250+anim);
    }
}
