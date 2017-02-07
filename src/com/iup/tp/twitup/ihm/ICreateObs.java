package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.core.ICreateCtrl;

public interface ICreateObs {
	void addCreateCtrl(ICreateCtrl cc);
	void notifyCreate(String login, String nom, String mdp);	
}