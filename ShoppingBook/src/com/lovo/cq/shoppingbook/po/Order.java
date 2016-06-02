package com.lovo.cq.shoppingbook.po;

import java.util.List;

/**
 * 一个订单的JavaBean
 */
public class Order {
	private int orderId;

	private User user;

	private String orderDate;

	private int flag;//0表示货没发出，1表示货已发出，2表示货冻结
	
	private List orderItem; //一个用于装订单项的List

	public List getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List orderItem) {
		this.orderItem = orderItem;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}