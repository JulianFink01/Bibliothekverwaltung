/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;

public class textfield extends JTextField implements FocusListener {

    private String placeholder;

    private int x;
    private int y;
    private int width;
    private int height;
    private Dimension dim;

    public textfield(String placeholder, int x, int y, int width, int height) {
        this.placeholder = placeholder;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        dim = new Dimension(width, height);
        preapereField();
        this.setOpaque(true);
    }

    private void preapereField() {

        this.setSize(dim);
        this.setBounds(x, y, width, height);
        this.addFocusListener(this);
        this.setText(placeholder);
        this.setForeground(Color.gray);
    }

    public void resetToOrigin(){
        this.setText(placeholder);
        this.setForeground(Color.gray);
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().equals(placeholder)) {
            this.setText("");
            this.setForeground(Color.black);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().equals(placeholder) || this.getText().equals("")) {
            this.setText(placeholder);
            this.setForeground(Color.gray);
        }
    }

}
