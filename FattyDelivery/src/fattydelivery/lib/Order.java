/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.lib;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Order {

    @Override
    public String toString() {
        return "Order{" + "addr=" + addr + ", amount=" + amount + ", bussinessid=" + bussinessid + ", customerid=" + customerid + ", commodityid=" + commodityid + ", date=" + date + ", status=" + status + ", time=" + time + ", phone=" + phone + ", orderid=" + orderid + '}';
    }
    private String addr;
    private double amount;
    private String bussinessid;
    private String customerid;
    private String commodityid;
    private java.util.Date date;
    private String status;
    private Timestamp time;
    private String phone;
    private long orderid;

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }
    public void setOrderid() {
        setTime();
        SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmss");
        Random r = new Random();
        int x = r.nextInt(9999);
        String str = String.format("%04d", x);
        orderid = Long.parseLong(f.format(time)+str);
    }
    
    public Order() {
        setTime();
        SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmss");
        Random r = new Random();
        int x = r.nextInt(9999);
        String str = String.format("%04d", x);
        orderid = Long.parseLong(f.format(time)+str);
    }
    public Order(int id) {
        orderid = id;
    }
    
    public void setAddress(String a) {
        addr = a;
    }
    public String getAddress() {
        return addr;
    }
    
    public void setAmount(double a) {
        amount = a;
    }
    public double getAmount() {
        return amount;
    }
    
    public void setBussinessId(String a) {
        bussinessid = a;
    }
    public String getBussinessId() {
        return bussinessid;
    }
    
    public void setCommodityId(String[] a) {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<a.length; i++) {
//            System.out.println(a[i]);
            if(i==a.length-1) sb.append(a[i]);
            else sb.append(a[i]+",");
        }
        commodityid = new String(sb.toString());
//        System.out.println(commodityid);
    }
    public void setCommodityId(String a) {
        commodityid = new String(a);
    }
    public String getCommodityId() {
        return commodityid;
    }
    public String[] getCommodityIds() {
        String[] a = commodityid.split(",");
        return a;
    }
    
    public void setCustomId(String a) {
        customerid = a;
    }
    public String getCustomId() {
        return customerid;
    }
    
    public void setPhone(String a) {
        phone = a;
    }
    public String getPhone() {
        return phone;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setTime(Timestamp t) {
        this.time = t;
    }
    
    public void setTime() {
        date = new java.util.Date();
        time = new Timestamp(date.getTime());
    }
    public Timestamp getTime() {
        return time;
    }
    
    private boolean check(Connection con) {
        boolean f = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql;
            f = false;
            sql = "select * from OrderTable where orderid=?";
            stm=con.prepareStatement(sql);
            stm.setLong(1, orderid);
            rs = stm.executeQuery();
            if(rs.next()) {
                f=false;
            } else {
                f=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return f;
    }
    
    public int addToDB() {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnect.connect();
            while(!check(con)) setOrderid();
            String sql;
            sql = "insert into OrderTable values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, addr);
            stm.setDouble(2, amount);
            stm.setString(3, bussinessid);
            stm.setString(4, customerid);
            stm.setString(5, commodityid);
            stm.setTimestamp(6, time);
            stm.setString(7, status);
            stm.setString(8, phone);
            stm.setLong(9, orderid);
            
            stm.executeUpdate();
            
            DBConnect.disconnect(con, stm);
                    
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return 1;
    }
    public void deleteFromDB() {
        Connection con = DBConnect.connect();
        PreparedStatement stm = null;
        try {
            String sql;
            sql = "delete from OrderTable where orderid = ?";
            stm = con.prepareStatement(sql);
            System.out.println(getOrderid());
            stm.setString(1, String.valueOf(this.getOrderid()));
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void updateToDB() {
        Connection con = DBConnect.connect();
        PreparedStatement stm = null;
        try {
            String sql;
            sql = "update OrderTable set addr=?, amount=?, bussinessid=?, customerid=?, commodityid=?, datetime=?,"
                    + " status=?, phone=? where orderid=?";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, addr);
            stm.setDouble(2, amount);
            stm.setString(3, bussinessid);
            stm.setString(4, customerid);
            stm.setString(5, commodityid);
            stm.setTimestamp(6, time);
            stm.setString(7, status);
            stm.setString(8, phone);
            stm.setLong(9, orderid);
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
