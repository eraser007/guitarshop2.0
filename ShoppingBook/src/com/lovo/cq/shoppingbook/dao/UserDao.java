package com.lovo.cq.shoppingbook.dao;

import java.util.List;

import com.lovo.cq.shoppingbook.po.Note;
import com.lovo.cq.shoppingbook.po.Page;
import com.lovo.cq.shoppingbook.po.User;

public interface UserDao {
	//用户注册
	public boolean addUser(User user);
	//用户登录
	public User login(String name,String password);
	//检查用户是否存在
	public boolean checkNameExist(String name);
	//显示所有用户
	public List listUser();
	//分页显示用户
	public Page doPage(int currentPage,int pageSize);
	
	
	//根据ID删除用户
	public boolean delete(int id,String powerType);
	//根据ID更改用户权限
	public boolean changePower(int id,String powerType);
	//根据用户名，获取其权限
	public String getPower(String name);
	//根据用户名，找到该用户
	public User getUser(String name);
	//显示所有用户的留言
	public List showNote();
	//分页显示用户留言
	public Page doNotePage(int currentPage,int pageSize);
	//用户添加留言
	public boolean addNote(Note note);
	
}
