package com.webservices.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.webservices.course.entities.User;
import com.webservices.course.repositories.UserRepository;
import com.webservices.course.services.exceptions.DatabaseException;
import com.webservices.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert (User user) {
		return repository.save(user);
	}
	
	public void delete(Long id) {
	       try{
	    	   User user = findById(id);
	    	   if(repository.existsById(id)){
	    		   repository.delete(user);
	    	   }else {
	    		   throw new ResourceNotFoundException(id);
	    	   }
	        }catch (DataIntegrityViolationException e) {
	        	throw new DatabaseException(e.getMessage());
	        }
	}
	
	public User update(Long id, User user) {
		User entity = repository.getReferenceById(id);
		updateData(entity, user);
		return repository.save(entity);
	}


	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}
}
