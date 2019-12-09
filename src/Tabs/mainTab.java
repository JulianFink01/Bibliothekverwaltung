/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabs;

import Frames.Pane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author Julian Fink
 */
public class mainTab extends JPanel implements Tap, MouseListener {

    private String title;
    private JPanel panelLeft;
    private JPanel panelTop;
    private JPanel panelBottom;
    private Pane parent;
    JButton neuerDatensatz;
    JButton suchFenster;
    JButton reset;
    JButton delete;
    JButton leave;

    private Dimension dim;

    private Color yale = new Color(14, 76, 146);
    private Color pigeon = new Color(114, 133, 165);
    private Color maya = new Color(115, 194, 251);

    public mainTab(Pane parent, String title, Dimension dim) {
        this.dim = dim;
        this.parent = parent;
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

        neuerDatensatz = new JButton(neueDatenpng);
        suchFenster = new JButton(searchpng);
        reset = new JButton(resetpng);
        delete = new JButton(deletepng);
        leave = new JButton(leafepng);

        int buttonAbstand = 20;
        prepareButton(neuerDatensatz, panel, 10, dim.height / 10 * 1, 80, 80);
        prepareButton(suchFenster, panel, 10, dim.height / 10 * 2 + buttonAbstand, 80, 80);
        prepareButton(reset, panel, 10, dim.height / 10 * 3 + 2 * buttonAbstand, 80, 80);
        prepareButton(delete, panel, 10, dim.height / 10 * 4 + 3 * buttonAbstand, 80, 80);
        prepareButton(leave, panel, 10, dim.height / 10 * 6 + 5 * buttonAbstand, 80, 80);

    }

    private void prepareButton(JButton button, JPanel parent, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setOpaque(false);
        button.setContentAreaFilled(true);
        LineBorder b = new LineBorder(pigeon);
        button.setBorder(b);
        button.addMouseListener(this);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == neuerDatensatz) {
            neuerEintrag n = new neuerEintrag(parent, "Neuer Eintrag", dim);
            parent.add(n.getTitle(),n);
            parent.setSelectedComponent(n);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
