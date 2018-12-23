package com.nelioalves.workshopmongo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
		
	}
	
	public User findByID(String id) {
		
		User user =  repo.findOne(id);
		
		if ( user == null) {
			throw new ObjectNotFoundException("OBjeto nao encontrado");
		}
		return user;
	}
	
	public User Insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findByID(id);
		repo.deleteById(id);
		
	}
	
	
	public User fromDTO(UserDTO objdto) {
		return new User(objdto.getId(),objdto.getName(), objdto.getEmail() );
	
	}
	
}
