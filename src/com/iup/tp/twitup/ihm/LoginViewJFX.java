package com.iup.tp.twitup.ihm;


import com.iup.tp.twitup.core.ILoginCtrl;

import javafx.scene.layout.Pane;

public class LoginViewJFX implements IJFXView, ILoginObservable, ILoginView {

	protected ILoginCtrl observers;

	
	public LoginViewJFX() {
	}


	@Override
	public void addLoginCtrl(ILoginCtrl cc) {
		observers = cc;
	}

	@Override
	public void notifyLogin(String login, String mdp) {
		boolean res = observers.checkUser(login, mdp);
		if(res == false) {
//			lError.setVisible(true);
		} else {
//			lError.setVisible(false);
		}
	}


	@Override
	public Pane getPane() {
		// TODO Auto-generated method stub
		return null;
	}
}
