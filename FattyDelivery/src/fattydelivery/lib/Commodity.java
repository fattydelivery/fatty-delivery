/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.lib;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Commodity {

    @Override
    public String toString() {
        return "Commodity{" + "id=" + id + ", cost=" + cost + ", bussinessid=" + bussinessid + '}';
    }
    private String id;
    private double cost;
    private String bussinessid;
    private File imgf;
    private InputStream imgs;
    private String imgext;
    
    public void setBussinessId(String a) {
        bussinessid = a;
    }
    public String getBussinessId() {
        return bussinessid;
    }
    
    public void setId(String a) {
        id = a;
    }
    public String getId() {
        return id;
    }
    
    public void setCost(double a) {
        cost = a;
    }
    public double getCost() {
        return cost;
    }
    public void setImg(InputStream imgs) {
        this.imgs = imgs;
    }
    public void setImg(String filepath) {
        try {
            imgf = new File(filepath);
            imgs = new FileInputStream(imgf);
            imgext = filepath.substring(filepath.lastIndexOf('.')+1);
            System.out.println(imgext);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Commodity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public InputStream getImg() {
        return imgs;
    }
    public void setImgext(String Imgext) {
        this.imgext = Imgext;
    }
    public String getImgext() {
        return imgext;
    }
    public void writeToLocal(String path) {
        FileOutputStream fos = null;
//        System.out.println(path);
        try {
//            System.out.println(path);
            fos = new FileOutputStream(path);
            byte[] b = new byte[1024];
            int length;
            while ((length = imgs.read(b)) > 0) {
                fos.write(b, 0, length);
            }
            imgs.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Commodity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Commodity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Commodity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private boolean check(Connection con) {
        boolean f = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql;
            sql = "select * from CommodityTable where id=?";
            stm=con.prepareStatement(sql);
            stm.setString(1, id);
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
        try {
            Connection con;
            con = DBConnect.connect();
            if(!check(con)) return 0;
            String sql = "insert into CommodityTable values(?, ?, ?, ?, ?)";
            PreparedStatement stm=con.prepareStatement(sql);
            stm.setString(1, id);
            stm.setDouble(2, cost);
            stm.setString(3, bussinessid);
            stm.setBinaryStream(4, imgs);
            stm.setString(5, imgext);
            stm.executeUpdate();
            DBConnect.disconnect(con, stm);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } return 1;
    }
    public void updateToDB(Connection con) {
        
    }
    
    public void deleteFromDB() {
        Connection con = DBConnect.connect();
        String sql = null;
        PreparedStatement stm = null;
        try {
            sql = "delete from CommodityTable where id = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Commodity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Commodity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void getFromDB() {
        Connection con = DBConnect.connect();
        String sql = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            sql = "select * from CommodityTable where id = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            for(;rs.next();) {
                this.setCost(rs.getDouble(2));
                this.setBussinessId(rs.getString(3));
                this.setImg(rs.getBinaryStream(4));
                this.setImgext(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Commodity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Commodity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
