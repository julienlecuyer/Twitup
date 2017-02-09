package com.iup.tp.twitup.ihm;

import java.awt.Component;


import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.core.TwitupJFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class TwitupMainViewJFX implements IMainView<IJFXView> {

	/**
	 * Fenetre du bouchon
	 */
	
	GridPane root;
	
	protected Stage mStage;
	protected Scene mScene;
	protected Group mGroup;
	protected MenuBar mMenuBar;
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
		mStage = s;
	}

	/**
	 * Lance l'afficahge de l'IHM.
	 */
	public void showGUI() {
		initGUI();
	}

	/**
	 * Initialisation de l'IHM
	 */
	protected void initGUI() {
		GridPane root = new GridPane();
		mScene = new Scene(root, 800, 600);
//		mStage.sizeToScene();
		mStage.setTitle("Test");
		mStage.setScene(mScene);
		mStage.show();
		initRoot(root);
	}

	protected void initRoot(GridPane root)
	{
		mMenuBar = this.createMenuBar();
		root.add(mMenuBar, 0, 0);
		GridPane.setHgrow(mMenuBar, Priority.ALWAYS);

		mGroup = new Group();
		GridPane.setHgrow(mGroup, Priority.ALWAYS);
		GridPane.setHalignment(mGroup, HPos.CENTER);
		GridPane.setVgrow(mGroup, Priority.ALWAYS);
		GridPane.setValignment(mGroup, VPos.CENTER);

		root.add(mGroup, 0, 1);
		ctrl.initLogin();
//		mGroup.getChildren().add(dummyLoginView);
	}

	protected void changeContainer() {
//		mGroup.getChildren().clear();
//		Node dummySecond = this.createDummySecondComponent();
//		mGroup.getChildren().add(dummySecond);
	}

	protected MenuBar createMenuBar() {
		MenuItem mHome = new MenuItem("Accueil	");
		MenuItem mApropos = new MenuItem("A propos");
		MenuItem mExit = new MenuItem("Quitter");
		Menu mParam = new Menu("Paramètres");
		MenuItem mFilechooser = new MenuItem("Choisir le fichier de configuration ...");
		
		MenuItem mnewUser = new MenuItem("Créer un nouveau compte ...");
		MenuItem mDeco = new MenuItem("Déconnexion");
		MenuItem mAccount = new MenuItem("Mon compte");
		
		mParam.getItems().addAll(mFilechooser);
		
		Menu mFichier = new Menu("Fichier");
		mFichier.getItems().addAll(mHome, mApropos,mParam, mExit);

		Menu mUser = new Menu("Utilisateur");
		mUser.getItems().addAll(mnewUser,mDeco,mAccount);

		MenuBar mb = new MenuBar();
		mb.getMenus().addAll(mFichier, mUser);
		
		mnewUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	initCreate();
            }
        });

		return mb;
	}

	public void initCreate() {
		ctrl.initCreate();
	}

	public void refreshMenuLabel() {
	}

	@Override
	public void showView(IJFXView v) {
		mGroup.getChildren().clear();
		mGroup.getChildren().add(v.getPane());
	}
	
	public void chooseFile(Component parent) {
	}
}
