package com.Display;

import com.Command.Controller;
import com.Command.InputH;

import java.awt.event.KeyEvent;

public class Optimize {
    public int time;
    public Controller controller;
    public Optimize(){
        controller = new Controller();
    }
    public void tick(boolean[]key){
        time++;
        boolean avant = key[KeyEvent.VK_Z];
        boolean arriere = key[KeyEvent.VK_S];
        boolean gauche = key[KeyEvent.VK_Q];
        boolean droit = key[KeyEvent.VK_D];
        boolean tournGauche = key[KeyEvent.VK_LEFT];
        boolean tournDroit = key[KeyEvent.VK_RIGHT];
        boolean invD = key[KeyEvent.VK_K]; // se deplacer dans l'inventaire a droite
        boolean invG = key[KeyEvent.VK_M];// se deplacer dans l'inventaire a gzaucje
        boolean invSelect = key[KeyEvent.VK_L]; // select inventaire
        controller.tick(tournDroit,tournGauche,avant,arriere,droit,gauche,invD,invG,invSelect);
    }
}
