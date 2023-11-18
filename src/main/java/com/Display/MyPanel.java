package com.Display;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.Command.InputH;
import com.graphics.Screen;
import com.inventaire.Container;
import com.inventaire.ContainerSelect;

public class MyPanel extends Canvas implements Runnable {

    private Thread gameThread;
    final int originalTileSize = 16; // 16*16
    final int scale = 3;
    final int tileSize = scale * originalTileSize; // 48*48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixel
    final int screenHeight = tileSize * maxScreenRow;// 576 pixels
    Container container = new Container();
    ContainerSelect containerSelect;

    private BufferedImage img;
    private Screen screen;
    private  Optimize optimize;
    private InputH inputH;

    public MyPanel() {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        inputH = new InputH();
        this.addKeyListener(inputH);
        this.addFocusListener(inputH);
        this.addMouseListener(inputH);
        this.addMouseMotionListener(inputH);
        screen = new Screen(screenWidth, screenHeight);
        img = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        optimize = new Optimize();
        newContainerSelect();
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage buffer = new BufferedImage(screenWidth, screenWidth, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bufferGraphics = buffer.createGraphics();
        render();
        // Dessinez sur l'image tampon
        container.draw(bufferGraphics, 692, 500);
        containerSelect.draw(bufferGraphics);

        // Affichez l'image tampon sur le composant
        g.drawImage(buffer, 0, 0, this);

        // Libérez les ressources graphiques
        bufferGraphics.dispose();

    }

    public void newContainerSelect() {
        containerSelect = new ContainerSelect(692, 500, 70, 70);
    }

    public void move() {
        containerSelect.move();
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                optimize.tick(inputH.key);
                move();
                //containerSelect.getAera();
                repaint();
                delta--;
                Toolkit.getDefaultToolkit().sync(); // Synchronisation avec le système d'affichage
                try {
                    Thread.sleep(200); // Petite pause pour limiter la fréquence de rendu
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3); // Crée une stratégie de tamponnage avec 2 tampons
            return;
        }

        // Récupérez le tableau de pixels de l'image pour le rendu
        int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < screenHeight * screenWidth; i++) {
            pixels[i] = screen.pixels[i];
        }

        // Appel à la méthode render de l'objet Screen
        screen.render(optimize);

        // Récupérez l'objet Graphics de la stratégie de tampon
        Graphics g = bs.getDrawGraphics();

        // Dessinez l'image sur le composant
        g.drawImage(img, 0, 0, screenWidth, screenHeight, null);
        g.setFont(new Font("Verdana", 0,25));
        g.setColor(Color.cyan);
        g.drawString("V0.1", 10,40);
        // Libérez les ressources graphiques
        g.dispose();

        // Affichez le tampon sur l'écran
        bs.show();
    }


}
