package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ISwingView;

public interface ITwitupObservateur {
	void userCreated();
	void userLogged();
	User getUserCo();
	void setUserCo(User u);
	void twitCreated(ISwingView view);
}
