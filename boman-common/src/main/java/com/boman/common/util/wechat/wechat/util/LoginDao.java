package com.boman.common.util.wechat.wechat.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {
    private static LoginDao instance = null;

    public static LoginDao getInstance() {
        if (instance == null) {
            instance = new LoginDao();
        }
        return instance;
    }
//验证唯一绑定微信
    public  static Integer weixin(String id) {
    	ResultSet rs=null;
    	Statement stmt=null;
    	Connection conn = null;
    	Integer counts=0;
        try {
            conn = MySQLDBCon.getCon(); // 建立数据库连接
         // 获取Statement
         			 stmt = conn.createStatement();
         			
         			String sql = "SELECT count(1) as counts  FROM weixin WHERE  userid= '" + id+"'";
         			// 执行查询
         			 rs = stmt.executeQuery(sql);
         			 
         			while(rs.next()){
         				counts= rs.getInt("counts");
         			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	rs.close();
            	stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return counts;
    }
    
    
    
    // 保存用户注册信息
    public  boolean saveUser(String op,String user ) {
        Connection conn = null;
        PreparedStatement pstmt=null;
        try {

        	conn = MySQLDBCon.getCon(); // 建立数据库连接
            String sql = "insert  into  weixin(openid,userid)  value(?,?)"; // insert
            pstmt = conn.prepareStatement(sql); // 创建用户操作执行SQL语句的PreparedStatement对象
            pstmt.setString(1, op);
            pstmt.setString(2, user);
            pstmt.executeUpdate(); // 编译执行insert语句
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    //找到登录信息
    public  static ArrayList<String>  findLoginData(String openid) {
    	ArrayList<String>  list=new ArrayList<String>();
       	Connection conn = null;
    	Statement stmt=null;
       	ResultSet rs=null;
        try {
            conn = MySQLDBCon.getCon(); // 建立数据库连接
         // 获取Statement
         			stmt = conn.createStatement();
         			// 添加图书信息的SQL语句
         			String sql = "SELECT DISTINCT account,`password` FROM `user` u LEFT JOIN weixin w ON w.userid=u.id  WHERE w.openid='" + openid +"'";
         			// 执行查询
         			rs = stmt.executeQuery(sql);
         			while(rs.next()){
         				String account=rs.getString("account");   
        				String password=rs.getString("password");
        				list.add(account);
        				list.add(password);
         			}
         			
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	stmt.close();
            	rs.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public  static boolean saveUser1(String op) {
    	Connection conn = null;
        try {
            conn = MySQLDBCon.getCon(); // 建立数据库连接
         // 获取Statement
         			Statement stmt = conn.createStatement();
         			// 添加图书信息的SQL语句
         			String sql = "select * from weixin where openid ='" + op +"'";
         			// 执行查询
         			ResultSet rs = stmt.executeQuery(sql);
         			while(rs.next()){
         				rs.close(); // 关闭ResultSet
            			stmt.close(); // 关闭Statement
            			conn.close(); // 关闭Connection
         				return false;
         			}
         			
         			rs.close(); // 关闭ResultSet
        			stmt.close(); // 关闭Statement
        			conn.close(); // 关闭Connection
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return true;
    }
    public  static ArrayList<String> findDataByUserid(String userid) {
    	
    	ArrayList<String>  list=new ArrayList<String>();
       	Connection conn = null;
    	Statement stmt=null;
       	ResultSet rs=null;
           try {
               conn = MySQLDBCon.getCon(); // 建立数据库连接
            // 获取Statement
            			stmt = conn.createStatement();
            			
            			String sql = "select u.id,u.unit_id,u.name,u.is_out ,o.insproject from user u LEFT JOIN outwork o on o.kquser=u.id where  u.id='"+userid+"'" ;
            			// 执行查询
            			rs = stmt.executeQuery(sql);
            			while(rs.next()){
            				
            				String id=rs.getString(1);
            				String unitid=rs.getString(2);
            				String name=rs.getString(3);
            				String isOut=rs.getString(4);   
            				String insproject=rs.getString(5);
            				list.add(id);
            				list.add(unitid);
            				list.add(name);
            				list.add(isOut);
            				list.add(insproject);
            			}
            			      		
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               try {
            	   stmt.close();
            	   rs.close();
                   conn.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
          return list;
       }
public  static String findUser(String openid) {
	String userid="";
    	Connection conn = null;
    	ResultSet rs=null;
    	Statement stmt=null;
        try {
            conn = MySQLDBCon.getCon(); // 建立数据库连接
         // 获取Statement
            	stmt= conn.createStatement();
         			
         			String sql = "select userid from weixin where  openid= '" + openid+"'";
         			// 执行查询
         			 rs = stmt.executeQuery(sql);
         			
         			while(rs.next()){
         				userid=rs.getString("userid");
         			}
         			
         			      		
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	stmt.close();
            	rs.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return userid;
    }
    
    public  static String kq(String account) {
    	ResultSet rs=null;
    	Statement stmt=null;
    	Connection conn = null;
        try {
            conn = MySQLDBCon.getCon(); // 建立数据库连接
         // 获取Statement
         			 stmt = conn.createStatement();
         			
         			String sql = "select salt from user where  account= '" + account+"'";
         			// 执行查询
         			 rs = stmt.executeQuery(sql);
         			while(rs.next()){
         				return rs.getString("salt");
         			}
         			
         			      		
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	rs.close();
            	stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return null;
    }
    
    
 public  static List<String> dl(String account,String password) {
    	
	 List<String> list=new ArrayList<String>();
    	Connection conn = null;
    	Statement stmt=null;
    	ResultSet rs=null;
        try {
            conn = MySQLDBCon.getCon(); // 建立数据库连接
         // 获取Statement
         			 stmt = conn.createStatement();
         			
         			String sql = "	select u.id,u.unit_id,u.name,u.is_out from user u  where u.status!=0 and  u.account= '" + account+"' and u.password ='"+ password + "'";
         			// 执行查询
         			rs = stmt.executeQuery(sql);
         			while(rs.next()){
         				
         				String id=rs.getString(1);
         				String unitid=rs.getString(2);
         				String name=rs.getString(3);
         				String isOut=rs.getString(4);  
         				list.add(id);
         				list.add(unitid);
         				list.add(name);
         				list.add(isOut);
         			}
         			      		
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	stmt.close();
            	rs.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return list;
    }
    }
