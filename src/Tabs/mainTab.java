/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabs;

import javax.swing.JPanel;

/**
 *
 * @author Julian Fink
 */
public class mainTab extends JPanel implements Tap{

    private String title;
    
    public mainTab(String title){
        
    }
   
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return this.title;
     }
    
    
    
}
