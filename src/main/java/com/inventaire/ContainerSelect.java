package com.inventaire;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ContainerSelect extends Rectangle {

    public ContainerSelect(int X, int Y, int pW, int pH){
        super(X,Y,pW,pH);
    }
    int xVelocity;
    int[] areaXY =new int[1];

    public void getAera(){
        if (areaXY!=null){
            for (int i = 0; i < areaXY.length; i++) {
                System.out.println(areaXY[i]);
            }
        }
    }
    public void setxDirection(int xDirection){
        xVelocity = xDirection;
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
             x=x+75;
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
             x=x-75;
        }
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {setxDirection(0);}
        if (e.getKeyCode() == KeyEvent.VK_E) {setxDirection(0);}
    }
    public void  move(){
        x = x+xVelocity;
    }
    public void draw(Graphics g){
        Color couleurTransparente = new Color(0, 0, 255, 30);
        g.setColor(couleurTransparente);
        g.fillRect(x,y,width,height);
    }

    public void getAreaSelect(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_Z){
            areaXY[0]=x;
            //areaXY[1]=y;
        }
    }
}
