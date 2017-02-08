package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.core.IUserCtrl;
import com.iup.tp.twitup.datamodel.User;

public interface IUserObservable {
	void addUserCtrl(IUserCtrl cc);

	void notifyModifNom(String login);

	void notifyModifAvatar();

	void getUser(User userCo);

}
