package com.lovo.cq.shoppingbook.daoimpl;

import com.lovo.cq.shoppingbook.common.DbUtil;
import com.lovo.cq.shoppingbook.dao.OrderDao;
import com.lovo.cq.shoppingbook.po.Order;
import com.lovo.cq.shoppingbook.po.OrderItem;

import java.sql.*;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

	/**
	 * 添加订单，并返回订一个单号
	 * @param order 一个订单
	 * @return int  返回一个整型的订单号
	 */
	public int addOrder(Order order) {
		int orderId = 0;
		PreparedStatement pstmt_order = null; //用于对订单进行操作的预定义语句
		PreparedStatement pstmt_item = null; //用于对订单项进行操作的预定义语句
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql_order = "insert into tb_order values(null,?,?,?,?,null,?)";
		try {
			//事物处理
			dbUtil = new DbUtil();
			dbUtil.getCon().setAutoCommit(false);
			//将订单中的数据录入数据库
			pstmt_order = dbUtil.getCon().prepareStatement(sql_order);
			pstmt_order.setString(1, order.getUser().getName());
			pstmt_order.setString(2, order.getUser().getAddress());
			pstmt_order.setString(3, order.getUser().getPostcode());
			pstmt_order.setString(4, order.getUser().getEmail());
			pstmt_order.setInt(5,order.getFlag() );
			pstmt_order.executeUpdate();
			rs = pstmt_order.getGeneratedKeys();//取得主键
			rs.next();
			orderId = rs.getInt(1); //返回一个订单号
			//将订单项中的数据录入数据库
			String sql_item = "insert into tb_orderItem values(null,?,?,?,?,?)";
			pstmt_item = dbUtil.getCon().prepareStatement(sql_item);
			List orderItem = order.getOrderItem();//得到所有订单项
			for(int i=0;i<orderItem.size();i++) {
				OrderItem orderItems = (OrderItem)orderItem.get(i);
				pstmt_item.setInt(1, orderId);
				pstmt_item.setInt(2, orderItems.getBookId());
				pstmt_item.setString(3, orderItems.getBookName());
				pstmt_item.setFloat(4, orderItems.getPrice());
				pstmt_item.setInt(5, orderItems.getBookNum());
				pstmt_item.addBatch();
			}
			pstmt_item.executeBatch();
			dbUtil.getCon().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dbUtil.getCon().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				rs.close();
				pstmt_order.close();
				pstmt_item.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return orderId;
	}
}
