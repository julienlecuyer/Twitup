package com.iup.tp.twitup.ihm;


import com.iup.tp.twitup.core.ILoginCtrl;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LoginViewJFX implements IJFXView, ILoginObservable, ILoginView {
	protected ILoginCtrl observers;
	protected GridPane mGrid;
	
	public LoginViewJFX() {
		mGrid = new GridPane();
		mGrid.setAlignment(Pos.CENTER);
		mGrid.setHgap(10);
		mGrid.setVgap(10);
		TextField tlogin = new TextField();
		PasswordField tpass = new PasswordField();
		
		mGrid.add(new Label("Username :"), 0, 0);
		mGrid.add(tlogin, 1, 0);
		mGrid.add(new Label("Password :"), 0, 1);
		mGrid.add(tpass, 1, 1);

		Button btn = new Button("Login");
		btn.setOnAction((e) -> observers.checkUser(tlogin.getText(), tpass.getText()));
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		mGrid.add(hbBtn, 1, 3);
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
		return mGrid;
	}
}
