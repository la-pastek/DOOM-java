package com.inventaire;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Container extends Rectangle {

    public void draw(Graphics g,int screenHeight,int screenWidth){
        
        g.setColor(Color.white);
        int x =0;
        for (int i = 0; i < 10; i++) {
            g.drawRect(screenHeight-x, screenWidth, 75,75);
            x=x+75;
        }
        
        // la largeur du contenair efait 1 pixele
        //le contenaire 75*75 pixel
    }
}
