/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.log;

import fattydelivery.bussiness.*;
import fattydelivery.customer.*;
import fattydelivery.lib.*;
import com.mysql.jdbc.PreparedStatement;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener {

    JPanel jp;
    JLabel UserName, Password, jl, j4;
    JTextField jta1;
    Icon i;
    JButton jb, jb1;
    JComboBox jcb;
    JPasswordField jpf;
    User u;
    String str, regex;
    Pattern pattern;
    Matcher matcher;

    public LoginFrame() {
        UIManager.put("Button.font", PJFont.ft);
        UIManager.put("Label.font", PJFont.ft);
        UIManager.put("TextField.font", PJFont.ft);
        UIManager.put("TextArea.font", PJFont.ft);
        UIManager.put("ComboBox.font", PJFont.ft);
        UIManager.put("PasswordField.font", PJFont.ft);
        UIManager.put("RadioButton.font", PJFont.ft);
        UIManager.put("TabbedPane.font", PJFont.ft);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        u = new User();
        jp = new JPanel();
        jp.setLayout(null);
        jp.setBackground(new Color(255, 242, 016));

        jta1 = new JTextField(10);
        jta1.setBounds(220, 100, 300, 30);
        jta1.setBackground(new Color(255, 242, 016));
        jp.add(jta1);

        jpf = new JPasswordField();
        jpf.setBounds(220, 160, 300, 30);
        jpf.setBackground(new Color(255, 242, 016));
        jp.add(jpf);

        jl = new JLabel();
        i = new ImageIcon("img\\picture1.png");
        jl.setIcon(i);
        jl.setBounds(0, 350, i.getIconWidth(), i.getIconHeight());
        jp.add(jl, new Integer(Integer.MIN_VALUE));

        UserName = new JLabel("UserName");
        Font f1 = new Font("黑体", Font.PLAIN, 18);
        UserName.setFont(f1);
        UserName.setOpaque(true);
        UserName.setBackground(new Color(255, 242, 016));
        UserName.setForeground(Color.red);
        UserName.setBounds(120, 100, 80, 30);
        jp.add(UserName);

        Password = new JLabel("Password");
        Password.setFont(f1);
        Password.setOpaque(true);
        Password.setBackground(new Color(255, 242, 016));
        Password.setForeground(Color.red);
        Password.setBounds(120, 160, 80, 30);
        jp.add(Password);

        Font f2 = new Font("黑体", Font.PLAIN, 24);
        j4 = new JLabel("Welocme to Fatty delivery");
        j4.setFont(f2);
        j4.setOpaque(true);
        j4.setBackground(new Color(255, 242, 016));
        j4.setForeground(Color.red);
        j4.setBounds(175, 40, 350, 35);
        jp.add(j4);

        jb = new JButton("Login");
        Font f3 = new Font("黑体", Font.PLAIN, 15);
        jb.setFont(f1);
        jb.setOpaque(true);
        jb.setBackground(new Color(255, 242, 016));
        jb.setForeground(Color.red);
        jb.setBounds(290, 220, 100, 30);
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                u.setUsername(jta1.getText());
                u.setPassword(new String(jpf.getPassword()));

                str = jta1.getText();
                regex = "^[a-zA-Z0-9_]+$";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(str);
                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(jp, "Username must not be special character.");
                    return;
                }

                str = String.valueOf(jpf.getPassword());
                regex = "^[a-zA-Z0-9]+$";
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(str);
                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(jp, "Password must not be special character.");
                    return;
                }

                if (jcb.getSelectedIndex() == 0) {
                    u.setUsertype(2);
                } else {
                    u.setUsertype(1);
                }
//                System.out.println(u.getUsertype());
                if (jta1.getText().length() == 0) {
                    JOptionPane.showMessageDialog(jp, "Empty UserName", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (jpf.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(jp, "Empty Password", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (u.checkPassword()) {
                    System.out.println("Success");
                    u.getFromDB();
                    if (u.getUsertype() == 1) {
                        new CustomerFrame(u);
                    } else {
                        new BussinessFrame(u);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(jp, "Incorrect User Name or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jp.add(jb);

        jb1 = new JButton("New account");
        jb1.setFont(f3);
        jb1.setOpaque(true);
        jb1.setBackground(new Color(255, 242, 016));
        jb1.setForeground(Color.red);
        jb1.setBounds(120, 260, 130, 30);
        jb1.addActionListener(this);
        jp.add(jb1);

        jcb = new JComboBox();
        jcb.addItem("Bussiness");
        jcb.addItem("Customer");
        jcb.setFont(f3);
        jcb.setOpaque(true);
        jcb.setBackground(new Color(255, 242, 016));
        jcb.setForeground(Color.red);
        jcb.setBounds(425, 260, 120, 30);
        jp.add(jcb);

        add(jp);
        setTitle("Login");
        setVisible(true);
        setSize(700, 700);
        setResizable(false);
        this.setLocationRelativeTo(null);
    }
    /*
    public static void main(String args[]) {
       LoginFrame FD=new LoginFrame();
    }*/
    // Variables declaration - do not modify                     
    // End of variables declaration                   

    @Override
    public void actionPerformed(ActionEvent e) {
        Register r = new Register();
        this.dispose();
    }

}
