package com.lovo.cq.shoppingbook.dao;

import java.util.List;

public interface SubTypeDao {
	//根据大类的ID获取所有小类
	public List showAllSubTypeBySuperId(int superId);
}
