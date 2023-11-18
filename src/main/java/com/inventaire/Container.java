package com.inventaire;

import java.awt.*;

public class inventDisplay extends Rectangle {
    
    public void draw(Graphics g,int screenHeight,int screenWidth){
        g.setColor(Color.white);
        g.drawRect(screenHeight, screenWidth, 50,50);
    }
}
