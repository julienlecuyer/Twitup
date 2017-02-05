package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.User;

public interface ITwitupObs {
	void userCreated();
	void userLogged();
	User getUserCo();
	void setUserCo(User u);
}
