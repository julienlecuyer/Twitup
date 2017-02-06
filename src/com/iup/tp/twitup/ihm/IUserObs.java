package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.core.IUserCtrl;

public interface IUserObs {
	void addUserCtrl(IUserCtrl cc);

	void notifyModifNom(String login);

}
