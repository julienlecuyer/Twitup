package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.IUserObservable;

public class UserCtrl implements IUserCtrl {
	protected IDatabase mDatabase;
	protected EntityManager mEntityManager;
	protected ITwitupObservateur obs;
	
	public UserCtrl(IDatabase mDatabase, EntityManager mEntityManager) {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
	}

	@Override
	public void addObserver(ITwitupObservateur ctrl) {
		obs = ctrl;
	}

	@Override
	public void getUser(IUserObservable o) {
		o.getUser(obs.getUserCo());
	}
	
	@Override
	public void modifLoginUser(String login) {
		obs.getUserCo().setName(login);
		mEntityManager.sendUser(obs.getUserCo());
	}

	@Override
	public void modifAvatarUser(String avatarPath) {
		obs.getUserCo().setAvatarPath(avatarPath);
		mEntityManager.sendUser(obs.getUserCo());
	}
}
