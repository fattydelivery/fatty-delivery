/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.lib;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class User {

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", gender=" + gender + ", phone=" + phone + ", email=" + email + ", dob=" + dob + ", addr=" + addr + ", regdate=" + regdate + ", regtime=" + regtime + ", usertype=" + usertype + '}';
    }
    private String username;
    private String password;
    private String gender;
    private String phone;
    private String email;
    private java.util.Date dob;
    private String addr;
    private java.util.Date regdate;
    private Timestamp regtime;
    private int usertype;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender.toLowerCase();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(String a) {
        Date s = null;
        try {
            s = new SimpleDateFormat("yyyy-MM-dd").parse(a);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dob = s;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public void setRegTime() {
        regdate = new java.util.Date();
        regtime = new Timestamp(regdate.getTime());
    }

    public Timestamp getRegTime() {
        return regtime;
    }

    public void setUsername(String a) {
        username = a;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String a) {
        password = a;
    }

    public String getPassword() {
        return password;
    }

    private boolean check(Connection con) {
        boolean f = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql;
            f = false;
            if (usertype == 1) {
                sql = "select * from CustomerTable where username=?";
            } else {
                sql = "select * from BussinessTable where username=?";
            }
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                f = false;
            } else {
                f = true;
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

    public boolean checkPassword() {
        boolean f = false;
        Connection con = DBConnect.connect();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql;
            f = false;
            if (usertype == 1) {
                sql = "select * from CustomerTable where username=? and password=?";
            } else {
                sql = "select * from BussinessTable where username=? and password=?";
            }
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                f = true;
            } else {
                f = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return f;
    }

    public boolean checkUesrname() {
        Connection con = DBConnect.connect();
        boolean f = !check(con);
        DBConnect.disconnect(con);
        return f;
    }

    public int addToDB() {
        try {
            Connection con;
            con = DBConnect.connect();
            if (!check(con)) {
                return 0;
            }
            String sql;
            if (usertype == 1) {
                sql = "insert into CustomerTable values(?, ?, ?, ?, ?, ?, ?, ?);";
            } else {
                sql = "insert into BussinessTable values(?, ?, ?, ?, ?, ?, ?, ?);";
            }
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, gender);
            stm.setString(4, phone);
            stm.setString(5, email);
            if (dob != null) {
                stm.setDate(6, new java.sql.Date(dob.getTime()));
            } else {
                stm.setDate(6, null);
            }
            stm.setString(7, addr);
            stm.setTimestamp(8, regtime);

            stm.executeUpdate();

            DBConnect.disconnect(con, stm);
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public void updateToDB() {
        Connection con = null;
        con = DBConnect.connect();
        String sql = null;
        if (usertype == 1) {
            sql = "update CustomerTable set password=?, gender=?, phone=?, email=?, dob=?, addr=? where username=?";
        } else {
            sql = "update BussinessTable set password=?, gender=?, phone=?, email=?, dob=?, addr=? where username=?";
        }
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, gender);
            stm.setString(3, phone);
            stm.setString(4, email);
            if (dob != null) {
                stm.setDate(5, new java.sql.Date(dob.getTime()));
            } else {
                stm.setDate(5, null);
            }
            stm.setString(6, addr);
            stm.setString(7, username);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getFromDB() {
        Connection con = DBConnect.connect();
        String sql = null;
        if (usertype == 1) {
            sql = "select * from CustomerTable where username=?";
        } else {
            sql = "select * from BussinessTable where username=?";
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
//            System.out.println(stm);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                password = rs.getString(2);
                gender = rs.getString(3);
                phone = rs.getString(4);
                email = rs.getString(5);
                dob = new java.util.Date(rs.getDate(6).getTime());
                addr = rs.getString(7);
                regtime = rs.getTimestamp(8);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Order[] getAllOrder() {
        Connection con = DBConnect.connect();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = null;
        Vector vec = new Vector<Order>();
        Order[] o = null;
        try {
            if (usertype == 1) {
                sql = "select * from OrderTable where customerid=?";
            } else {
                sql = "select * from OrderTable where bussinessid=?";
            }
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            for (; rs.next();) {
                Order tmp = new Order();
                tmp.setAddress(rs.getString(1));
                tmp.setAmount(rs.getDouble(2));
                tmp.setBussinessId(rs.getString(3));
                tmp.setCustomId(rs.getString(4));
                tmp.setCommodityId(rs.getString(5));
                tmp.setTime(new Timestamp(rs.getTimestamp(6).getTime()));
                tmp.setStatus(rs.getString(7));
                tmp.setPhone(rs.getString(8));
                tmp.setOrderid(rs.getLong(9));
                vec.add(tmp);
            }
            o = new Order[vec.size()];
            for (int i = 0; i < vec.size(); i++) {
                o[i] = (Order) vec.get(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return o;
    }

    public Commodity[] getAllCommodity() {
        PreparedStatement stm = null;
        String sql = null;
        Connection con = null;
        ResultSet rs = null;
        Commodity[] com = null;

        if (this.usertype == 2) {
            try {
                con = DBConnect.connect();
                sql = "select * from CommodityTable where bussinessid = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                Vector vec = new Vector();
                for (; rs.next();) {
                    Commodity c = new Commodity();
                    c.setId(rs.getString(1));
                    c.setCost(rs.getDouble(2));
                    c.setBussinessId(rs.getString(3));
                    c.setImg(rs.getBinaryStream(4));
                    c.setImgext(rs.getString(5));
                    vec.add(c);
                }
                com = new Commodity[vec.size()];
                for (int i = 0; i < vec.size(); i++) {
                    com[i] = (Commodity) vec.get(i);
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    rs.close();
                    stm.close();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                con = DBConnect.connect();
                sql = "select * from CommodityTable";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                Vector vec = new Vector();
                for (; rs.next();) {
                    Commodity c = new Commodity();
                    c.setId(rs.getString(1));
                    c.setCost(rs.getDouble(2));
                    c.setBussinessId(rs.getString(3));
                    c.setImg(rs.getBinaryStream(4));
                    c.setImgext(rs.getString(5));
                    vec.add(c);
                }
                com = new Commodity[vec.size()];
                for (int i = 0; i < vec.size(); i++) {
                    com[i] = (Commodity) vec.get(i);
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    rs.close();
                    stm.close();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return com;
    }

    public static Commodity[] getAllCommodities() {
        PreparedStatement stm = null;
        String sql = null;
        Connection con = null;
        ResultSet rs = null;
        Commodity[] com = null;

        try {
            con = DBConnect.connect();
            sql = "select * from CommodityTable";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            Vector vec = new Vector();
            for (; rs.next();) {
                Commodity c = new Commodity();
                c.setId(rs.getString(1));
                c.setCost(rs.getDouble(2));
                c.setBussinessId(rs.getString(3));
                c.setImg(rs.getBinaryStream(4));
                c.setImgext(rs.getString(5));
                vec.add(c);
            }
            com = new Commodity[vec.size()];
            for (int i = 0; i < vec.size(); i++) {
                com[i] = (Commodity) vec.get(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return com;
    }
}
