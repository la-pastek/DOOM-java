package com.graphics;

public class Render3D extends Render {

    public Render3D(int w,int h ) {
        super(w,h);
    }

    public void floor(){
        for (int i = 0; i < 600; i++) {
            double yDepth = i-600/2.4;
            double z = 100.0 /yDepth;

            for (int j = 0; j < 800; j++) {

                double xDepth = j - 800/2;
                xDepth *= z;
                int xx = (int) (xDepth);

                pixels[j+i*800]=xx*128;
            }
        }
    }
}
