/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.customer;

import fattydelivery.lib.*;
import java.awt.Color;
import java.awt.Container;
import javax.swing.*;
/**
 *
 * @author Administrator
 */
public class CustomerFrame extends JFrame {
    private User u;
    private JTabbedPane JTP;
    
    public CustomerFrame(User u) {
        super("Welcome!");
        this.u = u;
        this.setSize(500, 900);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        JTP = new JTabbedPane();
        JTP.add("CommodityList", new CommoditySelectPanel(this.u));
        JTP.add("Order", new CustomerOrderList(u));
        CustomerInformationPage CIP = new CustomerInformationPage(u);
        CIP.setBounds(50, 25, 400, 800);
        JPanel JP = new JPanel(null);
        JP.add(CIP);
        JTP.add("User Infor", JP);
        JTP.setBounds(0, 0, 500, 900);
        this.add(JTP);
        
        this.setVisible(true);
       
        Container pane = this.getContentPane();
        pane.setBackground(new Color(252,226,203));
        JP.setBackground(new Color(252,226,203));
   }
    /*
    public static void main(String[] args) {
        User u = new User();
        u.setUsername("beiyu");
        u.setPassword("beiyu");
        u.setUsertype(1);
        u.getFromDB();
        System.out.println(u.toString());
        new CustomerFrame(u);
    }*/
    
}
