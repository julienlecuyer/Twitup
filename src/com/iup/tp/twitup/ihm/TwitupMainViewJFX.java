package com.iup.tp.twitup.ihm;

import java.awt.Component;

import javax.swing.JFileChooser;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.core.TwitupJFX;
import com.iup.tp.twitup.core.TwitupS;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TwitupMainViewJFX implements IMainView<IJFXView> {

	/**
	 * Fenetre du bouchon
	 */
	
	 GridPane root;
	 Scene scene;
	
	protected Stage mStage;
	
	/**
	 * Base de donénes de l'application.
	 */
	protected TwitupJFX ctrl;
	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;

	/**
	 *  Vue pour le login 
	 */
	protected LoginViewJFX logV = new LoginViewJFX();
	/**
	 * Constructeur.
	 * 
	 * @param database
	 *            , Base de données de l'application.
	 */

	public TwitupMainViewJFX(TwitupJFX ctrl, Stage s) {
		this.ctrl = ctrl;
	}

	/**
	 * Lance l'afficahge de l'IHM.
	 */
	public void showGUI(Stage s) {
		
	}

	/**
	 * Initialisation de l'IHM
	 */
	protected void initGUI() {


		// Création de la fenetre principale
		final GridPane root = new GridPane();

		final Scene scene = new Scene(root, 800, 600);
		

		ctrl.initLogin();
	}

	public void initCreate() {
		ctrl.initCreate();
	}

	public void refreshMenuLabel() {
	}

	@Override
	public void showView(IJFXView v) {
	}
	
	public void chooseFile(Component parent) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(parent);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			TwitupS.getProp().setProperty("EXCHANGE_DIRECTORY", chooser.getSelectedFile().getPath());
			PropertiesManager.writeProperties(TwitupS.getProp(), Constants.CONFIGURATION_FILE);
			System.out.println(chooser.getSelectedFile().getPath());
		}
	}
}
