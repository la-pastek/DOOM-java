package com.graphics;

import com.fps.Optimize;

public class Render3D extends Render {

    public Render3D(int w,int h ) {
        super(w,h);
    }

    public void floor(Optimize optimize){
        double rotation = optimize.time/100.0;
        double cosine = Math.cos(rotation);
        double sine = Math.sin(rotation);

        for (int i = 0; i < 600; i++) {
            double ceiling = (i+-600/2.0)/600;
            double z = 10 /ceiling;

            if (ceiling<0){
                z =8/-ceiling;
            }


            for (int j = 0; j < 800; j++) {
                double Depth = (j - 800/2)/800;
                Depth *= z;
                double xx = (Depth*cosine+z*sine);
                double yy = (z* cosine - Depth*sine);
                int xPis = (int)(xx);
                int yPis = (int)(yy);
                pixels[j+i*800]=((xPis&15)*16) | ((yPis&15)*16)<<5;
            }
        }
    }
}
