package com.iup.tp.twitup.ihm;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.core.TwitupJFX;
import com.iup.tp.twitup.core.TwitupS;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TwitupMainViewJFX implements IMainView<ISwingView> {

	/**
	 * Fenetre du bouchon
	 */
	
	final GridPane root;
	final Scene scene;
	
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

	public TwitupMainViewJFX(TwitupJFX ctrl, Stage stage) {
		this.ctrl = ctrl;
		this.mStage = stage;
	}

	/**
	 * Lance l'afficahge de l'IHM.
	 */
	public void showGUI() {
		
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
	public void showView(ISwingView v) {
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
