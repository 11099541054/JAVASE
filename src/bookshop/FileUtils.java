package bookshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



/*
 * 文件读取工具类
 */
public class FileUtils {
	private static BufferedReader br = null;
	private static BufferedWriter bw = null;
	/*
	 * 从文件中读取书籍列表信息
	 */
	public static List<Book> loadBooks(String file) {
		List<Book> list = new ArrayList<Book>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String str ="";
			while((str = br.readLine()) != null) {
				String[] info = str.split(",");
				list.add(new Book(Integer.parseInt(info[0]),info[1],
						Double.parseDouble(info[2]),Integer.parseInt(info[3])));
			} 
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.err.println("文件路径错误");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.err.println("文件中读取的数字格式有问题");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.err.println("编码方式不同");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}
		return list;
	}
	/*
	 * 从文件中读取用户列表信息
	 */
	public static List<User> loadUsers(String file) {
		List<User> list = new ArrayList<User>();
		try {
			br = new BufferedReader(new FileReader(file)) ;
			String str ="";
			while((str = br.readLine()) != null) {
				String[] info = str.split(",");
				list.add(new User(info[0],info[1]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("文件路径错误");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);;
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}
		return list;
	}
	/*
	 * 把书籍列表信息写入指定文件
	 */
	public static void addBooks(List<Book> books, String file )  {
		String bookInfo = "";
		try {
			bw = new BufferedWriter(new FileWriter(file));
			for(Book book:books) {
				bookInfo = book.getBookId()+","+book.getBookName()+","+book.getPrice()+","+book.getStorage();
				bw.write(bookInfo);
				bw.newLine();
			}
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/*
	 * 用户注册
	 */
	public static void addUsers(List<User> users,String file) {
		String userInfo = "";
		try {
			bw = new BufferedWriter(new FileWriter(file));
			for(User user:users) {
				userInfo = user.getUserName()+","+user.getPassword();
				bw.write(userInfo);
				bw.newLine();
			}
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
