/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.bussiness;

import fattydelivery.lib.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Administrator
 */
public class BussinessCommodityList extends JPanel implements ActionListener {

    private User u;
    private JButton JBadd, JBdelete, JBreflash;
    private JTable JTlist;
    private final static String[] ColName = {"id", "cost", "bussinessid", "img"};
    private String[][] Content;
    private DefaultTableModel dtm;

    class ColorTableCellRenderer extends DefaultTableCellRenderer {

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (column == 3) {
                ImageIcon imageIcon = new ImageIcon(value.toString());
                Image image = imageIcon.getImage();
                Image scaledInstance = image.getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
                imageIcon.setImage(scaledInstance);
                return new JLabel(imageIcon);
            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }
    }

    private void Build() {
        setLayout(null);
        setBackground(new Color(249, 203, 168));
        JBadd = new JButton("add commodity");
        JBdelete = new JButton("delete commodity");
        JBreflash = new JButton("Reflash");
        JTlist = new JTable();

        JBadd.setBounds(300, 750, 200, 40);
        JBdelete.setBounds(700, 750, 200, 40);
        JBreflash.setBounds(400, 700, 400, 40);
        JBadd.addActionListener(this);
        JBdelete.addActionListener(this);
        JBreflash.addActionListener(this);

        updateModel();
        ColorTableCellRenderer r = new ColorTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        JTlist.setDefaultRenderer(Object.class, r);
        JTlist.getTableHeader().setReorderingAllowed(false);
        JTlist.getTableHeader().setResizingAllowed(false);
        JTlist.setModel(dtm);
        JTlist.setFont(PJFont.ft);
        JTlist.getTableHeader().setFont(PJFont.ft);
        JTlist.setRowHeight(100);
        JTlist.setBackground(new Color(187, 218, 236));
        JTlist.getTableHeader().setBackground(new Color(189, 203, 230));
        JTlist.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        JTlist.setBounds(100, 150, 1000, 500);
        JTlist.getTableHeader().setBounds(100, 100, 1000, 50);
        JScrollPane JSP = new JScrollPane(JTlist);
        JSP.setBounds(100, 150, 1000, 500);
        JSP.getViewport().setBackground(new Color(217, 239, 250));
        JSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JTlist.setBackground(new Color(249, 203, 168));
        JTlist.getTableHeader().setBackground(new Color(246, 177, 108));//////
        setBackground(new Color(252, 226, 203));
        JSP.getViewport().setBackground(new Color(252, 226, 203));
        add(JBadd);
        add(JBdelete);
        add(JBreflash);
        add(JTlist.getTableHeader()); // add(JTlist);
        add(JSP);
        JBadd.setBackground(new Color(241, 156, 67));
        JBdelete.setBackground(new Color(241, 156, 67));
        JBreflash.setBackground(new Color(241, 156, 67));
    }

    private void updateModel() {
        Commodity[] c = u.getAllCommodity();
        Content = new String[c.length][4];
        for (int i = 0; i < c.length; i++) {
            Content[i][0] = c[i].getId();
            Content[i][1] = Double.toString(c[i].getCost());
            Content[i][2] = c[i].getBussinessId();
            String filePath = null;
            File ff = new File("img\\" + c[i].getBussinessId());
            if (!ff.exists()) {
                ff.mkdirs();
            }
//        filePath = "img\\" + c.getId() + "." + c.getImgext();
            filePath = "img\\" + c[i].getBussinessId() + "\\" + c[i].getId() + "." + c[i].getImgext();
            c[i].writeToLocal(filePath);
            Content[i][3] = filePath;
        }
        dtm = new DefaultTableModel(Content, ColName);
    }

    public BussinessCommodityList(User u) {
        this.u = u;
        Build();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == JBadd) {
            new AddCommodityFrame(u);
        } else if (e.getSource() == JBdelete) {
            int selectedRow = JTlist.getSelectedRow();
            if (selectedRow != -1) {
                Commodity com = new Commodity();
                com.setId((String) JTlist.getValueAt(selectedRow, 0));
                com.setCost(Double.parseDouble((String) JTlist.getValueAt(selectedRow, 1)));
                com.setBussinessId((String) JTlist.getValueAt(selectedRow, 2));
                com.deleteFromDB();
                JOptionPane.showMessageDialog(this, "Delete successfully!");
                updateModel();
                JTlist.setModel(dtm);
//                System.out.println(com.toString());
            }
        } else if (e.getSource() == JBreflash) {
            updateModel();
            JTlist.setModel(dtm);
        }
    }
}
