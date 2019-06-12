/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.customer;

import fattydelivery.lib.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class CommoditySelectPanel extends JPanel implements ActionListener {

    private User u;
    private CommodityList cp;
    private JScrollPane JSP;
    private JTable JTlist;
    private static String[] ColName = {"id", "cost"};
    private String[][] Content;
    private Vector<Commodity> oo;
    private JButton JBpay, JBadd, JBclr;
    private DefaultTableModel dtm;
    private JLabel JLtot;
    private double cost;

    private void updateTable() {
        dtm = new DefaultTableModel(null, ColName);
        JTlist.setModel(dtm);
        if (oo.size() == 0) {
            cost = 0;
            JLtot.setText("Total: 0");
            return;
        }
        Content = new String[oo.size()][2];
        cost = 0;
        for (int i = 0; i < oo.size(); i++) {
//            System.out.println(oo.get(i).toString());
            Content[i][0] = oo.get(i).getId();
            Content[i][1] = String.valueOf(oo.get(i).getCost());
            cost += oo.get(i).getCost();
        }
        dtm = new DefaultTableModel(Content, ColName);
        JTlist.setModel(dtm);
        JLtot.setText("Total: " + String.valueOf(cost));
    }

    public CommoditySelectPanel(User u) {
        oo = new Vector<Commodity>();
        cp = new CommodityList(this);
        cost = 0;
        this.u = u;
        this.add(cp);
        this.setLayout(null);
        this.setBounds(0, 0, cp.getWidth() + 100, cp.getHeight() + 300);

        JLtot = new JLabel("Total: " + String.valueOf(cost));
        JBpay = new JButton("Buy Now");
        JBadd = new JButton("Add");
        JBclr = new JButton("clear");
        JLtot.setBounds(100, 800, 200, 30);
        JBpay.setBounds(300, 800, 150, 30);
        JBadd.setBounds(100, 650, 100, 30);
        JBclr.setBounds(300, 650, 100, 30);
        JBpay.addActionListener(this);
        JBclr.addActionListener(this);
        JBadd.addActionListener(this);

        JTlist = new JTable();
        updateTable();
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        JTlist.setDefaultRenderer(Object.class, r);
        JTlist.getTableHeader().setReorderingAllowed(false);
        JTlist.getTableHeader().setResizingAllowed(false);
//        JTlist.setFont(PJFont.ft);
        JTlist.getTableHeader().setFont(PJFont.ft);
        JTlist.setRowHeight(20);
        JTlist.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        JTlist.getTableHeader().setBounds(30, 30, 540, 30);
//        JTlist.setBounds(30, 60, 540, 600);
        JSP = new JScrollPane(JTlist);
        JSP.setBounds(50, 680, 400, 100);
        JSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        JPlist.add(JTlist);
        this.add(JSP);

        this.add(JLtot);
        this.add(JBpay);
        this.add(JBclr);
        this.add(JBadd);

        JTlist.setBackground(new Color(252, 226, 203));
        JSP.getViewport().setBackground(new Color(252, 226, 203));
        JBpay.setBackground(new Color(241, 156, 67));
        this.setBackground(new Color(252, 226, 203));
        JTlist.setBackground(new Color(249, 203, 168));
        JTlist.getTableHeader().setBackground(new Color(246, 177, 108));
    }

    private void addOrder(String bsid, Vector<String> com, double totcost) {
        Order ord = new Order();

        ord.setAddress(u.getAddr());
        ord.setAmount(totcost);
        ord.setBussinessId(bsid);
        ord.setCustomId(u.getUsername());
        String[] str = new String[com.size()];
        for (int i = 0; i < com.size(); i++) {
            str[i] = com.get(i);
        }
        ord.setStatus("preparing");
        ord.setCommodityId(str);
        ord.setPhone(u.getPhone());
        ord.addToDB();
    }

    private void addOrders() {
        Vector<String> ids = new Vector<String>();
        Vector<String> tmp = null;
        for (int i = 0; i < oo.size(); i++) {
            Commodity tt = oo.get(i);
            String bsi = tt.getBussinessId();
            boolean f = true;
            for (int j = 0; j < ids.size(); j++) {
                if (bsi.equals(ids.get(j))) {
                    f = false;
                    break;
                }
            }
            if (f) {
                ids.add(bsi);
            }
        }
//        System.out.println("-----------");
//        for(int i=0; i<ids.size(); i++) System.out.println(ids.get(i));
        for (int i = 0; i < ids.size(); i++) {
            tmp = new Vector<String>();
            double totcost = 0;
            for (int j = 0; j < oo.size(); j++) {
                if (oo.get(j).getBussinessId().equals(ids.get(i))) {
                    tmp.add(oo.get(j).getId());
                    totcost += oo.get(i).getCost();
//                    System.out.println("???????");
                }
            }
            addOrder(ids.get(i), tmp, totcost);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == JBpay) {
            if (oo.size() == 0) {
                JOptionPane.showMessageDialog(this, "You haven't selected anything!");
                return;
            }
            addOrders();
            JOptionPane.showMessageDialog(this, "Add Order Success!");

            oo.clear();
            this.updateTable();
            return;
        } else if(e.getSource() == JBclr) {
            oo.clear();
            this.updateTable();
//        System.out.println(e.getActionCommand());
//            Commodity c = new Commodity();
//            c.setId(e.getActionCommand());
//            c.getFromDB();
//            oo.add(c);
//            this.updateTable();
        } else if(e.getSource() == JBadd) {
            oo.clear();
            for(int i=0; i<cp.cp.length; i++) {
                Commodity c = new Commodity();
                c.setId(cp.cp[i].getName());
                c.getFromDB();
                for(int j=0; j<cp.cp[i].getNum(); j++) {
                    oo.add(c);
                }
            }
            this.updateTable();
        }
    }
//    
//    public static void main(String args[]) {
//        JFrame JF = new JFrame("qwq");
//        User u = new User();
//        u.setUsername("beiyu");
//        u.setPassword("beiyu");
//        u.setUsertype(1);
//        u.getFromDB();
//        System.out.println(u.toString());
//        CommoditySelectPanel csp = new CommoditySelectPanel(u);
//        JF.add(csp);
//        JF.setSize(500, 900);
//        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JF.setLocationRelativeTo(null);
//        JF.setLayout(null);
//        JF.setVisible(true);
//    }
}
