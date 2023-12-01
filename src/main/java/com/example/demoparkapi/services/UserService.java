package com.example.demoparkapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoparkapi.entities.User;
import com.example.demoparkapi.exceptions.PasswordInvalidException;
import com.example.demoparkapi.exceptions.UserNameUniqueException;
import com.example.demoparkapi.exceptions.UserNotFoundException;
import com.example.demoparkapi.repositories.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Transactional
	public User createUser(User user) {
		try {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}catch (DataIntegrityViolationException e) {
		throw new UserNameUniqueException(String.format("username {%s} ja cadastrado", user.getUsername()));
		
	 }
	}
	
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Usuario nao encontrado"));
	}

	@Transactional
	public String alterPassword(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
		User u = findById(id);
		
		if(!passwordEncoder.matches(senhaAtual, u.getPassword())) {
			throw new PasswordInvalidException("Senha atual invalida, verifique e tente novamente");
		}
		if(!novaSenha.equals(confirmaSenha)) {
			throw new PasswordInvalidException("Senha de confirmaçao nao confere com a nova senha");
		}
		u.setPassword(passwordEncoder.encode(novaSenha));	
		return  "Senha alterada com sucesso!";
		
	}
	
	@Transactional(readOnly = true)
	public List<User> findAll() {
		List<User> u = userRepo.findAll();
		return u;
	}

	@Transactional(readOnly = true)
	public User findByName(String username) {
		return userRepo.findByUsername(username).orElseThrow(()-> new UserNotFoundException(String.format("Usuario com {username} nao encontrado",username)));
	}
	
	@Transactional(readOnly = true)
	public String findRoleByUsername(String username) {
		return userRepo.findRoleByName(username);
	}

}
