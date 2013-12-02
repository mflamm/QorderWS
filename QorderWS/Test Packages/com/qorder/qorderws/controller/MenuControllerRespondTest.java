package com.qorder.qorderws.controller;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;

public class MenuControllerRespondTest {
	
	private AppClient client;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		client = new AppClient();
	}

	@Test
	public final void testGetMenuById() {
		System.out.println("Test menu controller respond:");
		long businessId = 2;
		Menu fetchedMenu =  client.requestForMenu("http://localhost:8080/qorderws/menus/business?id=5",businessId);
		System.out.println("Check object characteristics after parsing from Json:\n\n");
		Iterator<Category> categoryItr = fetchedMenu.getCategoryList().iterator();
		while(categoryItr.hasNext())
		{
			Category proxyCategory = categoryItr.next();
			System.out.println(proxyCategory.toString());
		}
		assertNotNull(fetchedMenu);
	}

}