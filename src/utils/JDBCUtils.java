package utils;
/**
 * @author 唐鹏
 * @version 创建时间:2021年1月7日 下午8:45:40
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

	private static Connection con;
	private static Statement state;
    private static ResultSet rs;
	
	private static void connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1","root","12345678");
			state = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败");
		}
	}
	public static int update(String sql) {
		connection();
		try {

			return state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql语句错误");
			return -1;
		}finally {
			close();
		}
	}
	public static ResultSet query(String sql) {
		connection();
		try {

			rs = state.executeQuery(sql);
			return  rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql语句错误");
			return null;
		}
	}
	public static void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(state != null)
				state.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
