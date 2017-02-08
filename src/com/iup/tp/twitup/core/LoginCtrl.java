package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class LoginCtrl implements ILoginCtrl {
	protected IDatabase mDatabase;
	protected EntityManager mEntityManager;
	protected ITwitupObservateur obs;

	public LoginCtrl(IDatabase mDatabase, EntityManager mEntityManager) {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
	}

	@Override
	public void addObserver(ITwitupObservateur ctrl) {
		obs = ctrl;
	}

	@Override
	public boolean checkUser(String login, String mdp) {
		for(User u : mDatabase.getUsers()) {
			if(u.getUserTag().equals(login) && u.getUserPassword().equals(mdp)) {
				obs.setUserCo(u);
				notifyLogin();
				return true; 
			}
		}
		obs.setUserCo(null);
		return false;
	}

	@Override
	public void notifyLogin() {
		obs.userLogged();
	}
}