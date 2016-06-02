package com.lovo.cq.shoppingbook.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lovo.cq.shoppingbook.common.DbUtil;
import com.lovo.cq.shoppingbook.dao.BookDao;
import com.lovo.cq.shoppingbook.po.Book;
import com.lovo.cq.shoppingbook.po.Page;
import com.lovo.cq.shoppingbook.po.User;

public class BookDaoImpl implements BookDao {
	/**
	 * ��ʾ�鼮
	 * @param type ��ʾ������������飬����,�ر��Ƽ���
	 * @param flag ��ʾ�Ƿ��ǣ����������飬���ۣ�
	 */
	public List showBooks(int type,int flag){
		List all = new ArrayList();
		PreparedStatement pstmt = null;
		DbUtil dbUtil = null;
		ResultSet rs = null;
		String sql = null;
		if(type==0){
			//��ʾ�����鼮
			sql = "select * from tb_book";
			try {
				dbUtil = new DbUtil();
				pstmt = dbUtil.getCon().prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Book book = new Book();
					book.setBookId(rs.getInt("bookId"));
					book.setBookName(rs.getString("bookName"));
					book.setISBN(rs.getString("ISBN"));
					book.setPages(rs.getInt("pages"));
					book.setAuthor(rs.getString("author"));
					book.setPublisher(rs.getString("publisher"));
					book.setIntroduce(rs.getString("introduce"));
					book.setPrice(rs.getFloat("price"));
					book.setNowPrice(rs.getFloat("nowPrice"));
					book.setPicture(rs.getString("picture"));
					all.add(book);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					dbUtil.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
		if(type==1) {
			//�����鼮
		    sql = "select * from tb_book where hostBooks=?";
		}
		if(type==2){
			//�µ��鼮
			sql = "select * from tb_book where newBooks=?";
		}
		if(type==3){
			//�����鼮
			sql = "select * from tb_book where saleBooks=?";
		}
		if(type==4){
			//�ر��Ƽ�
			sql = "select * from tb_book where specialBooks=?";
		}
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setInt(1, flag);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setISBN(rs.getString("ISBN"));
				book.setPages(rs.getInt("pages"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setIntroduce(rs.getString("introduce"));
				book.setPrice(rs.getFloat("price"));
				book.setNowPrice(rs.getFloat("nowPrice"));
				book.setPicture(rs.getString("picture"));
				all.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
		return all;
	}
	/**
	 * �������BookId����չʾ�������ϸ��Ϣ
	 * @param bookId �鼮��
	 * @return Book
	 */
	public Book showBookById(int bookId){
		Book book = new Book();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = "select * from tb_book where bookId=?";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				book.setBookId(rs.getInt("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setIntroduce(rs.getString("introduce"));
				book.setISBN(rs.getString("ISBN"));
				book.setAuthor(rs.getString("author"));
				book.setPages(rs.getInt("pages"));
				book.setPublisher(rs.getString("publisher"));
				book.setPrice(rs.getFloat("price"));
				book.setNowPrice(rs.getFloat("nowPrice"));
				book.setPicture(rs.getString("picture"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return book;
	}
	/**
	 *	���鱾��Ϣ¼�����ݿ� 
	 */
	public boolean addBook(Book book){
		boolean flag = false;
		PreparedStatement pstmt = null;
		DbUtil dbUtil = null;
		String sql = "insert into tb_book values(null,?,?,?,?,?,?,?,?,?,?,?,null,?,?,?,?)";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setInt(1, book.getSuperTypeId());
			pstmt.setInt(2, book.getSubTypeId());
			pstmt.setString(3, book.getBookName());
			pstmt.setString(4, book.getISBN());
			pstmt.setString(5, book.getIntroduce());
			pstmt.setFloat(6, book.getPrice());
			pstmt.setFloat(7, book.getNowPrice());
			pstmt.setString(8, book.getPicture());
			pstmt.setInt(9, book.getPages());
			pstmt.setString(10, book.getPublisher());
			pstmt.setString(11, book.getAuthor());
			pstmt.setInt(12, book.getNewBooks());
			pstmt.setInt(13, book.getSaleBooks());
			pstmt.setInt(14, book.getHostBooks());
			pstmt.setInt(15, book.getBookNum());
			int i = pstmt.executeUpdate();
			if(i != 0){
				//�����鼮�ɹ�
				return flag;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * ��ҳ��ʾ�鼮
	 * @param type ��Ҫ����ʾ��Ӧ���鼮
	 * @param currentPage ��ʾ�����ĵ�ǰҳ��
	 * @param pageSize ÿҳ��ʾ��Ŀ
	 * @return Page
	 */
	public Page doPage(int type,int currentPage,int pageSize){
		Page page = new Page();
		System.out.println("pagetype="+type);
		int totalNum = showBooks(type,1).size();
		System.out.println("totalNum"+totalNum);
		List pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = null;
		if(type==0){//��ʾ�����鼮
			sql = "select bookName,picture,price,nowPrice from tb_book limit "+currentPage+","+pageSize;
		}
		if(type==1) {
			//�����鼮
		    sql = "select bookName,picture,price,nowPrice from tb_book where hostBooks=1 limit "+currentPage+","+pageSize;
		}
		if(type==2){
			//�µ��鼮
			sql = "select bookName,picture,price,nowPrice from tb_book where newBooks=1 limit "+currentPage+","+pageSize;
		}
		if(type==3){
			//�����鼮
			sql = "select bookName,picture,price,nowPrice from tb_book where saleBooks=1 limit "+currentPage+","+pageSize;
		}
		if(type==4){
			//�ر��Ƽ�
			sql = "select bookName,picture,price,nowPrice from tb_book where specialBooks=1 limit "+currentPage+","+pageSize;
		}
		System.out.println("sql="+sql);
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setPicture(rs.getString("picture"));
				book.setPrice(rs.getFloat("price"));
				book.setNowPrice(rs.getFloat("nowPrice"));
				pageList.add(book);
			}
			System.out.println("pageList="+pageList.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		page.setPageList(pageList);
		page.setTotalNum(totalNum);
		return page;
	}
	/**
	 * �����û�����Ĺؼ�����������鼮
	 * @param keywords �û�����Ĺؼ��� 
	 */
	public List searchBooks(String keywords){
		List searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = "select * from tb_book where bookName like '%"+keywords+"%'";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				//������鼮
				while(rs.next()){
					Book book = new Book();
					book.setBookName(rs.getString("bookName"));
					book.setPicture(rs.getString("picture"));
					searchList.add(book);
				}	
			}else{
				//û���ҵ�����鼮
				throw new NoRelativeBooksException("û�������������ҵ�����鼮����");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchList;
	}
	/**
	 * ��ҳ��ʾ�鼮
	 * @param currentPage ��ʾ�����ĵ�ǰҳ��
	 * @param pageSize ÿҳ��ʾ��Ŀ
	 * @return Page
	 */
	public Page doPage(String keywords,int currentPage,int pageSize){
		Page page = new Page();
		int totalNum = searchBooks(keywords).size();
		System.out.println("totalNum"+totalNum);
		List pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = "select * from tb_book limit "+currentPage+","+pageSize;
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setPicture(rs.getString("picture"));
				book.setPrice(rs.getFloat("price"));
				book.setNowPrice(rs.getFloat("nowPrice"));
				pageList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		page.setPageList(pageList);
		page.setTotalNum(totalNum);
		return page;
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
		List bookList = new ArrayList();
		String str[] = searchMethod.split("=");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = null;
		if(str[0].equals("price")){//ѡ���˰��۸��ѯ��ʽ
			if(str[1].equals("����200")){
				int priceLow = 200;
				if(upLoadTime.equals("����30")){
					int dateLow = 30;
					
					//�۸����200��ʱ�����һ����
					sql = "select * from tb_book where superTypeId=? and subTypeId=? and "+str[0]+" >? and inTime>?";
				}else {
					//�۸����200��ʱ��û��һ����
					int dateLow = Integer.parseInt(upLoadTime);
					sql = "select * from tb_book where superTypeId=? and subTypeId=? and "+str[0]+" >? and upLoadTime<?";
				}
			}else{
				int priceLower = Integer.parseInt(str[1].split("-")[0]);
				int priceTop = Integer.parseInt(str[1].split("-")[1]);
				if(upLoadTime.equals("����30")){
					//�۸�С��200��ʱ�����30
					int dateLow = 30;
					sql = "";
				}else {
					//�۸�С��200��ʱ��û��һ����
					int dateLow = Integer.parseInt(upLoadTime);
					sql = "";
				}
			}	
		}else {//ѡ�����������ѯ��ʽ
			if(upLoadTime.equals("����30")){
				int dateLow = 30;
				//ʱ�����һ����
				sql = "";
			}else {
				//ʱ��û��һ����
				int dateLow = Integer.parseInt(upLoadTime);
				sql = "";
			}
		}
		System.out.println("sql="+sql);
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setInt(1, superTypeId);
			pstmt.setInt(2, subTypeId);
			pstmt.setString(3, str[1]);
			pstmt.setString(4, upLoadTime);
			rs = pstmt.executeQuery();
			if(rs.next()){
				//������鼮
				while(rs.next()){
					Book book = new Book();
					book.setBookName(rs.getString("bookName"));
					book.setPicture(rs.getString("picture"));
					bookList.add(book);
				}	
			}else{
				//û���ҵ�����鼮
				throw new NoRelativeBooksException("û�������������ҵ�����鼮����");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}
	/**
	 * �����û�ѡ�������������������鼮
	 * @param superTypeId ���������ID
	 * @param subTypeId ����С���ID
	 * @param searchMethod �������ǰ����ߡ������������硢ISBN���۸��е�һ����
	 * @param upLoadTime �鼮�ϼ�ʱ��
	 */
	public Page doPageByConditons(int superTypeId,int subTypeId,String searchMethod,String upLoadTime,int currentPage,int pageSize){
		Page page = new Page();
		String str[] = searchMethod.split("=");
		int totalNum = searchBooksByConditions(superTypeId,subTypeId,searchMethod,upLoadTime).size();
		System.out.println("totalNum"+totalNum);
		List pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = null;
		if(str[0].equals("price")){//ѡ���˰��۸��ѯ��ʽ
			if(str[1].equals("����200")){
				int priceLow = 200;
				if(upLoadTime.equals("����30")){
					long dateLow = 30*24*60*60*1000;
					long nowTime = new Date().getTime();
					long gup = nowTime - dateLow;
					String inTime = null;
					//�۸����200��ʱ�����һ����
					sql = "select * from tb_book where superTypeId=? and subTypeId=? and "+str[0]+" > ? ";
				}else {
					//�۸����200��ʱ��û��һ����
					int dateLow = Integer.parseInt(upLoadTime);
					sql = "";
				}
			}else{
				int priceLower = Integer.parseInt(str[1].split("-")[0]);
				int priceTop = Integer.parseInt(str[1].split("-")[1]);
				if(upLoadTime.equals("����30")){
					//�۸�С��200��ʱ�����30
					int dateLow = 30;
					sql = "";
				}else {
					//�۸�С��200��ʱ��û��һ����
					int dateLow = Integer.parseInt(upLoadTime);
					sql = "";
				}
			}	
		}else {//ѡ�����������ѯ��ʽ
			if(upLoadTime.equals("����30")){
				int dateLow = 30;
				//ʱ�����һ����
				sql = "";
			}else {
				//ʱ��û��һ����
				int dateLow = Integer.parseInt(upLoadTime);
				sql = "";
			}
		}
		System.out.println("sql="+sql);
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setPicture(rs.getString("picture"));
				book.setPrice(rs.getFloat("price"));
				book.setNowPrice(rs.getFloat("nowPrice"));
				pageList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		page.setPageList(pageList);
		page.setTotalNum(totalNum);
		return page;
	}
}
