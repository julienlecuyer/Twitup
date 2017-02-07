package com.iup.tp.twitup.core;

public interface ICreateCtrl {
	boolean addUser(String login, String mdp, String mdp2);
	
	void addObserver(ITwitupObs v);
	void notifyCreate();
}
