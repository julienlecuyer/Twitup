package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IView;

public interface ITwitupObservateur {
	void userCreated();
	void userLogged();
	User getUserCo();
	void setUserCo(User u);
	void twitCreated(IView view);
}
