package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entity.User;
import service.UserService;
import utils.JDBCUtils;


/**
 * @author 唐鹏
 * @version 创建时间:2021年1月8日 上午11:50:56
 */
public class UserMain {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
		System.out.println("请选择功能：");
		System.out.println("1新用户注册：");
		System.out.println("2老用户登录：");
		System.out.print("请输入选择：");
		String input = sc.next();
		switch (input) {
		case "1": 
			register();
			break;
		case "2":
			login();
			break;
		default:
			System.out.println("输入有误！");
		}
	}
	private static void login() throws SQLException {
		// TODO Auto-generated method stub
		System.out.print("请输入用户名：");
		String username = sc.next();
		System.out.print("请输入密码：");
		String password = sc.next();
		String sql = "SELECT username,password FROM user_info where username = '"+username+"'";
		ResultSet rs1 = JDBCUtils.query(sql);
		if(rs1.next() && username.equals(rs1.getString(1))) {
			if(!(password.equals(rs1.getString(2)))) {
				System.out.println("密码输入错误！");
				return;
			}
				
		}else {
			System.out.println("用户名错误！");
			return;
		}
		ResultSet rs = new UserService().login(new User(username,password));
		if(rs.next()) {
			String name = rs.getString(1);
			System.out.println("欢迎"+name+"于"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"登录本系统！");
		}else {
			System.out.println("用户名或密码错误！");
		}
	}
	private static void register() throws SQLException {
		// TODO Auto-generated method stub
		System.out.print("请输入用户名：");
		String username = sc.next();
		String sql = "SELECT username FROM user_info WHERE username = '"+username+"'";
		ResultSet rs = JDBCUtils.query(sql);
		if(rs.next()) {
			System.out.println("该用户已经被注册！");
			JDBCUtils.close();
			return;
		}
		System.out.print("请输入密码：");
		String password = sc.next();
		int a = new UserService().regist(new User(username,password));
		JDBCUtils.close();
		if(a == 1) {
			System.out.println("注册成功！");
		}
	}
}
