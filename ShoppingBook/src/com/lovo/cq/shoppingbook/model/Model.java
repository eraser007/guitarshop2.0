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
	//�û�ע��
	public boolean addUser(User user) {
		return ud.addUser(user);
	}
	//�û���¼
	public User login(String name,String password) {
		return ud.login(name,password);
	}
	//����û����Ƿ����
	public boolean checkNameExist(String name){
		return ud.checkNameExist(name);
	}
	//�û�����ҳ��
	public List listUser() {
		return ud.listUser();
	}
	//��ҳ��ʾ�û�
//	public Page doPage(int currentPage,int pageSize){
//		return ud.doPage(currentPage, pageSize);
//	}
	//����IDɾ���û�
	public boolean delete(int id,String powerType){
		return ud.delete(id,powerType);
	}
	//����ID�����û�Ȩ��
	public boolean changePower(int id,String powerType){
		return ud.changePower(id,powerType);
	}
	//	 �����û�������ȡ��Ȩ��
	public String getPower(String name){
		return ud.getPower(name);
	}
	//�����û������ҵ����û�
	public User getUser(String name){
		return ud.getUser(name);
	}
	//��ʾ�鼮
	public List showBooks(int type,int flag) {
		return bd.showBooks(type, flag);
	}
	//�����鼮��bookId����ʾ�鼮����ϸ��Ϣ
	public Book showBookById(int bookId){
		return bd.showBookById(bookId);
	}
	//��Ӷ����������ض�һ������
	public int addOrder(Order order){
		return od.addOrder(order);
	}
	//��ҳ��ʾ�鼮
	public Page doPage(int type,int currentPage,int pageSize){
		return bd.doPage(type,currentPage, pageSize);
	}
	//����������Ĺؼ�����������鼮
	public List searchBooks(String keywords){
		return bd.searchBooks(keywords);
	}
	/**
	 * ��ҳ��ʾ�鼮
	 * @param currentPage ��ʾ�����ĵ�ǰҳ��
	 * @param pageSize ÿҳ��ʾ��Ŀ
	 * @return Page
	 */
	public Page doPage(String keywords,int currentPage,int pageSize){
		return bd.doPage(keywords, currentPage, pageSize);
	}
	//��ʾ�����û�����
	public List showNote(){
		return ud.showNote();
	}
	/**
	 * ��ҳ��ʾ�����û�����
	 * @param currentPage ��ʾ�����ĵ�ǰҳ��
	 * @param pageSize ÿҳ��ʾ��Ŀ
	 * @return Page
	 */
	public Page doNotePage(int currentPage,int pageSize){
		return ud.doNotePage(currentPage, pageSize);
	}
	//�û��������
	public boolean addNote(Note note){
		return ud.addNote(note);
	}
	/**
	 * �����û�ѡ�������������������鼮
	 * @param superTypeId ���������ID
	 * @param subTypeId ����С���ID
	 * @param searchMethod �������ǰ����ߡ������������硢ISBN���۸��е�һ����
	 * @param upLoadTime �鼮�ϼ�ʱ��
	 * @return List
	 */
	public List searchBooksByConditions(int superTypeId,int subTypeId,String searchMethod,String upLoadTime){
		return bd.searchBooksByConditions(superTypeId, subTypeId, searchMethod, upLoadTime);
	}
	/**
	 * �����û�ѡ�������������������鼮
	 * @param superTypeId ���������ID
	 * @param subTypeId ����С���ID
	 * @param searchMethod �������ǰ����ߡ������������硢ISBN���۸��е�һ����
	 * @param upLoadTime �鼮�ϼ�ʱ��
	 */
	public Page doPageByConditons(int superTypeId,int subTypeId,String searchMethod,String upLoadTime,int currentPage,int pageSize){
		return bd.doPageByConditons(superTypeId, subTypeId, searchMethod, upLoadTime, currentPage, pageSize);
	}
}
