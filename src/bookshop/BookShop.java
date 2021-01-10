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
	 * 登录验证
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
	 * 显示图书列表
	 */
	public void showBoooks() {
		System.out.println("\t图书列表");
		System.out.println("图书编号\t"+"图书名称\t"+"图书单价\t"+"库存数量");
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
	 * 购买书籍
	 * @return
	 */
	public Order buyInfo() {

		int no= 0;//图书编号输入的
		int count =0;//输入的购买数量
		order.setOrderId(System.currentTimeMillis()+"");
		order.setDate(new Date());
		System.out.println("请输入图书编号选择图书：");
		try{
			//Scanner sc = new Scanner(System.in);
			no = ScannerUtils.nextInt();	
		}catch(InputMismatchException e) {
			System.out.println("输入非法!");
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
			System.out.println("您输入的书籍编号不存在！");
			return null;
		}
		System.out.println("请输入购买数量：");
		try {
			count = ScannerUtils.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("输入非法！");
			return null;
		}
		if(count<0) {
			System.out.println("购买的书籍数量不能小于零！");
			return null;
		}
		if(book.getStorage()<count) {
			System.out.println("库存不足！");
			return null;
		}
		//封装订单项
		OrderItem orderItem = new OrderItem(book.getBookName(),book.getPrice(),count);
		int index = orderItems.indexOf(orderItem);//查找对象在链表中的位置
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
	 * 显示订单信息
	 */
	public void orderInfo(Order order) {
		double total = 0.0;
		if(order != null) {
			System.out.println("\t图书订单");
			System.out.println("图书名称\t"+"购买数量\t"+"图书单价\t");
			System.out.println("----------------------------------------");
			for(OrderItem orderItem : order.getOrderItems()) {
				if(orderItem != null) {
					total+=orderItem.getNum()*orderItem.getPrice();
					System.out.println(orderItem.getBooksName()+"\t"+
							orderItem.getNum()+"\t"+orderItem.getPrice());
				}
			}
			System.out.println("订单总额："+String.format("%.2f", total)+"元。");
			System.out.println("日期："+new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(order.getDate()));
		}else {
			System.out.println("退出购买功能。");
		}
	}
	/*
	 * 添加图书
	 */
	public void addBooks() throws Exception{
		String input = "";
		do {
			int bookId = 0;
			while(true) {
				System.out.println("请输入图书编号：");
				bookId = ScannerUtils.nextInt();
				if(inputValidation(bookId)) {
					break;
				}
			}
			Book book = new Book(bookId);
			if(books.contains(book)) {
				int bookNum = 0;
				while(true) {
					System.out.println("请输入数量：");
					bookNum = ScannerUtils.nextInt();
					if(inputValidation(bookNum)) {
						break;
					}
				}
				//返回List中当前book对象的索引，如果有返回下标，如果没有返回-1
				int index = books.indexOf(book);
				if(index != -1) {
					//修改库存量
					books.get(index).setStorage(books.get(index).getStorage()+bookNum);
				}
			}else {
				System.out.println("请输入图书名称：");
				String bookName = ScannerUtils.next();
				double bookPrice = 0.0;
				while(true) {
					System.out.println("请输入价格");
					bookPrice = ScannerUtils.nextDouble();
					if(inputValidation(bookPrice )) {
						break;
					}
				}
				int bookNum = 0;
				while(true) {
					System.out.println("请输入数量：");
					bookNum = ScannerUtils.nextInt();
					if(inputValidation(bookNum)) {
						break;
					}
				}
				books.add(new Book(bookId,bookName,bookPrice,bookNum));
			}
			//把书单列表写入到文件中
			FileUtils.addBooks(books, System.getProperty("user.dir")+"/src/books.txt");
			System.out.println("添加成功! 是否继续添加书籍(y/n)");
			input = ScannerUtils.next();
		}while("y".equals(input));
	}
	/*
	 * 输入验证
	 */
	public Boolean inputValidation(Object obj) {
		if(obj instanceof Integer) {
			int num = (int) obj;
			if(num==-1) {
				System.err.println("你的输入不正确，请重新输入!");
				return false;
			}else if(num==-2){
				System.err.println("键盘读取数据出错!");
				return false;
			}else {
				return true;
			}
		}else {
			if(obj instanceof Double) {
				double num = (double) obj;
				if(num==-1) {
					System.err.println("你的输入不正确，请重新输入!");
					return false;
				}else if(num==-2){
					System.err.println("键盘读取数据出错!");
					return false;
				}
			}
		}
		return true;
	}
	/*
	 * 添加用户
	 */
	public void addUsers() throws Exception{
		String username = "";
		int count =  0;
		do {
			count = 0;
			System.out.print("请输入用户名:");
			username = ScannerUtils.next();

			for(User user:users) {
				if(user.getUserName().equals(username)) {
					System.out.println("该用户已经注册，请重新输入用户名！");
					count++;
				}
			}
		}while(count>0);
		System.out.print("请输入密码");
		String password = ScannerUtils.next();
		User user = new User(username,password);
		users.add(user);
		FileUtils.addUsers(users,System.getProperty("user.dir")+"/src/users.txt");
	}
}