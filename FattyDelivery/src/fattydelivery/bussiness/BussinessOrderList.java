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
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class BussinessOrderList extends JPanel {
    private User u;
    private Order[] o;
    private JTable JTlist;
    private JScrollPane JSP;
    private JLabel JLcustomerid, JLcommodityid, JLaddr, JLorderid, JLamount, JLdatetime, JLstatus, JLphone;
    private JTextField JTFcustomerid, JTForderid,JTFamount, JTFdatetime, JTFphone;
    private JTextArea JTAaddr;
    private JRadioButton JRBpreparing, JRBdelivering, JRBdelivered;
    private ButtonGroup BG;
    private JPanel JPlist, JPdetail;
    private static String[] ColName = {"orderid", "customerid", "amount", "addr", "phone", "status"};
    private String[][] Content;
    private DefaultTableModel dtm;
    private static int MHEIGHT1 = 30, MWIDTH1 = 30, MWIDTH2  = 150;
    private static int MWIDTH3 = 220, MWIDTH4 = 300;
    private JTable JTdtl;
    private DefaultTableModel dtl;
    private JScrollPane JSPdtl;
    private JButton JBupdate;
    private Order selectOrder;
    
    private void updateTable() {
        o = null;
        dtm = new DefaultTableModel(null, ColName);
        JTlist.setModel(dtm);
        o = u.getAllOrder();
        if(o.length == 0) return;
        Content = new String[o.length][6];
        for(int i=0; i<o.length; i++) {
            Content[i][0] = String.valueOf(o[i].getOrderid());
            Content[i][1] = o[i].getCustomId();
            Content[i][2] = String.valueOf(o[i].getAmount());
            Content[i][3] = o[i].getAddress();
            Content[i][4] = o[i].getPhone();
            Content[i][5] = o[i].getStatus();
        }
        dtm = new DefaultTableModel(Content, ColName);
        JTlist.setModel(dtm);
    }
    private void BuildJPlist() {
        JPlist = new JPanel(null);
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
        JTlist.getTableHeader().setBounds(30, 30, 540, 30);
//        JTlist.setBounds(30, 60, 540, 600);
        JSP = new JScrollPane(JTlist);
        JSP.setBounds(30, 60, 540, 600);
        JSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        JPlist.add(JTlist);
        JPlist.add(JSP);
//        JPlist.add(JTlist.getTableHeader());
        JPlist.setBounds(0, 0, 600, 900);
        this.add(JPlist);
         JTlist.setBackground(new Color(249,203,168));
        JTlist.getTableHeader().setBackground(new Color(246,177,108));
        JSP.getViewport().setBackground(new Color(252,226,203));
        setBackground(new Color(252,226,203));
        JPlist.setBackground(new Color(252,226,203));
        
        JTlist.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int r = JTlist.getSelectedRow();
                if(r != -1) {
                    updateJPdetail(o[r]);
                }
            }
        });
    }
    
    private void updateJPdetail(Order o) {
        selectOrder = o;
        JTForderid.setText(String.valueOf(o.getOrderid()));
        JTFcustomerid.setText(o.getCustomId());
        JTFamount.setText(String.valueOf(o.getAmount()));
        JTAaddr.setText(o.getAddress());
        JTFphone.setText(o.getPhone());
        JTFdatetime.setText(o.getTime().toString());
        if(o.getStatus().equalsIgnoreCase("preparing")) {
            JRBpreparing.setSelected(true);
        } else if(o.getStatus().equalsIgnoreCase("delivering")) {
            JRBdelivering.setSelected(true);
        } else {
            JRBdelivered.setSelected(true);
        }
        String[] tmp1 = o.getCommodityIds();
//        for(int i=0; i<tmp1.length; i++) System.out.println(tmp1[i]);
        String[][] tmp = new String[tmp1.length][1];
        for(int i=0; i<tmp.length; i++) tmp[i][0]=tmp1[i];
        String[] tmp2 = new String[1];
        tmp2[0]="Commodity List";
        
        dtl = new DefaultTableModel(tmp, tmp2);
        JTdtl.setModel(dtl);
    }
    
    private void BuildJPdetail() {
        JPdetail = new JPanel(null);
        
        JLorderid = new JLabel("OrderId");
        JLcustomerid = new JLabel("CustomerId");
        JLamount = new JLabel("Amount");
        JLaddr = new JLabel("Address");
        JLphone = new JLabel("Phone");
        JLstatus = new JLabel("Status");
        JLdatetime = new JLabel("DateTime");
        JLcommodityid = new JLabel("Commodities");
        
        JTForderid = new JTextField(30);
        JTForderid.setEditable(false);
        JTFcustomerid = new JTextField(30);
        JTFcustomerid.setEditable(false);
        JTFamount = new JTextField(30);
        JTFamount.setEditable(false);
        JTAaddr = new JTextArea(3, 20);
        JTAaddr.setEditable(false);
        JTFphone = new JTextField(30);
        JTFphone.setEditable(false);
        JTFdatetime = new JTextField(30);
        JTFdatetime.setEditable(false);
        
        JRBpreparing = new JRadioButton("Prepare");
        JRBdelivering = new JRadioButton("Sending");
        JRBdelivered = new JRadioButton("Sended");
        BG = new ButtonGroup();
        BG.add(JRBpreparing);
        BG.add(JRBdelivering);
        BG.add(JRBdelivered);
        
        JTdtl = new JTable();
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        JTdtl.setDefaultRenderer(Object.class, r);
        JTdtl.getTableHeader().setReorderingAllowed(false);
        JTdtl.getTableHeader().setResizingAllowed(false);
        JTdtl.setFont(PJFont.ft);
        JTdtl.getTableHeader().setFont(PJFont.ft);
        JTdtl.setRowHeight(35);
        JTdtl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        JSPdtl = new JScrollPane(JTdtl);
        JSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        JLorderid.setBounds(MWIDTH1, 30+MHEIGHT1*(2*1-2), MWIDTH2, MHEIGHT1);
        JTForderid.setBounds(MWIDTH3, 30+MHEIGHT1*(2*1-2), MWIDTH3, MHEIGHT1);
        JLcustomerid.setBounds(MWIDTH1, 30+MHEIGHT1*(2*2-2), MWIDTH2, MHEIGHT1);
        JTFcustomerid.setBounds(MWIDTH3, 30+MHEIGHT1*(2*2-2), MWIDTH3, MHEIGHT1);
        JLamount.setBounds(MWIDTH1, 30+MHEIGHT1*(2*3-2), MWIDTH2, MHEIGHT1);
        JTFamount.setBounds(MWIDTH3, 30+MHEIGHT1*(2*3-2), MWIDTH3, MHEIGHT1);
        JLphone.setBounds(MWIDTH1, 30+MHEIGHT1*(2*4-2), MWIDTH2, MHEIGHT1);
        JTFphone.setBounds(MWIDTH3, 30+MHEIGHT1*(2*4-2), MWIDTH3, MHEIGHT1);
        JLaddr.setBounds(MWIDTH1, 30+MHEIGHT1*(2*6-2), MWIDTH2, MHEIGHT1);
        JTAaddr.setBounds(MWIDTH3, 30+MHEIGHT1*(2*5-2), MWIDTH3, MHEIGHT1*5);
        JLstatus.setBounds(MWIDTH1, 30+MHEIGHT1*(2*8-2), MWIDTH2, MHEIGHT1);
        JRBpreparing.setBounds(MWIDTH3, 30+MHEIGHT1*(2*8-2), 100, MHEIGHT1);
        JRBdelivering.setBounds(MWIDTH3+100, 30+MHEIGHT1*(2*8-2), 100, MHEIGHT1);
        JRBdelivered.setBounds(MWIDTH3+200, 30+MHEIGHT1*(2*8-2), 100, MHEIGHT1);
        JLdatetime.setBounds(MWIDTH1, 30+MHEIGHT1*(2*9-2), MWIDTH2, MHEIGHT1);
        JTFdatetime.setBounds(MWIDTH3, 30+MHEIGHT1*(2*9-2), MWIDTH3, MHEIGHT1);
        JLcommodityid.setBounds(MWIDTH1, 30+MHEIGHT1*(2*10-2), MWIDTH2, MHEIGHT1);
        JSPdtl.setBounds(MWIDTH3, 30+MHEIGHT1*(2*10-2), MWIDTH3, MHEIGHT1*3);
        
        JBupdate = new JButton("Update");
//        System.out.println(30+MHEIGHT1*(2*12-2));
        JBupdate.setBounds(220, 30+MHEIGHT1*(2*12-2), 100, 30);
        JBupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectOrder == null) return;
                if(JRBpreparing.isSelected()) {
                    selectOrder.setStatus("preparing");
                } else if(JRBdelivering.isSelected()) {
                    selectOrder.setStatus("delivering");
                } else if(JRBdelivered.isSelected()) {
                    selectOrder.setStatus("delivered");
                }
                selectOrder.updateToDB();
                updateTable();
            }
        });
        
        JPdetail.add(JLorderid); JPdetail.add(JTForderid);
        JPdetail.add(JLcustomerid); JPdetail.add(JTFcustomerid);
        JPdetail.add(JLamount); JPdetail.add(JTFamount);
        JPdetail.add(JLphone); JPdetail.add(JTFphone);
        JPdetail.add(JLaddr); JPdetail.add(JTAaddr);
        JPdetail.add(JLstatus); JPdetail.add(JRBpreparing); JPdetail.add(JRBdelivering); JPdetail.add(JRBdelivered);
        JPdetail.add(JLdatetime); JPdetail.add(JTFdatetime);
        JPdetail.add(JLcommodityid); JPdetail.add(JSPdtl);
        JPdetail.add(JBupdate);
        
        JPdetail.setBounds(630, 30, 540, 780);
        JPdetail.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.add(JPdetail);
        
        JPdetail.setBackground(new Color(252,226,203));
        JTForderid.setBackground(new Color(249,203,168));
        JTFcustomerid.setBackground(new Color(249,203,168));
        JTFamount.setBackground(new Color(249,203,168));
        JTFphone.setBackground(new Color(249,203,168));
        JTAaddr.setBackground(new Color(249,203,168));
        JTFdatetime.setBackground(new Color(249,203,168));
        JSPdtl.getViewport().setBackground(new Color(249,203,168));
        JRBpreparing.setBackground(new Color(249,203,168));
        JRBdelivering.setBackground(new Color(249,203,168));
        JRBdelivered.setBackground(new Color(249,203,168));
        JBupdate.setBackground(new Color(241,156,67));
        JTdtl.setBackground(new Color(241,156,67));
    }
    
    private void Build() {
        this.setLayout(null);
        BuildJPlist();
        BuildJPdetail();
    }
    
    public BussinessOrderList(User u) {
        this.u = u;
        Build();
    }
    /*
    public static void main(String args[]) {
        JFrame JF = new JFrame("qwq");
        User u = new User();
        u.setUsername("beiyu");
        u.setPassword("beiyu");
        u.setUsertype(2);
        u.getFromDB();
        System.out.println(u.toString());
        JF.add(new BussinessOrderList(u));
        JF.setSize(1600, 900);
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF.setLocationRelativeTo(null);
        JF.setVisible(true);
    }*/

}
