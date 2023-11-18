package com.Command;

public class Controller {

    public  double x,z, rotationa, xa, za, rotation;
    public void tick(boolean tournDroite,boolean tournGauche,boolean avant,boolean arrier,boolean droite,boolean gauche,boolean invD,boolean invG,boolean invSelect){
        double rotationSpeed = 1;
        double walkspeed = 1;
        double xMove = 0;
        double zMove = 0;
        double select = 0;

        if(avant){
            zMove++;
        }
        if(arrier){
            zMove--;

        }if(gauche){
            xMove--;

        }if(droite){
            xMove++;

        }if(invD){
            select++;

        }if(invG){
            select--;

        }if(invSelect){
            //call methode
        }
        if (tournDroite){
            rotationa-=rotationSpeed;
        }
        if (tournGauche){
            rotationa+=rotationSpeed;
        }

        xa +=(xMove*Math.cos(rotation) +zMove* Math.sin(rotation))*walkspeed;
        za +=(zMove*Math.cos(rotation) -xMove* Math.sin(rotation))*walkspeed;

        x+=xa;
        z+=za;
        xa*= 0.1;
        za*= 0.1;
        rotation+= rotationa;
        rotationa*=0.8;
    }

}
