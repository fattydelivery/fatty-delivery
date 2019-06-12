/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.customer;

import fattydelivery.lib.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Administrator
 */
public class OrderDetailFrame extends JFrame implements ActionListener {
    private Order ord;
    private JPanel JP;
    private JLabel JLorderid, JLfoodlist, JLorderid1, JLstatus, JLstatus1;
    private JTable JTlist;
    private JScrollPane JSP;
    private String[] ColName = { "foodid", "cost", "bussinessid"};
    private String[][] Content;
    private String[] com;
    private DefaultTableModel dtm;
    private JButton JBcancel, JBclose;
    
    void updateTable() {
        dtm = new DefaultTableModel(null, ColName);
        if(com.length == 0) return;
        Content = new String[com.length][3];
        for(int i=0; i<com.length; i++) {
            Commodity tmp = new Commodity();
            tmp.setId(com[i].trim());
            tmp.getFromDB();
            Content[i][0] = tmp.getId();
            Content[i][1] = String.valueOf(tmp.getCost());
            Content[i][2] = tmp.getBussinessId();
        }
        dtm = new DefaultTableModel(Content, ColName);
        JTlist.setModel(dtm);
    }
    private void Build() {
        com = ord.getCommodityIds();
        JP = new JPanel(null);
//        for(int i=0; i<com.length; i++) System.out.println(com[i]);
        
        JLorderid = new JLabel("orderid");
        JLorderid1 = new JLabel(String.valueOf(ord.getOrderid()));
        JLstatus = new JLabel("status");
        JLstatus1 = new JLabel(ord.getStatus());
        JLfoodlist = new JLabel("food list");
        
        JLorderid.setBounds(100, 30, 120, 30);
        JLorderid1.setBounds(250, 30, 250, 30);
        JLstatus.setBounds(100, 90, 120, 30);
        JLstatus1.setBounds(250, 90, 250, 30);
        JLfoodlist.setBounds(50, 150, 120, 30);

        JTlist = new JTable();
        updateTable();
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        JTlist.setDefaultRenderer(Object.class, r);
        JTlist.getTableHeader().setReorderingAllowed(false);
        JTlist.getTableHeader().setResizingAllowed(false);
        JTlist.setFont(PJFont.ft);
        JTlist.getTableHeader().setFont(PJFont.ft);
        JTlist.setRowHeight(35);
        JTlist.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        JTlist.getTableHeader().setBounds(30, 30, 540, 30);
//        JTlist.setBounds(30, 60, 540, 600);
        JSP = new JScrollPane(JTlist);
        JSP.setBounds(30, 180, 440, 520);
        JSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        JBcancel = new JButton("Cancel");
        JBclose = new JButton("Close");
        JBcancel.setBounds(50, 730, 120, 30);
        JBclose.setBounds(300, 730, 120, 30);
        JBcancel.addActionListener(this);
        JBclose.addActionListener(this);
        if(!ord.getStatus().equalsIgnoreCase("preparing")) {
            JBcancel.setEnabled(false);
        }
        
        JP.add(JLorderid); JP.add(JLorderid1);
        JP.add(JLstatus); JP.add(JLstatus1);
        JP.add(JLfoodlist);
        JP.add(JSP);
        JP.add(JBcancel); JP.add(JBclose);
        
        this.add(JP);
        
         JTlist.setBackground(new Color(249,203,168));
        JTlist.getTableHeader().setBackground(new Color(246,177,108));
        setBackground(new Color(252,226,203));
        JBcancel.setBackground(new Color(241,156,67));
        JBclose.setBackground(new Color(241,156,67));
        JSP.getViewport().setBackground(new Color(252,226,203));
    }
    
    public OrderDetailFrame(Order ord) {
        super("Order Detail");
//        System.out.println(ord.toString());
        this.ord = ord;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(500, 800);
        this.setResizable(false);
        
        Build();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == JBclose) {
            this.dispose();
        } else {
            ord.deleteFromDB();
            this.dispose();
        }
    }
}
