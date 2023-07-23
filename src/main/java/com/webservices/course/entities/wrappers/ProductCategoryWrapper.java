package com.webservices.course.entities.wrappers;

import com.webservices.course.entities.Category;
import com.webservices.course.entities.Product;

public class ProductCategoryWrapper {
    private Product product;
    private Category category;
    
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

    
}