package bibliothekverwaltung;

import Frames.*;
import Tabs.*;


public class Bibliothekverwaltung {

    public static void main(String[] args) {
        init();
    }
    
    public static void init(){
           Frame mainFrame = new Frame();
           Pane mainPane = new Pane();
           
           mainTab tap1 = new mainTab(mainPane, "HOME", mainFrame.getSize());
           
           mainPane.add(tap1.getTitle(), tap1);
           
           mainFrame.setContentPane(mainPane);
           mainFrame.setVisible(true);
    }

}
