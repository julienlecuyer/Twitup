package com.iup.tp.twitup;

import com.iup.tp.twitup.core.TwitupJFX;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe de lancement de l'application.
 * 
 * @author S.Lucas
 */
public class TwitupLauncherJFX extends Application {

	/**
	 * Launcher.
	 * 
	 * @param args
	 */

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		new TwitupJFX(arg0);
	}
	
    public static void main(String[] args) { 
        launch(args); 
    } 
}
