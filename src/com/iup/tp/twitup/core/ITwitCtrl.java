package com.iup.tp.twitup.core;

public interface ITwitCtrl {
	boolean CreateTwit(String twit);
	
	void addObserver(ITwitupObs v);
	void notifyTwitCreated();

	void listTwitCtrl(String search);
}
