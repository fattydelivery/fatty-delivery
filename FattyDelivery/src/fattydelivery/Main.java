/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery;

import fattydelivery.lib.*;
import fattydelivery.log.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        new LoginFrame();
//        try {
//
//            // 添加客户或商户
//            User u = new User();
//            u.setUsername("beiyu");
//            u.setPassword("beiyu");
//            u.setRegTime();
//            u.setPhone("15666676912");
//            u.setAddr("Haikou, Haihan");
//            u.setDob("2018-05-04"); // 可以为空
//            u.setEmail("729320011@qq.com"); // 可以为空
//            u.setGender("Male"); // 可以为空
//            u.setUsertype(2);// 1为用户， 2为商户
//            if(u.addToDB()==0) { // 判断用户名是否被注册过并添加到数据库中
//                System.out.println("username has exist.");
//            } else {
//                System.out.println("Success"); // 添加成功
//            }
///*            
//            // 添加商品
//            Commodity c = new Commodity();
//            c.setBussinessId("beiyu");
//            c.setCost(23.55);
//            c.setId("food1");
//            if(c.addToDB()==0) { // 判断id是否冲突并添加到数据库中
//                System.out.println("id has exist.");
//            } else {
//                System.out.println("Success"); // 添加成功
//            }
//*/            
//            
//            // 添加订单
//            Order o = new Order(); // orderid自动生成 不必set setTime()已经调用过也可以不必调用
//            o.setAddress(u.getAddr());
//            o.setAmount(23.55*5);
//            o.setBussinessId(u.getUsername());
//            o.setCustomId(u.getUsername());
//            o.setPhone(u.getPhone());
//            // 一个商品时
//            o.setCommodityId("food1, food1, food1");
//            // 多个商品时
////            o.setCommodityId("food1，food2");
//            //或者
////            String[] s= new String[5];
////            s[0]="food1"; s[1]="food1"; s[2]="food1"; s[3]="food1"; s[4]="food1";
////            o.setCommodityId(s);
//            o.setStatus("Preparing"); // 准备中
//            o.setStatus("Delivering"); // 递送中
//            o.setStatus("Delivered"); // 已送达
//            o.addToDB();
//            
//        } catch (Exception ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
