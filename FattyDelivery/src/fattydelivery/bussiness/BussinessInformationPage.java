/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.bussiness;

import fattydelivery.lib.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class BussinessInformationPage extends JPanel implements ActionListener {

    private User u;
    private JLabel JLusername, JLpassword, JLrpassword, JLgender, JLemail, JLphone, JLdob, JLaddr, JLconfirm;
    private JTextField JTFusername, JTFemail, JTFphone;
    private JPasswordField JPFpassword, JPFrpassword, JPFconfirm;
    private JTextArea JTAaddr;
    private JComboBox JCBdoby, JCBdobm, JCBdobd;
    private JRadioButton JRBmale, JRBfemale;
    private ButtonGroup BG1;
    private JButton JBsave, JBcancel;
    private static int MHEIGHT = 30, MWIDTH = 150, MWIDTH2 = 200, WX = 10, DELTAY = 30;
    private String str, regex;
    private Pattern pattern;
    private Matcher matcher;

    private void BuildCom() {
        JLusername = new JLabel("Username:");
        JLusername.setBounds(WX, 10, MWIDTH, MHEIGHT);
        JLpassword = new JLabel("New Password:");
        JLpassword.setBounds(WX, 10 + (MHEIGHT + DELTAY) * 1, MWIDTH, MHEIGHT);
        JLrpassword = new JLabel("Repeat again:");
        JLrpassword.setBounds(WX, 10 + (MHEIGHT + DELTAY) * 2, MWIDTH, MHEIGHT);
        JLgender = new JLabel("Gender:");
        JLgender.setBounds(WX, 10 + (MHEIGHT + DELTAY) * 3, MWIDTH, MHEIGHT);
        JLemail = new JLabel("Email:");
        JLemail.setBounds(WX, 10 + (MHEIGHT + DELTAY) * 4, MWIDTH, MHEIGHT);
        JLphone = new JLabel("Phone:");
        JLphone.setBounds(WX, 10 + (MHEIGHT + DELTAY) * 5, MWIDTH, MHEIGHT);
        JLdob = new JLabel("DateofBirth:");
        JLdob.setBounds(WX, 10 + (MHEIGHT + DELTAY) * 6, MWIDTH, MHEIGHT);
        JLaddr = new JLabel("Address:");
        JLaddr.setBounds(WX, 10 + (MHEIGHT + DELTAY) * 8, MWIDTH, MHEIGHT);
        JLconfirm = new JLabel("Old Password:");
        JLconfirm.setBounds(WX, 10 + (MHEIGHT + DELTAY) * 10, MWIDTH, MHEIGHT);

        JTFusername = new JTextField(20);
        JTFusername.setBounds(150, 10, MWIDTH2, MHEIGHT);
        JTFusername.setEditable(false);
        JTFusername.setText(u.getUsername());
        JTFemail = new JTextField(20);
        JTFemail.setBounds(150, 10 + (MHEIGHT + DELTAY) * 4, MWIDTH2, MHEIGHT);
        JTFemail.setText(u.getEmail());
        JTFphone = new JTextField(20);
        JTFphone.setBounds(150, 10 + (MHEIGHT + DELTAY) * 5, MWIDTH2, MHEIGHT);
        JTFphone.setText(u.getPhone());

        JPFpassword = new JPasswordField(20);
        JPFpassword.setBounds(150, 10 + (MHEIGHT + DELTAY) * 1, MWIDTH2, MHEIGHT);
        JPFrpassword = new JPasswordField(20);
        JPFrpassword.setBounds(150, 10 + (MHEIGHT + DELTAY) * 2, MWIDTH2, MHEIGHT);
        JPFconfirm = new JPasswordField(20);
        JPFconfirm.setBounds(150, 10 + (MHEIGHT + DELTAY) * 10, MWIDTH2, MHEIGHT);

        JTAaddr = new JTextArea(5, 20);
//        JTAaddr.setBounds(150, 10+(MHEIGHT+DELTAY)*7, MWIDTH2, MHEIGHT*5);
        JTAaddr.setText(u.getAddr());
        JTAaddr.setLineWrap(true);
        JTAaddr.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane JSP = new JScrollPane(JTAaddr);
        JSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        JTAaddr.setBounds(150, 10+(MHEIGHT+DELTAY)*7, MWIDTH2+20, MHEIGHT*5);
        JSP.setBounds(150, 10 + (MHEIGHT + DELTAY) * 7, MWIDTH2, MHEIGHT * 5);
        JTAaddr.setText(u.getAddr());
//        JSP.getHorizontalScrollBar().setBounds(200, 10+(MHEIGHT+DELTAY)*7, 30, MHEIGHT*5);

        JCBdoby = new JComboBox();
        JCBdoby.setBounds(150, 10 + (MHEIGHT + DELTAY) * 6, 75, MHEIGHT);
//        JCBdoby.addItem("----");
        for (int i = 1950; i <= 2019; i++) {
            JCBdoby.addItem(Integer.toString(i));
        }
        JCBdoby.setSelectedItem(Integer.toString(u.getDob().getYear() + 1900));

        JCBdobm = new JComboBox();
        JCBdobm.setBounds(235, 10 + (MHEIGHT + DELTAY) * 6, 50, MHEIGHT);
        for (int i = 1; i <= 12; i++) {
            JCBdobm.addItem(Integer.toString(i));
        }
        JCBdobm.setSelectedItem(Integer.toString(u.getDob().getMonth()));

        JCBdobd = new JComboBox();
        JCBdobd.setBounds(295, 10 + (MHEIGHT + DELTAY) * 6, 50, MHEIGHT);
        for (int i = 1; i <= 31; i++) {
            JCBdobd.addItem(Integer.toString(i));
        }
        JCBdobd.setSelectedItem(Integer.toString(u.getDob().getDay()));

        JRBmale = new JRadioButton("Male");
        JRBfemale = new JRadioButton("Female");
        BG1 = new ButtonGroup();
        BG1.add(JRBmale);
        BG1.add(JRBfemale);
        if ("male".equalsIgnoreCase(u.getGender())) {
            JRBmale.setSelected(true);
        } else {
            JRBfemale.setSelected(true);
        }
        JRBmale.setBounds(150, 10 + (MHEIGHT + DELTAY) * 3, 70, MHEIGHT);
        JRBfemale.setBounds(225, 10 + (MHEIGHT + DELTAY) * 3, 100, MHEIGHT);

        JBsave = new JButton("Save");
        JBsave.setBounds(20, 10 + (MHEIGHT + DELTAY) * 11, MWIDTH, 40);
        JBsave.addActionListener(this);
        JBcancel = new JButton("Cancel");
        JBcancel.setBounds(180, 10 + (MHEIGHT + DELTAY) * 11, MWIDTH, 40);
        JBcancel.addActionListener(this);

        add(JLusername);
        add(JTFusername);
        add(JLpassword);
        add(JPFpassword);
        add(JLrpassword);
        add(JPFrpassword);
        add(JLgender);
        add(JRBmale);
        add(JRBfemale);
        add(JLemail);
        add(JTFemail);
        add(JLphone);
        add(JTFphone);
        add(JLdob);
        add(JCBdoby);
        add(JCBdobm);
        add(JCBdobd);
        add(JLaddr); // add(JTAaddr);
        add(JLconfirm);
        add(JPFconfirm);
        add(JBsave);
        add(JBcancel);
        add(JSP);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        add(JSP.getHorizontalScrollBar());
//        JLusername.setForeground(Color.white);
//        JLemail.setForeground(Color.white);
//        JLphone.setForeground(Color.white);
//        JLpassword.setForeground(Color.white);
//        JLrpassword.setForeground(Color.white);
//        JLconfirm.setForeground(Color.white);
//        JLaddr.setForeground(Color.white);
//        JLdob.setForeground(Color.white);
//        JLgender.setForeground(Color.white);
        JTFusername.setBackground(new Color(249, 203, 168));
        JTFemail.setBackground(new Color(249, 203, 168));
        JTFphone.setBackground(new Color(249, 203, 168));
        JPFpassword.setBackground(new Color(249, 203, 168));
        JPFconfirm.setBackground(new Color(249, 203, 168));
        JTAaddr.setBackground(new Color(249, 203, 168));
        JCBdoby.setBackground(new Color(249, 203, 168));
        JCBdobm.setBackground(new Color(249, 203, 168));
        JPFrpassword.setBackground(new Color(249, 203, 168));
        JCBdobd.setBackground(new Color(249, 203, 168));
        JRBmale.setBackground(new Color(249, 203, 168));
        JRBfemale.setBackground(new Color(249, 203, 168));
        JBcancel.setBackground(new Color(241, 156, 67));
        JBsave.setBackground(new Color(241, 156, 67));

    }

    public BussinessInformationPage(User u) {
        super(null);
        this.u = u;
        BuildCom();
    }

    private void Reset() {
        JTFusername.setText(u.getUsername());
        JTFemail.setText(u.getEmail());
        JTFphone.setText(u.getPhone());
        JTAaddr.setText(u.getAddr());
        JPFpassword.setText("");
        JPFrpassword.setText("");
        if ("male".equalsIgnoreCase(u.getGender())) {
            JRBmale.setSelected(true);
        } else {
            JRBfemale.setSelected(true);
        }
        JCBdoby.setSelectedItem(Integer.toString(u.getDob().getYear() + 1900));
        JCBdobm.setSelectedItem(Integer.toString(u.getDob().getMonth() + 1));
        JCBdobd.setSelectedItem(Integer.toString(u.getDob().getDay()));
        JPFconfirm.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == JBsave) {
            u.setPassword(new String(JPFconfirm.getPassword()));
            if(JPFconfirm.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Please enter your password! ");
                return;
            }
            if (!u.checkPassword()) {
                JOptionPane.showMessageDialog(this, "Password is wrong!");
                return;
            }

            if (JPFpassword.getPassword().length != 0 && JPFrpassword.getPassword().length != 0) {
                if (new String(JPFpassword.getPassword()).equals(new String(JPFrpassword.getPassword()))) {
                    u.setPassword(new String(JPFpassword.getPassword()));
                } else {
                    JOptionPane.showMessageDialog(this, "Inconsistent password entered twice!");
                    return;
                }
            }
//            else {
//                JOptionPane.showMessageDialog(this, "Password must not be empty.");
//                return;
//            }

            str = JTFusername.getText();
            regex = "^[a-zA-Z0-9_]+$";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(str);
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(this, "Username must not be special character.");
                return;
            }

//            str = String.valueOf(JPFpassword.getPassword());
//            regex = "^[a-zA-Z0-9]+$";
//            pattern = Pattern.compile(regex);
//            matcher = pattern.matcher(str);
//            if (!matcher.matches()) {
//                JOptionPane.showMessageDialog(this, "Password must not be special character.");
//                return;
//            }

            str = JTFemail.getText();
            regex = "[a-zA-Z-]{0,}[0-9]{0,}@(([a-zA-Z0-9]-*){1,}\\.){1,3}[a-zA-Z\\-]{1,}";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(str);
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(this, "The format of email is incorrect.");
                return;
            }

            str = JTFphone.getText();
            regex = "^[0-9]{11}$";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(str);
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(this, "Telephone number must be 11 digits.");
                return;
            }

            if (JRBmale.isSelected()) {
                u.setGender("male");
            } else {
                u.setGender("female");
            }

            u.setEmail(JTFemail.getText());

            u.setPhone(JTFphone.getText());

            u.setDob(JCBdoby.getSelectedItem().toString() + "-" + JCBdobm.getSelectedItem().toString() + "-" + JCBdobd.getSelectedItem().toString());

            u.setAddr(JTAaddr.getText());

            u.updateToDB();
            JOptionPane.showMessageDialog(this, "Update success");
            Reset();
        } else if (e.getSource() == JBcancel) {
            Reset();
        }
    }
}
