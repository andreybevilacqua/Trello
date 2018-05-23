package br.com.ab.Trello.controller;


import br.com.ab.Trello.dao.ApplicationUserDao;
import br.com.ab.Trello.model.ApplicationUser;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ApplicationUserController {

    @Inject
    ApplicationUserDao applicationUserDao;

    public ApplicationUserController(){ }
	
    public ApplicationUser findUserById(Integer userId){ return applicationUserDao.findUserById(userId); }

}
