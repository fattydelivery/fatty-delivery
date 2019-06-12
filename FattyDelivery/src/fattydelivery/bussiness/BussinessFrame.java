/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.bussiness;

import fattydelivery.lib.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Administrator
 */
public class BussinessFrame {
    private User u;
    
    public BussinessFrame(User u) {
        this.u = u;
        
        UIManager.put("Button.font", PJFont.ft);
        UIManager.put("Label.font", PJFont.ft);
        UIManager.put("TextField.font", PJFont.ft);
        UIManager.put("TextArea.font", PJFont.ft);
        UIManager.put("ComboBox.font", PJFont.ft);
        UIManager.put("PasswordField.font", PJFont.ft);
        UIManager.put("RadioButton.font", PJFont.ft);
        UIManager.put("TabbedPane.font", PJFont.ft);
        
        JFrame JF = new JFrame("CusteromPage");
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF.setSize(1600, 900);
        JF.setLocationRelativeTo(null);
        JF.setResizable(false);
        JF.setLayout(null);
        
        Container pane = JF.getContentPane();
        //pane.setBackground(new Color(236,229,213));
        pane.setBackground(new Color(252,226,203));
        JTabbedPane JTP = new JTabbedPane();
        JTP.addTab("MyCommodityList", new BussinessCommodityList(this.u));
        JTP.addTab("OrderList", new BussinessOrderList(this.u));
//        JTP.addTab("MyInformation", new CustomerInformationPage(this.u));
        JPanel JP = new BussinessInformationPage(this.u);
        JTP.setBounds(0, 0, 1200, 900);
        JP.setBounds(1225, 50, 360, 800);
        JF.add(JTP);
        JF.add(JP);

        JF.setVisible(true);
        
         JP.setBackground(new Color(252,226,203));
    }
    /*
    public static void main(String[] args) {
        User u = new User();
        u.setUsername("beiyu");
//        u.setUsertype(1);
//        u.getFromDB();
//        u.toString();
        u.setPassword("beiyu");
        u.setEmail("729320011@qq.com");
        u.setAddr("Haikou, Hainan");
        u.setGender("Male");
        u.setRegTime();
        u.setPhone("1566667912");
        u.setUsertype(2);
        u.setDob("2000-01-06");
        u.addToDB();
        
        Commodity com = new Commodity();
        com.setId("food1");
        com.setCost(32.66);
        com.setBussinessId("beiyu");
        com.addToDB();
//        System.out.println(u.getDob());
//        System.out.println(u.getDob().getYear());
        BussinessFrame bf = new BussinessFrame(u);
    }*/
}
