/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabs;

import Database.DBConnector;
import Frames.Pane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class neuerEintrag extends JPanel implements Tap, MouseListener {

    private String title;
    private JPanel panelLeft;
    private JPanel panelTop;
    private JPanel panelBottom;

    private Pane parent;

    private Dimension dim;

    private Color yale = new Color(14, 76, 146);
    private Color pigeon = new Color(114, 133, 165);
    private Color maya = new Color(115, 194, 251);

    private JButton leave;
    private JButton save;

    private textfield buchnummer;
    private textfield sachgebiet;
    private textfield buchrueckennummer;
    private textfield buchtitel;
    private textfield autornachname;
    private textfield autorvorname;
    private textfield verlag;
    private textfield ort;
    private textfield erscheinungsjahr;

    public neuerEintrag(Pane parent, String title, Dimension dim) {
        this.dim = dim;
        this.parent = parent;
        this.setTitle(title);
        init();
        initBackground();
    }

    private void init() {
        this.setLayout(null);
    }

    private void initInputFields(JPanel panel, int panelwidth, int panelheight) {
        panel.setLayout(null);
        buchnummer = new textfield("Buchnummer", 50, 50, (panelwidth - 200) / 2, 50);
        buchrueckennummer = new textfield("Buchrückennummer", 50 + (panelwidth - 200) / 2 + 50, 50, (panelwidth - 200) / 2 + 50, 50);
        sachgebiet = new textfield("Sachgebiet", 50, 120, panelwidth - 100, 50);
        buchtitel = new textfield("Buchtitel", 50, 190, panelwidth - 100, 50);
        autorvorname = new textfield("Autor-Vorname", 50, 260, (panelwidth - 200) / 2, 50);
        autornachname = new textfield("Autor-Nachname", 50 + (panelwidth - 200) / 2 + 50, 260, (panelwidth - 200) / 2 + 50, 50);
        verlag = new textfield("Verlag", 50, 330, (panelwidth - 200) / 2, 50);
        ort = new textfield("Ort", 50 + (panelwidth - 200) / 2 + 50, 330, (panelwidth - 200) / 2 + 50, 50);
        erscheinungsjahr = new textfield("Erscheinungsjahr", 50, 400, (panelwidth - 200) / 2, 50);
        panel.add(buchnummer);
        panel.add(sachgebiet);
        panel.add(buchrueckennummer);
        panel.add(buchtitel);
        panel.add(autorvorname);
        panel.add(autornachname);
        panel.add(verlag);
        panel.add(ort);
        panel.add(erscheinungsjahr);

    }

    private void initSideButtons(JPanel panel, int panelwidth, int panelheight) {
        panel.setLayout(null);
        Icon leafepng = scaleImage("src/Images/leave.png", 80, 80);
        Icon savepng = scaleImage("src/Images/save.png", 80, 80);
        leave = new JButton(leafepng);
        save = new JButton(savepng);

        int buttonAbstand = 20;
        prepareButton(save, panel, 10, dim.height / 10 * 1, 80, 80);
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

    private void revertTextFields(){
        buchtitel.resetToOrigin();
        sachgebiet.resetToOrigin();
        ort.resetToOrigin();
        verlag.resetToOrigin();
        autornachname.resetToOrigin();
        autorvorname.resetToOrigin();
        erscheinungsjahr.resetToOrigin();
        buchnummer.resetToOrigin();
        buchrueckennummer.resetToOrigin();
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
        JLabel title = new JLabel("<html><body><a style='text-align: left; color: white; width: 100%; font-size: 30px; color: white; padding-left: 20px;'>Neuer Eintrag</a></body></html>", SwingConstants.LEFT);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBounds(20, 0, panelTop.getWidth(), panelTop.getHeight());
        panelTop.add(title);

        panelBottom.setBounds(dim.width / 10 * 1, dim.height / 10 * 1, dim.width - dim.width / 10 * 1, dim.height - dim.height / 10 * 1);
        panelBottom.setBackground(maya);

        initInputFields(panelBottom, panelBottom.getWidth(), panelBottom.getHeight());

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
        if (e.getSource() == leave) {
            parent.remove(this);
        }

        if (e.getSource() == save) {
            DBConnector conn = new DBConnector();

            if (!conn.isConnected()) {
                if (conn.connect()) {
                    String sql = "insert into buch (sachgebiet, buchtitel, a_nachname, a_vorname, verlag, erscheinungsjahr, ort) values ('"+sachgebiet.getText()+"','"+buchtitel.getText()+"','"+autornachname.getText()+"','"+autorvorname.getText()+"','"+verlag.getText()+"','01/01/"+erscheinungsjahr.getText()+"','"+ort.getText()+"');";
                    if(conn.executeUpdate(sql)>=0){
                            JOptionPane.showMessageDialog(this, "Der Datensatz wurde erfolgreich eingefügt.", "Info", JOptionPane.INFORMATION_MESSAGE);
                            revertTextFields();
                    }else{
                            JOptionPane.showMessageDialog(this, "Dein Datensatz konnte nicht eingefügt werden.", "Error", JOptionPane.ERROR_MESSAGE);
               
                    }
                    
                    conn.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Deine Verbindung zur Datenbank ist fehlgeschlagen.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
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
