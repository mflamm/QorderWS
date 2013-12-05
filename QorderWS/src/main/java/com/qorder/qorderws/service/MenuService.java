package com.qorder.qorderws.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IMenuDAO;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;

public class MenuService implements IMenuService {

	private IMenuDAO menuDAO;
	
	public IMenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(IMenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	@Transactional
	@Override
	public Menu fetchMenuById(long businessId) {
		Menu menu = new Menu();
		List<Category> categoryList = menuDAO.getCategoryListById(businessId);
		menu.setCategoryList(categoryList);
		return menu;
	}
}
