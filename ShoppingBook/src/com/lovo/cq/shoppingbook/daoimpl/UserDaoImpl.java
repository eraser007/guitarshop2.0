package com.lovo.cq.shoppingbook.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lovo.cq.shoppingbook.common.DbUtil;
import com.lovo.cq.shoppingbook.dao.UserDao;
import com.lovo.cq.shoppingbook.po.Book;
import com.lovo.cq.shoppingbook.po.Note;
import com.lovo.cq.shoppingbook.po.Page;
import com.lovo.cq.shoppingbook.po.User;

public class UserDaoImpl implements UserDao{

	// 用户注册
	public boolean addUser(User user) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		DbUtil dbUtil = null;
		String sql = "insert into tb_user values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getTrueName());
			pstmt.setString(5, user.getSex());
			pstmt.setString(6, user.getBirthday());
			pstmt.setString(7, user.getAddress());
			pstmt.setString(8, user.getPostcode());
			pstmt.setString(9, user.getPhone());
			pstmt.setString(10, user.getMphone());
			pstmt.setString(11, user.getQuestion());
			pstmt.setString(12, user.getAnswer());
			int i = pstmt.executeUpdate();
			if(i != 0) {
				// 注册成功
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return flag;
	}

	// 用户登录
	public User login(String name,String password) {
		User user = new User();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql ="select * from tb_user where name=?";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(!password.equals(rs.getString("password"))) {
					throw new ErrPwdException("密码不正确！！");
				}else {
					//登录成功
					user.setTrueName(rs.getString("trueName"));
					return user;
				}
			}else {
				throw new NameNotFoundException("用户名不存在！！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return user;
	}
	// 显示出所有用户
	public List listUser() {
		List userList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil du = null;
		String sql = "select * from tb_user";
		try {
			DbUtil dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getInt("id"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				du.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return userList;
	}
	//分页显示用户
	public Page doPage(int currentPage,int pageSize){
		Page page = new Page();
		int totalNum = listUser().size();
		List pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil du = null;
		String sql = "select * from tb_user limit "+currentPage+","+pageSize;
		try {
			DbUtil dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				pageList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				du.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		page.setPageList(pageList);
		page.setTotalNum(totalNum);
		return page;
	}
	
	// 根据ID删除用户
	public boolean delete(int id,String powerType){
		boolean flag = false;
		if(powerType.equals("超级管理员")) {
			PreparedStatement pstmt = null;
			DbUtil du = null;
			String sql = "delete from tb_user where id=?";
			try {
				DbUtil dbUtil = new DbUtil();
				pstmt = dbUtil.getCon().prepareStatement(sql);
				pstmt.setInt(1, id);
				int i = pstmt.executeUpdate();
				if(i != 0){
					flag = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					du.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		return flag;
	}

	public boolean changePower(int id, String powerType) {
		return false;
	}

	public String getPower(String name) {
		return null;
	}

	public User getUser(String name) {
		return null;
	}
//	显示所有用户的留言
	public List showNote(){
		List messageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil du = null;
		String sql = "select * from tb_note";
		try {
			DbUtil dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				try{
					Note note = new Note();
					note.setAuthor(rs.getString("author"));
					note.setTitle(rs.getString("title"));
					note.setContent(rs.getString("content"));
					note.setLy_time(rs.getString("ly_time"));
					note.setImgs(rs.getString("imgs"));
					messageList.add(note);
				}catch(NoMessageException e){
					throw new NoMessageException("目前还没有用户留言！");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageList;
	}
	/**
	 * 分页显示所有用户留言
	 * @param currentPage 显示出来的当前页码
	 * @param pageSize 每页显示数目
	 * @return Page
	 */
	public Page doNotePage(int currentPage,int pageSize){
		Page page = new Page();
		int totalNum = showNote().size();
		List pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = "select * from tb_note limit "+currentPage+","+pageSize;
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Note note = new Note();
				note.setAuthor(rs.getString("author"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setLy_time(rs.getString("ly_time"));
				note.setImgs(rs.getString("imgs"));
				pageList.add(note);
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
		page.setCurrentPage(currentPage);
		page.setPageList(pageList);
		page.setTotalNum(totalNum);
		return page;
	}
	//用户添加留言
	public boolean addNote(Note note){
		boolean flag = false;
		PreparedStatement pstmt = null;
		DbUtil dbUtil = null;
		String sql = "insert into tb_note(id,title,author,content,ly_time,imgs) values(null,?,?,?,null,?)";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setString(1, note.getTitle());
			pstmt.setString(2, note.getAuthor());
			pstmt.setString(3, note.getContent());
			pstmt.setString(4, note.getImgs());
			int i = pstmt.executeUpdate();
			if(i != 0) {
				//留言成功
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	//检查用户是否存在
	public boolean checkNameExist(String name){
		boolean flag = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql ="select * from tb_user where name=?";	
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()){//用户存在
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return flag;
	}
}