package com.iup.tp.twitup.core;

public interface ITwitCtrl {
	boolean CreateTwit(String twit);
	
	void addObserver(ITwitupObservateur v);

	void listTwitCtrl(String search);

	void notifyTwitCreated();
}