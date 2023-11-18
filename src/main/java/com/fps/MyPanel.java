package com.fps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.graphics.Render;
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
    final int screenWidth = tileSize * maxScreenCol; // 717 pixel
    final int screenHeight = tileSize * maxScreenRow;//525 pixels
    Container container = new Container();
    ContainerSelect containerSelect;

    private BufferedImage img;
    private Screen screen;

    public MyPanel() {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.addKeyListener(new AL());
        newContainerSelect();
        screen = new Screen(800, 600);
        img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
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
                move();
                containerSelect.getAera();
                repaint();
                delta--;
                Toolkit.getDefaultToolkit().sync(); // Synchronisation avec le système d'affichage
                try {
                    Thread.sleep(1); // Petite pause pour limiter la fréquence de rendu
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            containerSelect.keyPressed(e);
            containerSelect.getAreaSelect(e);
        }

        public void keyReleased(KeyEvent e) {
            containerSelect.keyReleased(e);
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2); // Crée une stratégie de tamponnage avec 2 tampons
            return;
        }

        // Récupérez le tableau de pixels de l'image pour le rendu
        int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < 600 * 800; i++) {
            pixels[i] = screen.pixels[i];
        }

        // Appel à la méthode render de l'objet Screen
        screen.render();

        // Récupérez l'objet Graphics de la stratégie de tampon
        Graphics g = bs.getDrawGraphics();

        // Dessinez l'image sur le composant
        g.drawImage(img, 0, 0, 800, 600, null);

        // Libérez les ressources graphiques
        g.dispose();

        // Affichez le tampon sur l'écran
        bs.show();
    }


}
