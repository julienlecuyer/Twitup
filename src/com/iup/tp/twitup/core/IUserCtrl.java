package com.iup.tp.twitup.core;

public interface IUserCtrl {

	void addObserver(ITwitupObs ctrl);

	void getUser();

	void modifLoginUser(String login);

	void modifAvatarUser(String avatarPath);

}
