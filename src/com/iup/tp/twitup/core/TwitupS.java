package com.iup.tp.twitup.core;

import java.awt.Component;
import java.io.File;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.datamodel.ConsoleDatabaseObserver;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.CreateViewS;
import com.iup.tp.twitup.ihm.ISwingView;
import com.iup.tp.twitup.ihm.IView;
import com.iup.tp.twitup.ihm.LoginViewS;
import com.iup.tp.twitup.ihm.TwitViewS;
import com.iup.tp.twitup.ihm.TwitupMainViewS;
import com.iup.tp.twitup.ihm.TwitupMock;
import com.iup.tp.twitup.ihm.UserViewS;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class TwitupS implements ITwitupObservateur {
	/**
	 * Base de données.
	 */
	protected CreateCtrl createCtrl;
	protected LoginCtrl loginCtrl;
	protected TwitCtrl twitCtrl;
	protected UserCtrl userCtrl;

	protected User userCo;
	
	protected IDatabase mDatabase;

	protected IDatabaseObserver mDatabaseObserver;
	
	protected static Properties prop;
	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected TwitupMainViewS mMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = false;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	/**
	 * Constructeur.
	 */
	public TwitupS() {
		userCo = null;
		prop = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation de la base de données
		this.initDatabase();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		}

		// Initialisation de l'IHM
		this.initGui();

		// Initialisation du répertoire d'échange
		this.initDirectory();
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel(prop.getProperty("UI_CLASS_NAME"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		mMainView = new TwitupMainViewS(this);
		mMainView.showGUI();
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
		this.initDirectory(prop.getProperty("EXCHANGE_DIRECTORY"));
	}

	/**
	 * Indique si le fichier donné est valide pour servire de répertoire
	 * d'échange
	 * 
	 * @param directory
	 *            , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du mode bouchoné de l'application
	 */
	protected void initMock() {
		TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
		mock.showGUI();
	}

	/**
	 * Initialisation de la base de données
	 */
	protected void initDatabase() {
		mDatabase = new Database();
		mDatabaseObserver = new ConsoleDatabaseObserver();
		mDatabase.addObserver(mDatabaseObserver);
		mEntityManager = new EntityManager(mDatabase);
	}

	/**
	 * Initialisation du répertoire d'échange.
	 * 
	 * @param directoryPath
	 */
	public void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}


	public static Properties getProp() {
		return prop;
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

	public void showApropos() {
		JOptionPane.showMessageDialog(null, "Baloss Twitter v0\nDévelopper : Julien Lécuyer", "A propos", JOptionPane.INFORMATION_MESSAGE);
	}

	public void exitFrame() {
		System.exit(0);
	}

	public void initCreate() {
		CreateViewS v =  new CreateViewS();
		createCtrl = new CreateCtrl(mDatabase, mEntityManager);
		v.addCreateCtrl(createCtrl);
		createCtrl.addObserver(this);
		mMainView.showView(v);
	}

	public void initLogin() {
		LoginViewS v =  new LoginViewS();
		loginCtrl = new LoginCtrl(mDatabase, mEntityManager);
		v.addLoginCtrl(loginCtrl);
		loginCtrl.addObserver(this);
		mMainView.showView(v);
	}
	
	@Override
	public void userCreated() {
		System.out.println("Connection vue");
		initLogin();
	}

	@Override
	public User getUserCo() {
		return userCo;
	}

	@Override
	public void setUserCo(User u) {
		this.userCo = u;
	}

	@Override
	public void userLogged() {
		TwitViewS v = new TwitViewS();
		twitCtrl = new TwitCtrl(mDatabase, mEntityManager, v);
		mDatabase.addObserver(twitCtrl);
		twitCtrl.addObserver(this);
		twitCtrl.listTwitCtrl(null);
		mMainView.showView(v);
		System.out.println("user logged");
		mMainView.refreshMenuLabel();
	}

	public void initHome() {
		if(userCo != null) {
			userLogged();
		} else {
			initLogin();
		}
	}
	
	public void twitCreated(IView view) {
		mMainView.showView((ISwingView) view);
	}
	
	public void initAccount() {
		UserViewS v = new UserViewS();
		userCtrl = new UserCtrl(mDatabase, mEntityManager);
		v.addUserCtrl(userCtrl);
		userCtrl.addObserver(this);
		userCtrl.getUser(v);
		mMainView.showView(v);
	}
	
	public void decoUser() {
		userCo = null;
		mMainView.refreshMenuLabel();
		initLogin();
	}
	
	public IDatabase getDatabase() {
		// TODO Auto-generated method stub
		return mDatabase;
	}
}