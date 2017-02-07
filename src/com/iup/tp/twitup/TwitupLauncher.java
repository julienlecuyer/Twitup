package com.iup.tp.twitup;

import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.mock.MockController;

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
		Twitup twitup = new Twitup();
		twitup.show();
		
		// JAVAFX
		MockController mockController = new MockController(twitup.getDatabase());
		mockController.startMock();
	}
}
