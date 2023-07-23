package com.webservices.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.webservices.course.entities.Category;
import com.webservices.course.entities.Product;
import com.webservices.course.repositories.ProductRepository;
import com.webservices.course.services.exceptions.DatabaseException;
import com.webservices.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	

	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	public Product insert (Product product,Category cat) {
		Product prod = new Product(null, product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
		prod.getCategories().add(cat);
		return repository.save(prod);
	}
	
	public void delete(Long id) {
	       try{
	    	   Product product = findById(id);
	    	   if(repository.existsById(id)){
	    		   repository.delete(product);
	    	   }else {
	    		   throw new ResourceNotFoundException(id);
	    	   }
	        }catch (DataIntegrityViolationException e) {
	        	throw new DatabaseException(e.getMessage());
	        }
	}
	
	public Product update(Long id, Product product, Category cat) {
		try {
		Product entity = repository.getReferenceById(id);
		updateData(entity, product, cat);
		return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	

	private void updateData(Product entity, Product product,Category cat) {
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setPrice(product.getPrice());
		entity.getCategories().add(cat);
		
	}
}
