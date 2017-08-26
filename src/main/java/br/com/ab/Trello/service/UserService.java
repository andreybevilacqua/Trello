package br.com.ab.Trello.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ab.Trello.dao.UserDao;
import br.com.ab.Trello.model.User;

@Stateless
public class UserService {

	@Inject
	UserDao userDao;
	
	public void addUser(User user){
		userDao.addUser(user);
	}
	
	public User findUserById(Integer userId){
		User user = userDao.findUserById(userId);
		return user;
	}
	
	public List<User> findAllUser(){
		return userDao.findAllUser();
	}
}
