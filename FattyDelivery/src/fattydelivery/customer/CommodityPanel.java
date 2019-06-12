/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.customer;

import fattydelivery.lib.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class CommodityPanel extends JPanel implements ActionListener {

    private CommoditySelectPanel FJP;
    private JLabel JLcommodityid, JLcommodityname, JLprice, JLpricea, JLimg;
    private String filePath;
    public JButton JBadd, JBdel;
    private JLabel JLnum;
    private int num;

    public CommodityPanel(Commodity c, CommoditySelectPanel FJP) {
        this.FJP = FJP;
        this.setLayout(null);
        JLcommodityid = new JLabel("Name");
        JLcommodityname = new JLabel(c.getId());
        JLprice = new JLabel("Price");
        JLpricea = new JLabel(String.valueOf(c.getCost()));
        JBadd = new JButton("+");
        JBdel = new JButton("-");
        JLnum = new JLabel(String.valueOf(num));
        File ff = new File("img\\" + c.getBussinessId());
        if (!ff.exists()) {
            ff.mkdirs();
        }
//        filePath = "img\\" + c.getId() + "." + c.getImgext();
        filePath = "img\\" + c.getBussinessId() + "\\" + c.getId() + "." + c.getImgext();
//        System.out.println(filePath);
        c.writeToLocal(filePath);
        ImageIcon imageIcon = new ImageIcon(filePath);
        Image image = imageIcon.getImage();
        Image scaledInstance = image.getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
        imageIcon.setImage(scaledInstance);
        JLimg = new JLabel(imageIcon);

        JLcommodityid.setBounds(150, 30, 100, 30);
        JLcommodityname.setBounds(250, 30, 100, 30);
        JLprice.setBounds(150, 80, 100, 30);
        JLpricea.setBounds(250, 80, 100, 30);
        JLimg.setBounds(25, 30, 100, 100);
        JBadd.setBounds(315, 110, 50, 30);
        JLnum.setBounds(280, 110, 40, 30);
        JBdel.setBounds(215, 110, 45, 30);
        JBadd.addActionListener(FJP);
        JBadd.addActionListener(this);
        JBdel.addActionListener(this);
        JBadd.setActionCommand(c.getId());

        this.add(JLcommodityid);
        this.add(JLcommodityname);
        this.add(JLprice);
        this.add(JLpricea);
        this.add(JLimg);
        this.add(JBadd);
        this.add(JBdel);
        this.add(JLnum);
        this.setSize(400, 150);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JBadd.setBackground(new Color(241, 156, 67));
        JBdel.setBackground(new Color(241, 156, 67));
        this.setBackground(new Color(252, 226, 203));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == JBadd) {
            if(num >= 99) {
                JOptionPane.showMessageDialog(this, "This is too large...");
                return;
            }
            num += 1;
            JLnum.setText(String.valueOf(num));
        } else if(e.getSource() == JBdel) {
            if(num <= 0) return;
            num -= 1;
            JLnum.setText(String.valueOf(num));
        }
    }
    public int getNum() {
        return num;
    }
    public String getName() {
        return JLcommodityname.getText();
    }
}
