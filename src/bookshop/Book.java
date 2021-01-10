package bookshop;

public class Book {
 
	private int bookId;;
	private String bookName;
	private double price;
	private int storage;
	
    public Book() {}
	public Book(int bookId) {
		super();
		this.bookId = bookId;
	}
	public Book(int bookId, String bookName, double price, int storage) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.price = price;
		this.storage = storage;
	}

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStorage() {
		return storage;
	}
	public void setStorage(int storage) {
		this.storage = storage;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", price=" + price + ", storage=" + storage + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId != other.bookId)
			return false;
		return true;
	}
	
	
}
