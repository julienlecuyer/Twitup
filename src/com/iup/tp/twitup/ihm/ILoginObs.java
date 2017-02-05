package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.core.ILoginCtrl;

public interface ILoginObs {
	void addLoginCtrl(ILoginCtrl cc);
	void notifyLogin(String login, String mdp);	
}