package bookshop;
public class Test {
	public static void main(String[] args) throws Exception {
		BookShop bm = new BookShop();
		//bm.addUsers();
		Menu.loginMenu();
		System.out.println("�������û�����");
		String username = ScannerUtils.next();
		System.out.println("���������룺");
		String password = ScannerUtils.next();
		if(bm.login(username,password)) {
			boolean flag = true;
			while(flag) {
				Menu.mainMenu();
				//ѭ������ѡ��ֱ���˳�
				System.out.println("��ѡ����:");
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
						System.out.println("�Ƿ��������(y/n)");
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
					System.out.println("�˳�ϵͳ");
					break;
				}
			}
			ScannerUtils.close();
		}else {
			System.out.println("��������û�������������");
			ScannerUtils.close();
		}
	}
}
