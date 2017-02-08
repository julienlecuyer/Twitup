package com.iup.tp.twitup.core;

public interface ILoginCtrl {
	boolean checkUser(String login, String mdp);
	
	void addObserver(ITwitupObservateur v);
	void notifyLogin();
}
