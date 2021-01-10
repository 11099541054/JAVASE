package bookshop;
public class Test {
	public static void main(String[] args) throws Exception {
		BookShop bm = new BookShop();
		//bm.addUsers();
		Menu.loginMenu();
		System.out.println("请输入用户名：");
		String username = ScannerUtils.next();
		System.out.println("请输入密码：");
		String password = ScannerUtils.next();
		if(bm.login(username,password)) {
			boolean flag = true;
			while(flag) {
				Menu.mainMenu();
				//循环进行选择，直到退出
				System.out.println("请选择功能:");
				int key = ScannerUtils.nextInt();
				//.mainMenu();
				switch (key) {
				case 1:
					Order order = null;
					String str = "";
					bm.showBoooks();
					do {
						order = bm.buyInfo();
						bm.showBoooks();
						System.out.println("是否继续购买(y/n)");
						str = ScannerUtils.next();
					}while("y".equals(str));
					//ScannerUtils.close();
					bm.orderInfo(order);
					break; 
				case 2:
						bm.addBooks();
					break;
				case 3:
					flag = false;
					System.out.println("退出系统");
					break;
				}
			}
			ScannerUtils.close();
		}else {
			System.out.println("你输入的用户名或密码有误。");
			ScannerUtils.close();
		}
	}
}
