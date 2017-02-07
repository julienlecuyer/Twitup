package com.iup.tp.twitup.core;

import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ITwitObs;
import com.iup.tp.twitup.ihm.IUserObs;
import com.iup.tp.twitup.ihm.TwitView;
import com.iup.tp.twitup.ihm.UserView;

public class UserCtrl implements IUserCtrl{
	protected IDatabase mDatabase;
	protected UserView view;
	protected EntityManager mEntityManager;
	protected ITwitupObs obs;
	
	public UserCtrl(IDatabase mDatabase, EntityManager mEntityManager, UserView v) {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
		this.view = v;
		v.addUserCtrl(this);
	}

	@Override
	public void addObserver(ITwitupObs ctrl) {
		obs = ctrl;
	}

	@Override
	public void getUser() {
		view.getUser(obs.getUserCo());
	}
	
	@Override
	public void modifLoginUser(String login) {
		obs.getUserCo().setName(login);
		mEntityManager.sendUser(obs.getUserCo());
	}
	
}
