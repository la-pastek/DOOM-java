package com.fps;

import javafx.scene.image.Image;

import javax.swing.*;
import java.awt.*;

import java.util.*;

public class MyPanel extends JPanel  {
    final int originalTileSize=16; //16*16
    final int scale =3;
    final int tileSize = scale*originalTileSize; // 48*48
    final int maxScreenCol=16;
    final int maxScreenRow =12;
    final int screenWidth = tileSize* maxScreenCol; // 768 pixel
    final int screenHeight = tileSize*maxScreenRow;//576 pixels

    public ArrayList<Integer> xList= new ArrayList<>();
    public ArrayList<Integer> yList= new ArrayList<>();

    Thread antStart = new Thread();
    Random rand = new Random();

    public MyPanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
    }


}
