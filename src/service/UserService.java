package service;

import java.sql.ResultSet;

import entity.User;
import utils.JDBCUtils;


/**
* @author ����
* @version ����ʱ��:2021��1��8�� ����11:21:11
*/
public class UserService {
	//ע��
	public  int regist(User user) {
		String sql = "INSERT INTO user_info VALUES(0,'"+user.getUsername()+"','"+user.getPassword()+"',NOW())";
		return JDBCUtils.update(sql);
	}
	//��¼
	public ResultSet login(User user) {
		String sql = "SELECT username,password,registTime From user_info where username='"+user.getUsername()+"' AND password ='"+user.getPassword()+"'";
		return JDBCUtils.query(sql);
	}
}
