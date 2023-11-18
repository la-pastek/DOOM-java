package com.graphics;

import com.Display.Optimize;

public class Render3D extends Render {

    public Render3D(int width,int height ) {
        super(width,height);
    }

    public void floor(Optimize optimize){
        double floorPosition = 15;
        double ceilingPosition = 80;
        double avant = optimize.controller.z;
        double droite = optimize.controller.x;

        double rotation = optimize.controller.rotation;
        double cosine = Math.cos(rotation);
        double sine = Math.sin(rotation);

        for (int y = 0; y < height; y++) {
            double ceiling = (y+-height/2.0)/height;
            double z = floorPosition/ceiling;

            if (ceiling<0){
                z =ceilingPosition/-ceiling;
            }


            for (int x = 0; x < width; x++) {
                double Depth = (x - width/2.0)/width;
                Depth *= z;
                double xx = (Depth*cosine+z*sine);
                double yy = (z* cosine - Depth*sine);
                int xPis = (int)(xx+droite);
                int yPis = (int)(yy+avant);
                pixels[x+y*height]=Texture.floor.pixels[(xPis & 7)+(yPis & 7)*8];
                if (z>500){
                    pixels[x+y*height]=0;
                }
            }
        }
    }
}
