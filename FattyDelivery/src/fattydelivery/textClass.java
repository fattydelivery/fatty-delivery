/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery;

import fattydelivery.lib.*;
/**
 *
 * @author Administrator
 */
public class textClass {
    public static void main(String[] args) {
        User u = new User();
        u.setUsername("jiachangcai");
        u.setPassword("88888888");
        u.setDob("1998-12-24");
        u.setAddr("People Street, Haikou, Hainan, China");
        u.setGender("male");
        u.setEmail("8888@qq.com");
        u.setPhone("19955668877");
        u.setRegTime();
        u.setUsertype(2);
        u.addToDB();
        
        u.setUsername("niupai");
        u.setPassword("88888888");
        u.setDob("1999-1-24");
        u.setAddr("Haidiandao, Haikou, Hainan, China");
        u.setGender("male");
        u.setEmail("6666@qq.com");
        u.setPhone("11223344567");
        u.setRegTime();
        u.setUsertype(2);
        u.addToDB();
        
        u.setUsername("pisa");
        u.setPassword("88888888");
        u.setDob("2000-1-24");
        u.setAddr("Renmentianqiao, Haikou, Hainan, China");
        u.setGender("female");
        u.setEmail("wwww6@qq.com");
        u.setPhone("11223344568");
        u.setRegTime();
        u.setUsertype(2);
        u.addToDB();
        
        u.setUsername("guke");
        u.setPassword("88888888");
        u.setDob("1998-11-24");
        u.setAddr("58 th People Street, Haikou, Hainan, China");
        u.setGender("female");
        u.setEmail("wwww@qq.com");
        u.setPhone("19955668977");
        u.setRegTime();
        u.setUsertype(1);
        u.addToDB();
        
        Commodity c = new Commodity();
        c.setImg("foodimg\\galizhupaifan.jpg");
        c.setBussinessId("jiachangcai");
        c.setId("galizhupaifan");
        c.setCost(12);
        c.addToDB();
        
        c.setImg("foodimg\\huangmenjimifan.jpg");
        c.setBussinessId("jiachangcai");
        c.setId("huangmenjimifan");
        c.setCost(15);
        c.addToDB();
        
        c.setImg("foodimg\\qiezibao.jpg");
        c.setBussinessId("jiachangcai");
        c.setId("qiezibao");
        c.setCost(12);
        c.addToDB();
        
        c.setImg("foodimg\\huoyanniupai.jpg");
        c.setBussinessId("niupai");
        c.setId("huoyanniupai");
        c.setCost(38);
        c.addToDB();
        
        c.setImg("foodimg\\xinaoerliangkaoroupisa.jpg");
        c.setBussinessId("pisa");
        c.setId("xinaoerliangkaoroupisa");
        c.setCost(22);
        c.addToDB();
    }
}
