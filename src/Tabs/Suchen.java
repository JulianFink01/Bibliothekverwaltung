/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabs;

import Database.DBConnector;
import Frames.Pane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Suchen extends JPanel implements Tap, MouseListener, ActionListener {

    private String title;
    private JPanel panelLeft;
    private JPanel panelTop;
    private JPanel panelBottom;
    private DBConnector conn;
    private Pane parent;

    public boolean canClickTableData = true;

    private Dimension dim;

    private JTable output;

    private long lastClicked;

    private JScrollPane scrollpane = new JScrollPane();

    private Color yale = new Color(14, 76, 146);
    private Color pigeon = new Color(114, 133, 165);
    private Color maya = new Color(115, 194, 251);

    private JButton leave;
    private JButton showausgeliehn;
    private JButton showfrei;
    private JButton showstart;

    private textfield search;
    private JButton suche;

    private JTable eintraege;

    public Suchen(Pane parent, String title, Dimension dim) {
        this.dim = dim;
        this.parent = parent;
        this.setTitle(title);
        init();
        initBackground();
        initTable(panelBottom, panelBottom.getWidth(), panelBottom.getHeight());
        startTable();
    }

    private void connect() {

        conn = new DBConnector();
        if (!conn.isConnected()) {
            if (conn.connect()) {
            } else {
                JOptionPane.showMessageDialog(this, "Deine Verbindung zur Datenbank ist fehlgeschlagen.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void startTable() {
        connect();
        String sql = "select distinct buch.bid, buchtitel,sachgebiet,a_nachname as autornachname, a_vorname as autovorname, erscheinungsjahr, ort,  coalesce(vorname, 'nicht ausgeliehen') as vorname, coalesce(nachname, 'nicht ausgeliehen') as nachname, coalesce(ausweisnummer, 'nicht ausgeliehen') as ausweisnummer from buch left join ausleihen on buch.bid = ausleihen.bid left join schueler on schueler.sid = ausleihen.sid order by buch.bid";
        ResultSet rs1 = conn.executeQuery(sql);
        ResultSet rs2 = conn.executeQuery(sql);
        insertIntoJTable(rs1, rs2);
        closeConnection();
    }

    private void init() {
        this.setLayout(null);
    }

    private void initInputFields(JPanel panel, int panelwidth, int panelheight) {
        panel.setLayout(null);
        search = new textfield("Suchen", 50, 50, panelwidth - 100, 50);

        panel.add(search);

    }

    private void initTable(JPanel panel, int panelwidth, int panelheight) {
        output = new JTable();
        output.setBounds(50, 150, panelwidth - 100, panelheight - 250);
        output.setGridColor(Color.gray);
        output.setRowHeight(panelheight / 11);
        output.setFont(new Font("Verdana", Font.PLAIN, 12));
        output.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 12));

        scrollpane.setBounds(50, 150, panelwidth - 100, panelheight - 250);
        scrollpane.setViewportView(output);
        panel.add(scrollpane);
    }

    private void initSideButtons(JPanel panel, int panelwidth, int panelheight) {
        panel.setLayout(null);
        Icon leafepng = scaleImage("src/Images/leave.png", 80, 80);
        Icon buchfrei = scaleImage("src/Images/buchfrei.png", 80, 80);
        Icon buchnichtfrei = scaleImage("src/Images/buchausgeliehn.png", 80, 80);
        Icon savepng = scaleImage("src/Images/save.png", 80, 80);
        Icon buchnpng = scaleImage("src/Images/buch.png", 80, 80);
        leave = new JButton(leafepng);
        Icon searchpng = scaleImage("src/Images/search.png", 80, 80);

        suche = new JButton(searchpng);
        suche.addActionListener(this);

        showausgeliehn = new JButton(buchnichtfrei);
        showausgeliehn.addActionListener(this);

        showfrei = new JButton(buchfrei);
        showfrei.addActionListener(this);

        showstart = new JButton(buchnpng);
        showstart.addActionListener(this);

        int buttonAbstand = 20;

        prepareButton(suche, panel, 10, dim.height / 10 * 1, 80, 80);
        prepareButton(showausgeliehn, panel, 10, dim.height / 10 * 2 + buttonAbstand, 80, 80);
        prepareButton(showfrei, panel, 10, dim.height / 10 * 3 + 2 * buttonAbstand, 80, 80);
        prepareButton(showstart, panel, 10, dim.height / 10 * 4 + 3 * buttonAbstand, 80, 80);
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

    private void revertTextFields() {
        search.resetToOrigin();
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
        JLabel title = new JLabel("<html><body><a style='text-align: left; color: white; width: 100%; font-size: 30px; color: white; padding-left: 20px;'>" + this.getTitle() + "</a></body></html>", SwingConstants.LEFT);
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

    public void insertIntoJTable(ResultSet rs, ResultSet rs2) {
        try {
            
           

            
            int row = 0;
            while (rs.next()) {

                row++;
            }

            //collumnames
            String[] colNames = new String[rs.getMetaData().getColumnCount()];
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                colNames[i - 1] = rs.getMetaData().getColumnName(i);
            }
            //table
            TableModel tm = new DefaultTableModel(colNames, row);
            //rs.beforeFirst();
            int RowCounter = 0;

            //write objects
            while (rs2.next()) {

                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
                    tm.setValueAt(rs2.getString(i), RowCounter, i - 1);
                    
          
                    if (rs2.getString(i).equals("nicht ausgeliehen")) {
                       
                    }
                }

                RowCounter++;
            }
            Suchen suche = this;
            output.setModel(tm);
            output.setCellSelectionEnabled(true);
            ListSelectionModel cellSelectionModell = output.getSelectionModel();
            cellSelectionModell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            cellSelectionModell.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {

                    if (canClickTableData) {
                        String selectedData = null;
                        canClickTableData = false;
                        int[] selectedRow = output.getSelectedRows();
                        int[] selectedCollums = output.getSelectedColumns();

                        for (int i = 0; i < selectedRow.length; i++) {
                            for (int j = 0; j < selectedCollums.length; j++) {
                                selectedData = (String) output.getValueAt(selectedRow[i], 0);
                            }
                        }
                        Thread thread = new Thread() {
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Suchen.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                canClickTableData = true;
                            }
                        };
                        thread.start();
                        try {
                            new ausleihen(suche, Integer.parseInt(selectedData));
                        } catch (Exception ex) {

                        }

                    }
                }

            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        output.repaint();

    }

    private void closeConnection() {
        if (conn.isConnected()) {
            conn.close();
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == suche) {
            if (!search.isEmpty()) {
                connect();
                String sql = "select distinct buch.bid,buchtitel, sachgebiet, a_nachname as autornachname, a_vorname as autovorname, erscheinungsjahr, ort,  coalesce(vorname, 'nicht ausgeliehen') as vorname, coalesce(nachname, 'nicht ausgeliehen') as nachname, coalesce(ausweisnummer, 'nicht ausgeliehen') as ausweisnummer from buch left join ausleihen on buch.bid = ausleihen.bid left join schueler on schueler.sid = ausleihen.sid where buchtitel like '%" + search.getText() + "%'";
                ResultSet rs1 = conn.executeQuery(sql);
                ResultSet rs2 = conn.executeQuery(sql);
                insertIntoJTable(rs1, rs2);
                closeConnection();
            }
        }

        if (e.getSource() == showausgeliehn) {

            connect();
            String sql = "select distinct buch.bid,buchtitel, sachgebiet, a_nachname as autornachname, a_vorname as autovorname, erscheinungsjahr, ort,  coalesce(vorname, 'nicht ausgeliehen') as vorname, coalesce(nachname, 'nicht ausgeliehen') as nachname, coalesce(ausweisnummer, 'nicht ausgeliehen') as ausweisnummer from buch left join ausleihen on buch.bid = ausleihen.bid left join schueler on schueler.sid = ausleihen.sid where ausweisnummer not like 'nicht ausgeliehen'";
            ResultSet rs1 = conn.executeQuery(sql);
            ResultSet rs2 = conn.executeQuery(sql);
            insertIntoJTable(rs1, rs2);
            closeConnection();

        }

        if (e.getSource() == showfrei) {

            connect();
            String sql = "select distinct buch.bid,buchtitel, sachgebiet, a_nachname as autornachname, a_vorname as autovorname, erscheinungsjahr, ort,  coalesce(vorname, 'nicht ausgeliehen') as vorname, coalesce(nachname, 'nicht ausgeliehen') as nachname, coalesce(ausweisnummer, 'nicht ausgeliehen') as ausweisnummer from buch left join ausleihen on buch.bid = ausleihen.bid left join schueler on schueler.sid = ausleihen.sid where ausweisnummer is null";
            ResultSet rs1 = conn.executeQuery(sql);
            ResultSet rs2 = conn.executeQuery(sql);
            insertIntoJTable(rs1, rs2);
            closeConnection();

        }

        if (e.getSource() == showstart) {
            startTable();
        }
    }
}
