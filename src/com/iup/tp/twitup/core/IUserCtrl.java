package com.iup.tp.twitup.core;

import com.iup.tp.twitup.ihm.IUserObservable;

public interface IUserCtrl {

	void addObserver(ITwitupObservateur ctrl);

	void getUser(IUserObservable o);

	void modifLoginUser(String login);

	void modifAvatarUser(String avatarPath);

}
