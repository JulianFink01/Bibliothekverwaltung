/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Julian Fink
 */
public class Frame extends JFrame{
    
    private Dimension dim = new Dimension(1080,720);
    
    public Frame(){
       init();
    }
    
    private void init(){
        this.setSize(dim);
        this.setMaximumSize(dim);
        this.setPreferredSize(dim);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Bibliothekverwaltung");
    }
}
