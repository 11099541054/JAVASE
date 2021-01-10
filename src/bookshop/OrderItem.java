package bookshop;

public class OrderItem {
	private String booksName;
	private double price;
	private int num;
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrderItem(String booksName, double price, int num) {
		super();
		this.booksName = booksName;
		this.price = price;
		this.num = num;
	}


	public String getBooksName() {
		return booksName;
	}


	public void setBooksName(String booksName) {
		this.booksName = booksName;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (booksName == null) {
			if (other.booksName != null)
				return false;
		} else if (!booksName.equals(other.booksName))
			return false;
		return true;
	}
	
}
