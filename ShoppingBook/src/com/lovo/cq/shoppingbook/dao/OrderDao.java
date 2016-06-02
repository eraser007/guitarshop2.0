package com.lovo.cq.shoppingbook.dao;

import com.lovo.cq.shoppingbook.po.Order;

/**
 * 对订单进行操作的接口 
 */
public interface OrderDao {
	
	//添加订单，并返回订一个单号
	public int addOrder(Order order);
}
