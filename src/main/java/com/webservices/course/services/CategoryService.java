package com.webservices.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.webservices.course.entities.Category;
import com.webservices.course.repositories.CategoryRepository;
import com.webservices.course.services.exceptions.DatabaseException;
import com.webservices.course.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	

	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
	
	public Category insert (Category category) {
		return repository.save(category);
	}
	
	public void delete(Long id) {
	       try{
	    	   Category category = findById(id);
	    	   if(repository.existsById(id)){
	    		   repository.delete(category);
	    	   }else {
	    		   throw new ResourceNotFoundException(id);
	    	   }
	        }catch (DataIntegrityViolationException e) {
	        	throw new DatabaseException(e.getMessage());
	        }
	}
}
