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
 * @author ����
 * @version ����ʱ��:2021��1��8�� ����11:50:56
 */
public class UserMain {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
		System.out.println("��ѡ���ܣ�");
		System.out.println("1���û�ע�᣺");
		System.out.println("2���û���¼��");
		System.out.print("������ѡ��");
		String input = sc.next();
		switch (input) {
		case "1": 
			register();
			break;
		case "2":
			login();
			break;
		default:
			System.out.println("��������");
		}
	}
	private static void login() throws SQLException {
		// TODO Auto-generated method stub
		System.out.print("�������û�����");
		String username = sc.next();
		System.out.print("���������룺");
		String password = sc.next();
		String sql = "SELECT username,password FROM user_info where username = '"+username+"'";
		ResultSet rs1 = JDBCUtils.query(sql);
		if(rs1.next() && username.equals(rs1.getString(1))) {
			if(!(password.equals(rs1.getString(2)))) {
				System.out.println("�����������");
				return;
			}
				
		}else {
			System.out.println("�û�������");
			return;
		}
		ResultSet rs = new UserService().login(new User(username,password));
		if(rs.next()) {
			String name = rs.getString(1);
			System.out.println("��ӭ"+name+"��"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"��¼��ϵͳ��");
		}else {
			System.out.println("�û������������");
		}
	}
	private static void register() throws SQLException {
		// TODO Auto-generated method stub
		System.out.print("�������û�����");
		String username = sc.next();
		String sql = "SELECT username FROM user_info WHERE username = '"+username+"'";
		ResultSet rs = JDBCUtils.query(sql);
		if(rs.next()) {
			System.out.println("���û��Ѿ���ע�ᣡ");
			JDBCUtils.close();
			return;
		}
		System.out.print("���������룺");
		String password = sc.next();
		int a = new UserService().regist(new User(username,password));
		JDBCUtils.close();
		if(a == 1) {
			System.out.println("ע��ɹ���");
		}
	}
}
