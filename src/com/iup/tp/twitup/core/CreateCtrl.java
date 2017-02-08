package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class CreateCtrl implements ICreateCtrl {
	protected IDatabase mDatabase;
	protected EntityManager mEntityManager;
	protected ITwitupObservateur obs;
	
	public CreateCtrl(IDatabase mDatabase, EntityManager mEntityManager) {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
	}

	public boolean addUser(String login, String nom, String mdp, String aPath) {
		Set<User> list = mDatabase.getUsers();
		boolean exist = false;
		for(User u : list) {
			if(u.getUserTag().equals(login)) {
				exist = true;
			}
		}
		if(!exist) {
			User mnewUser = new User(UUID.randomUUID(), login, mdp, nom, new HashSet<String>(), aPath);
			this.mDatabase.addUser(mnewUser);
			this.mEntityManager.sendUser(mnewUser);
			notifyCreate();	
			return true;
		}
		return false;
	}

	@Override
	public void addObserver(ITwitupObservateur ctrl) {
		obs = ctrl;
	}

	@Override
	public void notifyCreate() {
		obs.userCreated();
	}
}