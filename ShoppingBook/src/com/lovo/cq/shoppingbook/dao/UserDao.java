package com.lovo.cq.shoppingbook.dao;

import java.util.List;

import com.lovo.cq.shoppingbook.po.Note;
import com.lovo.cq.shoppingbook.po.Page;
import com.lovo.cq.shoppingbook.po.User;

public interface UserDao {
	//�û�ע��
	public boolean addUser(User user);
	//�û���¼
	public User login(String name,String password);
	//����û��Ƿ����
	public boolean checkNameExist(String name);
	//��ʾ�����û�
	public List listUser();
	//��ҳ��ʾ�û�
	public Page doPage(int currentPage,int pageSize);
	
	
	//����IDɾ���û�
	public boolean delete(int id,String powerType);
	//����ID�����û�Ȩ��
	public boolean changePower(int id,String powerType);
	//�����û�������ȡ��Ȩ��
	public String getPower(String name);
	//�����û������ҵ����û�
	public User getUser(String name);
	//��ʾ�����û�������
	public List showNote();
	//��ҳ��ʾ�û�����
	public Page doNotePage(int currentPage,int pageSize);
	//�û��������
	public boolean addNote(Note note);
	
}
