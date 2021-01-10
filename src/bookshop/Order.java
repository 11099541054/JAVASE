package bookshop;

import java.util.Date;
import java.util.List;
public class Order {
	private String orderId;
	private double total;
	private Date date;
	private List<OrderItem> orderItems ;//订单项列表

	public Order() {}

	public Order(String orderId, double total, Date date, List<OrderItem> orderItems) {
		this.orderId = orderId;
		this.total = total;
		this.date = date;
		this.orderItems = orderItems;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
