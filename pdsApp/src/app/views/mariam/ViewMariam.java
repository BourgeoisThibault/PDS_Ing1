/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.views.mariam;

import app.controllers.*;
import app.listeners.*;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Thibault
 */
public class ViewMariam implements ListenerMariam {
    
    private ControllerMariam cci;
    private JPanel body;
    private Container cont;
    
    public ViewMariam(ControllerMariam cci, JPanel body, Container cont) {
      this.cci = cci;
      this.body = body;
      this.cont = cont;
    }
    
    public void setIHM() {
        body.removeAll();
        
        JLabel test = new JLabel();
        test.setFont(new java.awt.Font("Verdana", 0, 50)); // NOI18N
        test.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        test.setText("IHM de Mariam");
        
        
        body.setLayout(new FlowLayout());
        body.setBackground(new Color(215,203,233,200));
        body.add(test);
        body.revalidate();
        body.repaint();
        
        cont.revalidate();
        cont.repaint();
    }
    
}