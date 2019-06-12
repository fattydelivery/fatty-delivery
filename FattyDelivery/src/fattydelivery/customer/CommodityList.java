/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.customer;

import fattydelivery.lib.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Administrator
 */
public class CommodityList extends JScrollPane {
    private CommoditySelectPanel FJP;
    private Commodity[] oo;
    private JPanel JP;
    public CommodityPanel cp[];
    
    private void Build() {
        JP = new JPanel(null);
        
        oo = User.getAllCommodities();
        if(oo.length == 0);
        cp = new CommodityPanel[oo.length];
        for(int i=0; i<oo.length; i++) {
            cp[i] = new CommodityPanel(oo[i], FJP);
            cp[i].setBounds(0, i*200, cp[i].getWidth(), cp[i].getHeight());
            JP.add(cp[i]);
            JP.updateUI();
            JP.validate();
            this.setViewportView(JP);
            JP.setPreferredSize(new Dimension(JP.getWidth(), JP.getHeight() + cp[i].getHeight()));
            System.out.println(oo[i].toString());
        }
        JP.setPreferredSize(new Dimension(400,oo.length*200));
        JP.setBackground(new Color(252,226,203));
        
//        JSP.setViewportView(JP);
//        JSP.getViewport().setLayout(new BorderLayout());
//        JSP.getViewport().add(JP, BorderLayout.CENTER);
//        this.setSize(400, 800);
        this.setBounds(50, 50, 400, 600);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.getVerticalScrollBar().setUnitIncrement(15);
    }
    public CommodityList(CommoditySelectPanel FJP) {
        this.FJP = FJP;
        Build();
    }


}
