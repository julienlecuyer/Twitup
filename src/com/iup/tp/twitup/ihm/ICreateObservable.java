package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.core.ICreateCtrl;

public interface ICreateObservable {
	void addCreateCtrl(ICreateCtrl cc);
	void notifyCreate(String login, String nom, String mdp, String aPath);	
}