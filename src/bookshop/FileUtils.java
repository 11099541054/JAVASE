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
 * �ļ���ȡ������
 */
public class FileUtils {
	private static BufferedReader br = null;
	private static BufferedWriter bw = null;
	/*
	 * ���ļ��ж�ȡ�鼮�б���Ϣ
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
			System.err.println("�ļ�·������");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.err.println("�ļ��ж�ȡ�����ָ�ʽ������");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.err.println("���뷽ʽ��ͬ");
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
	 * ���ļ��ж�ȡ�û��б���Ϣ
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
			System.err.println("�ļ�·������");
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
	 * ���鼮�б���Ϣд��ָ���ļ�
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
	 * �û�ע��
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
