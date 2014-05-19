package com.qorder.qorderws.model.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qorder.qorderws.model.product.Product;

@Entity
@Table(name = "PRODUCT_CATEGORIES")
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_CATEGORY_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "FK_PRODUCT_CATEGORY_ID")
	private List<Product> productList = new ArrayList<Product>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public boolean addProduct(Product product) {
		return productList.add(product);
	}
}
