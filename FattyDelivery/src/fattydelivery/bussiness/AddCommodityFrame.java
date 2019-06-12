/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.bussiness;

import fattydelivery.lib.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrator
 */
public class AddCommodityFrame extends JFrame implements ActionListener {

    private Commodity com;
    private JPanel JP;
    private JLabel JLid, JLcost, JLimg;
    private JTextField JTFid, JTFcost, JTFimg;
    private JButton JBsubmit, JBcancel, JBupload;
    private String filePath;

    public AddCommodityFrame(User u) {
        super("Add Commodity");
        com = new Commodity();
        com.setBussinessId(u.getUsername());
        Build();
    }

    private void Build() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(600, 400);
        JP = new JPanel(null);
        JP.setFont(PJFont.ft);

        JLid = new JLabel("id:");
        JLcost = new JLabel("cost:");
        JTFid = new JTextField(30);
        JTFcost = new JTextField(30);
        JLimg = new JLabel("img:");
        JTFimg = new JTextField(30);

        JLid.setFont(PJFont.ft);
        JLcost.setFont(PJFont.ft);
        JTFid.setFont(PJFont.ft);
        JTFcost.setFont(PJFont.ft);
        JLimg.setFont(PJFont.ft);
        JTFimg.setFont(PJFont.ft);

        JLid.setBounds(100, 100, 50, 30);
        JTFid.setBounds(250, 100, 250, 30);
        JLcost.setBounds(100, 150, 50, 30);
        JTFcost.setBounds(250, 150, 250, 30);
        JLimg.setBounds(100, 200, 50, 30);
        JTFimg.setBounds(250, 200, 250, 30);

        JBsubmit = new JButton("Submit");
        JBcancel = new JButton("Cancel");
        JBupload = new JButton("...");
        JBsubmit.addActionListener(this);
        JBcancel.addActionListener(this);
        JBupload.addActionListener(this);
        JBsubmit.setBounds(100, 250, 150, 50);
        JBcancel.setBounds(350, 250, 150, 50);
        JBupload.setBounds(500, 200, 50, 30);
        JBsubmit.setFont(PJFont.ft);
        JBcancel.setFont(PJFont.ft);
        JBupload.setFont(PJFont.ft);

        JP.add(JLid);
        JP.add(JTFid);
        JP.add(JLcost);
        JP.add(JTFcost);
        JP.add(JLimg);
        JP.add(JTFimg);
        JP.add(JBupload);
        JP.add(JBsubmit);
        JP.add(JBcancel);

        this.add(JP);
        this.setVisible(true);

        JBsubmit.setBackground(new Color(241, 156, 67));
        JBcancel.setBackground(new Color(241, 156, 67));
        JBupload.setBackground(new Color(241, 156, 67));
        JTFid.setBackground(new Color(249, 203, 168));
        JTFcost.setBackground(new Color(249, 203, 168));
        JTFimg.setBackground(new Color(249, 203, 168));
        JP.setBackground(new Color(252, 226, 203));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == JBsubmit) {
            String str;
            String regex;
            Pattern pattern;
            Matcher matcher;
            
            str = JTFid.getText();
            regex = "^[a-zA-Z0-9_]+$";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(str);
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(this, "Commodity name must not be special character.");
                return;
            }
            
            str = JTFcost.getText();
            regex = "^[0-9]+$";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(str);
            Matcher matcher1 = Pattern.compile("^[0-9]+.[0-9]{0,2}$").matcher(str);
            if (!matcher.matches() && !matcher1.matches()) {
                JOptionPane.showMessageDialog(this, "Cost must not be special character.");
                return;
            }
            com.setId(JTFid.getText());
            com.setCost(Double.parseDouble(JTFcost.getText()));
            if(filePath == null) {
                com.setImg("img\\fatty.jpg");
            }
            else  {
                com.setImg(filePath);
            }
//            System.out.println(com);
            if (com.addToDB() != 0) {
                JOptionPane.showMessageDialog(this, "Add Success!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "id already exists.");
            }
        } else if (e.getSource() == JBcancel) {
            this.dispose();
        } else if (e.getSource() == JBupload) {
            JFileChooser chooser = new JFileChooser("/C:/");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "gif", "png");
            chooser.setFileFilter(filter);
            int result = chooser.showOpenDialog(this);
            String uploadPath = null;
            String selectedPath = null;
            String fileName = null;
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                selectedPath = selectedFile.getPath();
                fileName = selectedFile.getName();
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
//                System.out.println(ext);
//                System.out.println(selectedPath);
//                System.out.println(selectedFile.getName());
                if (!"jpg".equalsIgnoreCase(ext)
                        && !"jpeg".equalsIgnoreCase(ext)
                        && !"gif".equalsIgnoreCase(ext)
                        && !"png".equalsIgnoreCase(ext)) {
                    JOptionPane.showMessageDialog(this, "only image file you can choose here.");
                    return;
                }
//                System.out.println(selectedFile.length());
                if(selectedFile.length() > 1024*1024) {
                    JOptionPane.showMessageDialog(this, "image size must smaller than 1MB.");
                    return;
                }
                filePath = selectedPath;
                JTFimg.setText(filePath);
            }
        }
    }

//    public static void main(String[] args) {
//        User u = new User();
//        u.setUsername("beiyu");
//        u.setUsertype(2);
//        u.getFromDB();
//        new AddCommodityFrame(u);
//    }
}
