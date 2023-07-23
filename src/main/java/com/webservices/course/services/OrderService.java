package com.webservices.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.webservices.course.entities.Order;
import com.webservices.course.repositories.OrderRepository;
import com.webservices.course.services.exceptions.DatabaseException;
import com.webservices.course.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	

	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
	
	public Order insert (Order order) {
		return repository.save(order);
	}
	
	public void delete(Long id) {
	       try{
	    	   Order order = findById(id);
	    	   if(repository.existsById(id)){
	    		   repository.delete(order);
	    	   }else {
	    		   throw new ResourceNotFoundException(id);
	    	   }
	        }catch (DataIntegrityViolationException e) {
	        	throw new DatabaseException(e.getMessage());
	        }
	}
}
