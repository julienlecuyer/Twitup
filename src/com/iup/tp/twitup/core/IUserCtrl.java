package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.User;

public interface IUserCtrl {

	void addObserver(ITwitupObs ctrl);

	void getUser();

	void modifLoginUser(String login);

}
