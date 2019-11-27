/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Julian Fink
 */
public class mainTab extends JPanel implements Tap {

    private String title;
    private JPanel panelLeft;
    private JPanel panelTop;
    private JPanel panelBottom;

    private Dimension dim;

    private Color yale = new Color(14, 76, 146);
    private Color pigeon = new Color(114, 133, 165);
    private Color maya = new Color(115, 194, 251);

    public mainTab(String title, Dimension dim) {
        this.dim = dim;
        setTitle(title);
        init();
        initBackground();
    }

    private void init() {
        this.setLayout(null);
    }

    private void initSideButtons(JPanel panel, int panelwidth, int panelheight) {
        panel.setLayout(null);

        Icon neueDatenpng = scaleImage("src/Images/neuerDatensatz.png", 80, 80);
        Icon leafepng = scaleImage("src/Images/leave.png", 80, 80);
        Icon resetpng = scaleImage("src/Images/reset.png", 80, 80);
        Icon searchpng = scaleImage("src/Images/search.png", 80, 80);
        Icon deletepng = scaleImage("src/Images/delete.png", 80, 80);

        JButton neuerDatensatz = new JButton(neueDatenpng);
        JButton suchFenster = new JButton(searchpng);
        JButton reset = new JButton(resetpng);
        JButton delete = new JButton(deletepng);
        JButton leave = new JButton(leafepng);

        int buttonAbstand = 20;
        prepareButton(neuerDatensatz,panel, 10, dim.height/10*1, 80, 80);
        prepareButton(suchFenster,panel, 10, dim.height/10*2+buttonAbstand, 80, 80);
        prepareButton(reset,panel, 10, dim.height/10*3+2*buttonAbstand, 80, 80);
        prepareButton(delete,panel, 10, dim.height/10*4+3*buttonAbstand, 80, 80);
        prepareButton(leave,panel, 10, dim.height/10*5+4*buttonAbstand, 80, 80);
       

    }

    private void prepareButton(JButton button,JPanel parent, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        parent.add(button);
    }

    private ImageIcon scaleImage(String src, int width, int height) {

        ImageIcon imageIcon = new ImageIcon(src);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }

    private void initBackground() {

        System.out.println(dim.width + " " + dim.height);
        panelLeft = new JPanel();
        panelTop = new JPanel();
        panelBottom = new JPanel();

        panelLeft.setBounds(0, 0, dim.width / 10 * 1, dim.height);
        panelLeft.setBackground(yale);
        initSideButtons(panelLeft, panelLeft.getWidth(), panelLeft.getHeight());

        panelTop.setBounds(dim.width / 10 * 1, 0, dim.width - dim.width / 10 * 1, dim.height / 10 * 1);
        panelTop.setBackground(pigeon);
        panelTop.setLayout(null);
        JLabel title = new JLabel("<html><body><a style='text-align: left; color: white; width: 100%; font-size: 30px; color: white; padding-left: 20px;'>Bibliothekverwaltung</a></body></html>", SwingConstants.LEFT);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBounds(20, 0, panelTop.getWidth(), panelTop.getHeight());
        panelTop.add(title);

        panelBottom.setBounds(dim.width / 10 * 1, dim.height / 10 * 1, dim.width - dim.width / 10 * 1, dim.height - dim.height / 10 * 1);
        panelBottom.setBackground(maya);

        this.add(panelTop);
        this.add(panelLeft);
        this.add(panelBottom);
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
