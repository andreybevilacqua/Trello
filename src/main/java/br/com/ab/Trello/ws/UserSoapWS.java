package br.com.ab.Trello.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.dao.UserDao;
import br.com.ab.Trello.error.ErrorMessage;
import br.com.ab.Trello.error.WSObjectFault;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.User;

@WebService
@Stateless
public class UserSoapWS {

	@Inject
	UserDao userDao;

	@WebMethod(operationName = "addUser")
	@WebResult(name = "User")
	public boolean addUser(@WebParam(name = "New_User") @XmlElement(required = true, nillable = false) User user)
			throws WSObjectException {
		if (validateUser(user)) {
			userDao.addUser(user);
			return true;
		} else {
			throw new WSObjectException(new WSObjectFault(ErrorMessage.EMPTY_NULL_PARAMETERS));
		}
	}

	@WebMethod(operationName = "findUserById")
	@WebResult(name = "user")
	public User findUserById(@WebParam(name = "userId") @XmlElement(required = true, nillable = false) Integer userId)
			throws WSObjectException {
		if(validateUserId(userId)){
			return this.userDao.findUserById(userId);
		} else{
			throw new WSObjectException(new WSObjectFault(ErrorMessage.ID_NULL_MINOR_ZERO));
		}
		
	}

	@WebMethod(operationName="findAllUsers")
	@WebResult(name="listOfUsers")
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
