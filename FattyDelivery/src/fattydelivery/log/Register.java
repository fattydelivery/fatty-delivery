package fattydelivery.log;

import fattydelivery.lib.*;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class Register extends javax.swing.JFrame implements ActionListener {

    JPanel jp;
    JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl9;
    JTextField jtf1, jtf3, jtf4, jtf5, jtf7;
    JPasswordField jpf;
    JTextArea jtr;
    Icon i;
    JRadioButton jrb1, jrb2;
    ButtonGroup bg;
    JButton jb;
    JComboBox JCBdoby;
    JComboBox JCBdobm;
    JComboBox JCBdobd;
    JLabel JLusertype, JLrpassword;
    JPasswordField JPFrpassword;
    JRadioButton JRBcustomer, JRBbussiness;
    ButtonGroup BG;
    User u;
    String str, regex;
    Pattern pattern;
    Matcher matcher;

    public Register() {
        u = new User();
        jp = new JPanel();
        jp.setLayout(null);
        jp.setBackground(Color.WHITE);

        jl1 = new JLabel("username");
        Font f1 = new Font("黑体", Font.PLAIN, 20);
        jl1.setFont(f1);
        jl1.setOpaque(true);
        jl1.setBackground(Color.WHITE);
        jl1.setForeground(Color.red);
        jl1.setBounds(160, 60, 80, 25);
        jp.add(jl1);

        jl2 = new JLabel("password");
        jl2.setFont(f1);
        jl2.setOpaque(true);
        jl2.setBackground(Color.WHITE);
        jl2.setForeground(Color.red);
        jl2.setBounds(160, 40 + 60, 80, 25);
        jp.add(jl2);

        JLrpassword = new JLabel("repeat again");
        JLrpassword.setFont(f1);
        JLrpassword.setOpaque(true);
        JLrpassword.setBackground(Color.WHITE);
        JLrpassword.setForeground(Color.red);
        JLrpassword.setBounds(120, 80 + 60, 120, 25);
        jp.add(JLrpassword);

        jl3 = new JLabel("gender");
        jl3.setFont(f1);
        jl3.setOpaque(true);
        jl3.setBackground(Color.WHITE);
        jl3.setForeground(Color.red);
        jl3.setBounds(170, 80 + 100, 60, 25);
        jp.add(jl3);

        jl4 = new JLabel("phone");
        jl4.setFont(f1);
        jl4.setOpaque(true);
        jl4.setBackground(Color.WHITE);
        jl4.setForeground(Color.red);
        jl4.setBounds(170, 120 + 100, 60, 25);
        jp.add(jl4);

        jl5 = new JLabel("email");
        jl5.setFont(f1);
        jl5.setOpaque(true);
        jl5.setBackground(Color.WHITE);
        jl5.setForeground(Color.red);
        jl5.setBounds(170, 160 + 100, 60, 25);
        jp.add(jl5);

        jl6 = new JLabel("date of birth");
        jl6.setFont(f1);
        jl6.setOpaque(true);
        jl6.setBackground(Color.WHITE);
        jl6.setForeground(Color.red);
        jl6.setBounds(120, 200 + 100, 130, 25);
        jp.add(jl6);

        jl7 = new JLabel("address");
        jl7.setFont(f1);
        jl7.setOpaque(true);
        jl7.setBackground(Color.WHITE);
        jl7.setForeground(Color.red);
        jl7.setBounds(160, 250 + 100, 80, 25);
        jp.add(jl7);

        JLusertype = new JLabel("usertype");
        JLusertype.setFont(f1);
        JLusertype.setOpaque(true);
        JLusertype.setBackground(Color.WHITE);
        JLusertype.setForeground(Color.red);
        JLusertype.setBounds(160, 300 + 100, 80, 25);
        jp.add(JLusertype);

        jtf1 = new JTextField();
        jtf1.setBounds(260, 0 + 60, 250, 25);
        jtf1.setBackground(Color.WHITE);
        jp.add(jtf1);

        jpf = new JPasswordField();
        jpf.setBounds(260, 40 + 60, 250, 25);
        jpf.setBackground(Color.WHITE);
        jp.add(jpf);

        JPFrpassword = new JPasswordField();
        JPFrpassword.setBounds(260, 80 + 60, 250, 25);
        JPFrpassword.setBackground(Color.WHITE);
        jp.add(JPFrpassword);

        jrb1 = new JRadioButton("Male");
        jrb1.setFont(f1);
        jrb1.setOpaque(true);
        jrb1.setBackground(Color.WHITE);
        jrb1.setForeground(Color.red);
        jrb1.setBounds(260, 80 + 100, 80, 25);
        jrb1.setSelected(true);
        jp.add(jrb1);

        jrb2 = new JRadioButton("Female");
        jrb2.setFont(f1);
        jrb2.setOpaque(true);
        jrb2.setBackground(Color.WHITE);
        jrb2.setForeground(Color.red);
        jrb2.setBounds(380, 80 + 100, 100, 25);
        jp.add(jrb2);

        jtf3 = new JTextField();
        jtf3.setBounds(260, 120 + 100, 250, 25);
        jtf3.setBackground(Color.WHITE);
        jp.add(jtf3);

        jtf4 = new JTextField();
        jtf4.setBounds(260, 160 + 100, 250, 25);
        jtf4.setBackground(Color.WHITE);
        jp.add(jtf4);

        jtr = new JTextArea();
//        jtr.setBounds(260, 240+100, 250, 50);
        jtr.setBackground(Color.WHITE);
        jtr.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153)));
        jtr.setLineWrap(true);
        JScrollPane JSP = new JScrollPane(jtr);
        JSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        JTAaddr.setBounds(150, 10+(MHEIGHT+DELTAY)*7, MWIDTH2+20, MHEIGHT*5);
        JSP.setBounds(260, 240 + 100, 250, 50);
        jp.add(JSP);

        JCBdoby = new JComboBox();
        for (int i = 1950; i <= 2019; i++) {
            JCBdoby.addItem(Integer.toString(i));
        }
        JCBdoby.setBounds(260, 200 + 100, 80, 25);
        JCBdoby.setBackground(Color.WHITE);
        jp.add(JCBdoby);

        JCBdobm = new JComboBox();
        for (int i = 1; i <= 12; i++) {
            JCBdobm.addItem(Integer.toString(i));
        }
        JCBdobm.setBounds(370, 200 + 100, 50, 25);
        JCBdobm.setBackground(Color.WHITE);
        jp.add(JCBdobm);

        JCBdobd = new JComboBox();
        for (int i = 1; i <= 31; i++) {
            JCBdobd.addItem(Integer.toString(i));
        }
        JCBdobd.setBounds(450, 200 + 100, 50, 25);
        JCBdobd.setBackground(Color.WHITE);
        jp.add(JCBdobd);

        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);

        JRBbussiness = new JRadioButton("Bussiness");
        JRBbussiness.setFont(f1);
        JRBbussiness.setOpaque(true);
        JRBbussiness.setBackground(Color.WHITE);
        JRBbussiness.setForeground(Color.red);
        JRBbussiness.setBounds(260, 300 + 100, 120, 25);
        JRBbussiness.setSelected(true);
        jp.add(JRBbussiness);

        JRBcustomer = new JRadioButton("Customer");
        JRBcustomer.setFont(f1);
        JRBcustomer.setOpaque(true);
        JRBcustomer.setBackground(Color.WHITE);
        JRBcustomer.setForeground(Color.red);
        JRBcustomer.setBounds(380, 300 + 100, 120, 25);
        jp.add(JRBcustomer);
        BG = new ButtonGroup();
        BG.add(JRBbussiness);
        BG.add(JRBcustomer);

        jl9 = new JLabel();
        jl9 = new JLabel();
        i = new ImageIcon("img\\picture2.png");
        jl9.setIcon(i);
        jl9.setBounds(50, 350 + 140, i.getIconWidth(), i.getIconHeight());
        jp.add(jl9, new Integer(Integer.MIN_VALUE));

        jb = new JButton("Register");
        jb.setFont(f1);
        jb.setOpaque(true);
        jb.setBackground(Color.WHITE);
        jb.setForeground(Color.red);
        jb.setBounds(280, 310 + 140, 120, 25);
        jp.add(jb);
        jb.addActionListener(this);

        User u = new User();

        add(jp);
        setTitle("Register");
        setVisible(true);
        setSize(700, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new LoginFrame();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        u.setUsername(jtf1.getText());
        u.setPassword(new String(jpf.getPassword()));
        if (jrb1.isSelected()) {
            u.setGender("male");
        } else {
            u.setGender("female");
        }
        u.setPhone(jtf3.getText());
        u.setEmail(jtf4.getText());
        u.setDob(JCBdoby.getSelectedItem().toString() + "-" + JCBdobm.getSelectedItem().toString() + "-" + JCBdobd.getSelectedItem().toString());
        u.setAddr(jtr.getText());
        u.setRegTime();
        if (JRBcustomer.isSelected()) {
            u.setUsertype(1);
        } else {
            u.setUsertype(2);
        }
//        System.out.println("???");
        if (jtf1.getText().length() == 0) {
            JOptionPane.showMessageDialog(jp, "Username must not be empty.");
            return;
        }
//        System.out.println("username ok");
        String s1 = new String(jpf.getPassword()), s2 = new String(JPFrpassword.getPassword());
        if (!s1.equals(s2)) {
            JOptionPane.showMessageDialog(jp, "Inconsistent password input twice.");
            return;
        }
//        System.out.println("password ok");
        if (jtf3.getText().length() == 0) {
            JOptionPane.showMessageDialog(jp, "Phone number must not be empty.");
            return;
        }
//        System.out.println("phone ok");
        if (jtr.getText().length() == 0) {
            JOptionPane.showMessageDialog(jp, "Address must not be empty.");
            return;
        }
//        System.out.println("addr ok");
        if (u.checkUesrname()) {
            JOptionPane.showMessageDialog(jp, "Username already exists.");
            return;
        }
//        System.out.println("user ok");

        str = jtf1.getText();
        regex = "^[a-zA-Z0-9_]+$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(jp, "Username must not be special character or Hanzi");
            return;
        }

        str = String.valueOf(jpf.getPassword());
//        regex = "^[a-zA-Z0-9]+$";
//        pattern = Pattern.compile(regex);
//        matcher = pattern.matcher(str);
//        if (!matcher.matches()) {
//            JOptionPane.showMessageDialog(jp, "Password must not be special character.");
//            return;
//        }

        str = jtf3.getText();
        regex = "^[0-9]{11}$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(jp, "Telephone number must be 11 digits.");
            return;
        }

        str = jtf4.getText();
        regex = "[a-zA-Z-]{0,}[0-9]{0,}@(([a-zA-Z0-9]-*){1,}\\.){1,3}[a-zA-Z\\-]{1,}";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(jp, "The format of email is incorrect.");
            return;
        }

        u.addToDB();
        JOptionPane.showMessageDialog(jp, "Registration Success!");
        this.dispose();
        new LoginFrame();
    }

}
