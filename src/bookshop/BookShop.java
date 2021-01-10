package bookshop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public class BookShop {
	private List<Book> books = null;
	private List<User> users =  null;
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	Order order = new Order();
	/*
	 * ��¼��֤
	 */
	public boolean login(String username,String password) {
		User user = new User(username,password);
		return users.contains(user);
	}
	public BookShop(){
		books = FileUtils.loadBooks(System.getProperty("user.dir")+"/src/books.txt");
		users = FileUtils.loadUsers(System.getProperty("user.dir")+"/src/users.txt");
	}
	/*
	 * ��ʾͼ���б�
	 */
	public void showBoooks() {
		System.out.println("\tͼ���б�");
		System.out.println("ͼ����\t"+"ͼ������\t"+"ͼ�鵥��\t"+"�������");
		System.out.println("----------------------------------------");
		for(Book book: books) {
			if(book.getStorage()!=0) {
				System.out.println(book.getBookId()+"\t"+book.getBookName()+"\t"+
						book.getPrice()+"\t"+book.getStorage());
			}
		}
		System.out.println("----------------------------------------");
	} 
	/**
	 * �����鼮
	 * @return
	 */
	public Order buyInfo() {

		int no= 0;//ͼ���������
		int count =0;//����Ĺ�������
		order.setOrderId(System.currentTimeMillis()+"");
		order.setDate(new Date());
		System.out.println("������ͼ����ѡ��ͼ�飺");
		try{
			//Scanner sc = new Scanner(System.in);
			no = ScannerUtils.nextInt();	
		}catch(InputMismatchException e) {
			System.out.println("����Ƿ�!");
			return null;
		}
		Book book = null;
		for(Book temp : books) {
			if(temp.getBookId()==no ) {
				temp.setStorage(temp.getStorage()-count);
				book = temp;
				break;
			}
		}
		if(book == null) {
			System.out.println("��������鼮��Ų����ڣ�");
			return null;
		}
		System.out.println("�����빺��������");
		try {
			count = ScannerUtils.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("����Ƿ���");
			return null;
		}
		if(count<0) {
			System.out.println("������鼮��������С���㣡");
			return null;
		}
		if(book.getStorage()<count) {
			System.out.println("��治�㣡");
			return null;
		}
		//��װ������
		OrderItem orderItem = new OrderItem(book.getBookName(),book.getPrice(),count);
		int index = orderItems.indexOf(orderItem);//���Ҷ����������е�λ��
		if(index != -1) {
			orderItems.get(index).setNum(orderItems.get(index).getNum()+count);
		}else {
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		book.setStorage(book.getStorage() - count);
		FileUtils.addBooks(books, System.getProperty("user.dir")+"/src/books.txt");
		return order;
	}
	/*
	 * ��ʾ������Ϣ
	 */
	public void orderInfo(Order order) {
		double total = 0.0;
		if(order != null) {
			System.out.println("\tͼ�鶩��");
			System.out.println("ͼ������\t"+"��������\t"+"ͼ�鵥��\t");
			System.out.println("----------------------------------------");
			for(OrderItem orderItem : order.getOrderItems()) {
				if(orderItem != null) {
					total+=orderItem.getNum()*orderItem.getPrice();
					System.out.println(orderItem.getBooksName()+"\t"+
							orderItem.getNum()+"\t"+orderItem.getPrice());
				}
			}
			System.out.println("�����ܶ"+String.format("%.2f", total)+"Ԫ��");
			System.out.println("���ڣ�"+new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(order.getDate()));
		}else {
			System.out.println("�˳������ܡ�");
		}
	}
	/*
	 * ���ͼ��
	 */
	public void addBooks() throws Exception{
		String input = "";
		do {
			int bookId = 0;
			while(true) {
				System.out.println("������ͼ���ţ�");
				bookId = ScannerUtils.nextInt();
				if(inputValidation(bookId)) {
					break;
				}
			}
			Book book = new Book(bookId);
			if(books.contains(book)) {
				int bookNum = 0;
				while(true) {
					System.out.println("������������");
					bookNum = ScannerUtils.nextInt();
					if(inputValidation(bookNum)) {
						break;
					}
				}
				//����List�е�ǰbook���������������з����±꣬���û�з���-1
				int index = books.indexOf(book);
				if(index != -1) {
					//�޸Ŀ����
					books.get(index).setStorage(books.get(index).getStorage()+bookNum);
				}
			}else {
				System.out.println("������ͼ�����ƣ�");
				String bookName = ScannerUtils.next();
				double bookPrice = 0.0;
				while(true) {
					System.out.println("������۸�");
					bookPrice = ScannerUtils.nextDouble();
					if(inputValidation(bookPrice )) {
						break;
					}
				}
				int bookNum = 0;
				while(true) {
					System.out.println("������������");
					bookNum = ScannerUtils.nextInt();
					if(inputValidation(bookNum)) {
						break;
					}
				}
				books.add(new Book(bookId,bookName,bookPrice,bookNum));
			}
			//���鵥�б�д�뵽�ļ���
			FileUtils.addBooks(books, System.getProperty("user.dir")+"/src/books.txt");
			System.out.println("��ӳɹ�! �Ƿ��������鼮(y/n)");
			input = ScannerUtils.next();
		}while("y".equals(input));
	}
	/*
	 * ������֤
	 */
	public Boolean inputValidation(Object obj) {
		if(obj instanceof Integer) {
			int num = (int) obj;
			if(num==-1) {
				System.err.println("������벻��ȷ������������!");
				return false;
			}else if(num==-2){
				System.err.println("���̶�ȡ���ݳ���!");
				return false;
			}else {
				return true;
			}
		}else {
			if(obj instanceof Double) {
				double num = (double) obj;
				if(num==-1) {
					System.err.println("������벻��ȷ������������!");
					return false;
				}else if(num==-2){
					System.err.println("���̶�ȡ���ݳ���!");
					return false;
				}
			}
		}
		return true;
	}
	/*
	 * ����û�
	 */
	public void addUsers() throws Exception{
		String username = "";
		int count =  0;
		do {
			count = 0;
			System.out.print("�������û���:");
			username = ScannerUtils.next();

			for(User user:users) {
				if(user.getUserName().equals(username)) {
					System.out.println("���û��Ѿ�ע�ᣬ�����������û�����");
					count++;
				}
			}
		}while(count>0);
		System.out.print("����������");
		String password = ScannerUtils.next();
		User user = new User(username,password);
		users.add(user);
		FileUtils.addUsers(users,System.getProperty("user.dir")+"/src/users.txt");
	}
}