package com.lovo.cq.shoppingbook.model;

import java.util.List;
import com.lovo.cq.shoppingbook.dao.BookDao;
import com.lovo.cq.shoppingbook.dao.OrderDao;
import com.lovo.cq.shoppingbook.dao.SubTypeDao;
import com.lovo.cq.shoppingbook.dao.SuperTypeDao;
import com.lovo.cq.shoppingbook.dao.UserDao;
import com.lovo.cq.shoppingbook.daoimpl.BookDaoImpl;
import com.lovo.cq.shoppingbook.daoimpl.OrderDaoImpl;
import com.lovo.cq.shoppingbook.daoimpl.SubTypeDaoImpl;
import com.lovo.cq.shoppingbook.daoimpl.SuperTypeDaoImpl;
import com.lovo.cq.shoppingbook.daoimpl.UserDaoImpl;
import com.lovo.cq.shoppingbook.po.Book;
import com.lovo.cq.shoppingbook.po.Note;
import com.lovo.cq.shoppingbook.po.Order;
import com.lovo.cq.shoppingbook.po.Page;
import com.lovo.cq.shoppingbook.po.User;

public class Model {
	private UserDao ud = new UserDaoImpl();
	private BookDao bd = new BookDaoImpl();
	private OrderDao od = new OrderDaoImpl();
	private SuperTypeDao superDao = new SuperTypeDaoImpl();

	private SubTypeDao subDao = new SubTypeDaoImpl();

	public List showAllSuperType() {
		return superDao.showAllSuperType();
	}

	public List showAllSubTypeBySuperId(int superId) {
		return subDao.showAllSubTypeBySuperId(superId);
	}
	//用户注册
	public boolean addUser(User user) {
		return ud.addUser(user);
	}
	//用户登录
	public User login(String name,String password) {
		return ud.login(name,password);
	}
	//检查用户名是否存在
	public boolean checkNameExist(String name){
		return ud.checkNameExist(name);
	}
	//用户管理页面
	public List listUser() {
		return ud.listUser();
	}
	//分页显示用户
//	public Page doPage(int currentPage,int pageSize){
//		return ud.doPage(currentPage, pageSize);
//	}
	//根据ID删除用户
	public boolean delete(int id,String powerType){
		return ud.delete(id,powerType);
	}
	//根据ID更改用户权限
	public boolean changePower(int id,String powerType){
		return ud.changePower(id,powerType);
	}
	//	 根据用户名，获取其权限
	public String getPower(String name){
		return ud.getPower(name);
	}
	//根据用户名，找到该用户
	public User getUser(String name){
		return ud.getUser(name);
	}
	//显示书籍
	public List showBooks(int type,int flag) {
		return bd.showBooks(type, flag);
	}
	//根据书籍的bookId来显示书籍的详细信息
	public Book showBookById(int bookId){
		return bd.showBookById(bookId);
	}
	//添加订单，并返回订一个单号
	public int addOrder(Order order){
		return od.addOrder(order);
	}
	//分页显示书籍
	public Page doPage(int type,int currentPage,int pageSize){
		return bd.doPage(type,currentPage, pageSize);
	}
	//根据用输入的关键字搜索相关书籍
	public List searchBooks(String keywords){
		return bd.searchBooks(keywords);
	}
	/**
	 * 分页显示书籍
	 * @param currentPage 显示出来的当前页码
	 * @param pageSize 每页显示数目
	 * @return Page
	 */
	public Page doPage(String keywords,int currentPage,int pageSize){
		return bd.doPage(keywords, currentPage, pageSize);
	}
	//显示所有用户留言
	public List showNote(){
		return ud.showNote();
	}
	/**
	 * 分页显示所有用户留言
	 * @param currentPage 显示出来的当前页码
	 * @param pageSize 每页显示数目
	 * @return Page
	 */
	public Page doNotePage(int currentPage,int pageSize){
		return ud.doNotePage(currentPage, pageSize);
	}
	//用户添加留言
	public boolean addNote(Note note){
		return ud.addNote(note);
	}
	/**
	 * 根据用户选择的搜索条件搜索相关书籍
	 * @param superTypeId 所属大类的ID
	 * @param subTypeId 所属小类的ID
	 * @param searchMethod （可以是按作者、书名、出版社、ISBN、价格中的一个）
	 * @param upLoadTime 书籍上价时间
	 * @return List
	 */
	public List searchBooksByConditions(int superTypeId,int subTypeId,String searchMethod,String upLoadTime){
		return bd.searchBooksByConditions(superTypeId, subTypeId, searchMethod, upLoadTime);
	}
	/**
	 * 根据用户选择的搜索条件搜索相关书籍
	 * @param superTypeId 所属大类的ID
	 * @param subTypeId 所属小类的ID
	 * @param searchMethod （可以是按作者、书名、出版社、ISBN、价格中的一个）
	 * @param upLoadTime 书籍上价时间
	 */
	public Page doPageByConditons(int superTypeId,int subTypeId,String searchMethod,String upLoadTime,int currentPage,int pageSize){
		return bd.doPageByConditons(superTypeId, subTypeId, searchMethod, upLoadTime, currentPage, pageSize);
	}
}
