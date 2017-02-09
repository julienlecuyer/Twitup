package com.iup.tp.twitup;

import com.iup.tp.twitup.core.TwitupS;

/**
 * Classe de lancement de l'application.
 * 
 * @author S.Lucas
 */
public class TwitupLauncher {

	/**
	 * Launcher.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TwitupS twitup = new TwitupS();
		twitup.show();
		
//		JAVAFX
//		MockController mockController = new MockController(twitup.getDatabase());
//		mockController.startMock();
	}
}
