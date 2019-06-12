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
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class CustomerOrderList extends JPanel {

    private User u;
    private Order[] o;
    private JTable JTlist;
    private JScrollPane JSP;
    private static String[] ColName = {"commodities", "amount", "status"};
    private String[][] Content;
    private DefaultTableModel dtm;
    private JButton JBreflash;

    private void updateTable() {
        o = null;
        dtm = new DefaultTableModel(null, ColName);
        JTlist.setModel(dtm);
        o = u.getAllOrder();
        if (o.length == 0) {
            return;
        }
        Content = new String[o.length][3];
        for (int i = 0; i < o.length; i++) {
            Content[i][0] = o[i].getCommodityId();
            Content[i][1] = String.valueOf(o[i].getAmount());
            Content[i][2] = o[i].getStatus();
        }
        dtm = new DefaultTableModel(Content, ColName);
        JTlist.setModel(dtm);
    }

    private void Build() {
        this.setLayout(null);
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
        JTlist.getTableHeader().setBounds(30, 30, 440, 30);
//        JTlist.setBounds(30, 60, 540, 600);
        JSP = new JScrollPane(JTlist);
        JSP.setBounds(30, 60, 440, 600);
        JSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        JPlist.add(JTlist);
        add(JSP);
//        JPlist.add(JTlist.getTableHeader());


        JBreflash = new JButton("Reflash");
        JBreflash.setBounds(200, 700, 100, 30);
        JBreflash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
        this.add(JBreflash);
        
        setBounds(0, 0, 500, 900);
        
        
        
        JTlist.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    int r = JTlist.getSelectedRow();
                    if (r != -1) {
//                        System.out.println(o[r].toString());
                        new OrderDetailFrame(o[r]);
                    }
                }
            }
        });
   
    JTlist.setBackground(new Color(249,203,168));
        JTlist.getTableHeader().setBackground(new Color(246,177,108));
        JSP.getViewport().setBackground(new Color(252,226,203));
        setBackground(new Color(252,226,203));
        //JPlist.setBackground(new Color(252,226,203));
        JBreflash.setBackground(new Color(241,156,67));
    }

    public CustomerOrderList(User u) {
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
        JF.add(new CustomerOrderList(u));
        JF.setSize(800, 900);
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF.setLocationRelativeTo(null);
        JF.setVisible(true);
    }*/
}
