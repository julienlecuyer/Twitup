package com.iup.tp.twitup.core;

public interface ICreateCtrl {
	boolean addUser(String login, String mdp);
	
	void addObserver(ITwitupObs v);
	void notifyCreate();
}
