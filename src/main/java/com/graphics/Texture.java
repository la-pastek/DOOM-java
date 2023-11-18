package com.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Texture {
    public static Render floor = loadBitmap("src/main/resources/com/Display/floor.png");
    public static Render loadBitmap(String fileName){
        try {
            BufferedImage image= ImageIO.read(new File(fileName));
            if (image == null) {
                System.out.println("Image file not found: " + fileName);
                return null;  // Or throw an exception or return a default Render object
            }
            int w= image.getWidth();
            int h = image.getHeight();
            Render result = new Render(w,h);
            image.getRGB(0,0,w,h, result.pixels, 0,w);
            return  result;
        } catch (IOException e) {
            System.out.println("oh FUCK");
            System.out.println("Error loading image: " + fileName);
            throw new RuntimeException(e);
        }
    }
}
