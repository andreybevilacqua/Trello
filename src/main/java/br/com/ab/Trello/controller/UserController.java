package br.com.ab.Trello.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.model.User;
import br.com.ab.Trello.service.UserService;

@WebService
@Stateless
public class UserController {

	@Inject
	private UserService userService;

	public void addUser(User user) {
		if (!user.equals(null)) {
			this.userService.addUser(user);
		}
	}

	@WebResult(name = "user")
	public User findUserById(
			@WebParam(name = "userId") @XmlElement(required = true, nillable = false) Integer userId) {
		return this.userService.findUserById(userId);
	}
	
	@WebResult(name = "user")
	public List<User> findAllUser() {
		return this.userService.findAllUser();
	}
}
