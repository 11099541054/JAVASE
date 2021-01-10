package service;

import java.sql.ResultSet;

import entity.User;
import utils.JDBCUtils;


/**
* @author 唐鹏
* @version 创建时间:2021年1月8日 上午11:21:11
*/
public class UserService {
	//注册
	public  int regist(User user) {
		String sql = "INSERT INTO user_info VALUES(0,'"+user.getUsername()+"','"+user.getPassword()+"',NOW())";
		return JDBCUtils.update(sql);
	}
	//登录
	public ResultSet login(User user) {
		String sql = "SELECT username,password,registTime From user_info where username='"+user.getUsername()+"' AND password ='"+user.getPassword()+"'";
		return JDBCUtils.query(sql);
	}
}
