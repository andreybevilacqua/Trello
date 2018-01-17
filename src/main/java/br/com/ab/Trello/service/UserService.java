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

	public User addUser(User user) {
		try{
			this.userDao.addUser(user);
			return user;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	public User findUserById(Integer userId) {
		User user = this.userDao.findUserById(userId);
		return user;
	}

	public List<User> findAllUser() {
		return this.userDao.findAllUser();
	}

	public boolean validateUser(User user) {
		boolean result = false;
		if (!user.equals(null)){
			if(!user.getLogin().equals(null) && !user.getPassword().equals(null)) {
				if (!user.getLogin().equals("") && !user.getPassword().equals("") && !user.getLogin().equals("?")){
					result = true;
				}
			}
		}
		return result;
	}
	
	public boolean validateUserId(Integer userId){
		boolean result = false;
		if(userId != null && userId >= 0){
			result = true;
		}
		return result;
	}

}
