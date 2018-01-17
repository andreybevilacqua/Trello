package br.com.ab.Trello.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.error.ErrorMessage;
import br.com.ab.Trello.error.WSObjectFault;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.User;
import br.com.ab.Trello.service.UserService;

@WebService
@Stateless
public class UserController {

	@Inject
	private UserService userService;

	@WebMethod(operationName = "addUser")
	@WebResult(name = "User")
	public User addUser(@WebParam(name = "New_User") @XmlElement(required = true, nillable = false) User user)
			throws WSObjectException {
		if (userService.validateUser(user)) {
			return userService.addUser(user);
		} else {
			throw new WSObjectException(new WSObjectFault(ErrorMessage.EMPTY_NULL_PARAMETERS));
		}
	}

	@WebMethod(operationName = "findUserById")
	@WebResult(name = "user")
	public User findUserById(@WebParam(name = "userId") @XmlElement(required = true, nillable = false) Integer userId)
			throws WSObjectException {
		if(userService.validateUserId(userId)){
			return this.userService.findUserById(userId);
		} else{
			throw new WSObjectException(new WSObjectFault(ErrorMessage.ID_NULL_MINOR_ZERO));
		}
		
	}

	@WebMethod(operationName="findAllUsers")
	@WebResult(name="listOfUsers")
	public List<User> findAllUser() {
		return this.userService.findAllUser();
	}

}
