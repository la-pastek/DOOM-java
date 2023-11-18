package com.Display;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends Canvas {

    MyPanel panel;


    public MyFrame() throws HeadlessException, InterruptedException {
        panel = new MyPanel();
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setTitle("fps");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        System.out.println("Running...");
    }
}