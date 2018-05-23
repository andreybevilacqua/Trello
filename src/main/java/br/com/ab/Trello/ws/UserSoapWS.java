package br.com.ab.Trello.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.dao.ApplicationUserDao;
import br.com.ab.Trello.error.ErrorMessage;
import br.com.ab.Trello.error.WSObjectFault;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.ApplicationUser;

@WebService
@Stateless
public class UserSoapWS {

	@Inject
    ApplicationUserDao applicationUserDao;

	@WebMethod(operationName = "addUser")
	@WebResult(name = "ApplicationUser")
	public boolean addUser(@WebParam(name = "New_User") @XmlElement(required = true, nillable = false) ApplicationUser applicationUser)
			throws WSObjectException {
		if (validateUser(applicationUser)) {
			applicationUserDao.addUser(applicationUser);
			return true;
		} else {
			throw new WSObjectException(new WSObjectFault(ErrorMessage.EMPTY_NULL_PARAMETERS));
		}
	}

	@WebMethod(operationName = "findUserById")
	@WebResult(name = "user")
	public ApplicationUser findUserById(@WebParam(name = "userId") @XmlElement(required = true, nillable = false) Integer userId)
			throws WSObjectException {
		if(validateUserId(userId)){
			return this.applicationUserDao.findUserById(userId);
		} else{
			throw new WSObjectException(new WSObjectFault(ErrorMessage.ID_NULL_MINOR_ZERO));
		}
		
	}

	@WebMethod(operationName="findAllUsers")
	@WebResult(name="listOfUsers")
	public List<ApplicationUser> findAllUser() {
		return this.applicationUserDao.findAllUser();
	}

	public boolean validateUser(ApplicationUser applicationUser) {
		boolean result = false;
		if (!applicationUser.equals(null)){
			if(!applicationUser.getLogin().equals(null) && !applicationUser.getPassword().equals(null)) {
				if (!applicationUser.getLogin().equals("") && !applicationUser.getPassword().equals("") && !applicationUser.getLogin().equals("?")){
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
