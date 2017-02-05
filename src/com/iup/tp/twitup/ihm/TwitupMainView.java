package com.iup.tp.twitup.ihm;

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
import com.iup.tp.twitup.core.Twitup;

public class TwitupMainView {

	/**
	 * Fenetre du bouchon
	 */
	protected JFrame mFrame;
	protected JPanel mPanel;
	/**
	 * Base de donénes de l'application.
	 */
	protected Twitup ctrl;
	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;

	protected JMenuBar mMenuBar;
	protected JMenu mFichier;
	protected JMenu mUser;
	protected JMenuItem mExit;
	protected JMenuItem mApropos;
	protected JMenu mParam;
	protected JMenuItem mFilechooser;
	protected JMenuItem mnewUser;
	protected JMenuItem mHome;
	protected JMenuItem mDeco;
	protected JMenuItem mAccount;
	/**
	 *  Vue pour le login 
	 */
	protected LoginView logV = new LoginView();
	
	/**
	 * Constructeur.
	 * 
	 * @param database
	 *            , Base de données de l'application.
	 */

	public TwitupMainView(Twitup ctrl) {
		this.ctrl = ctrl;
	}

	/**
	 * Lance l'afficahge de l'IHM.
	 */
	public void showGUI() {
		// Init auto de l'IHM au cas ou ;)
		if (mFrame == null) {
			this.initGUI();
		}
		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage
				JFrame frame = TwitupMainView.this.mFrame;
				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation((screenSize.width - frame.getWidth()) / 6,
						(screenSize.height - frame.getHeight()) / 4);

				// Affichage
				TwitupMainView.this.mFrame.setVisible(true);

				// TwitupMainView.this.mFrame.pack();
			}
		});
	}

	/**
	 * Initialisation de l'IHM
	 */
	protected void initGUI() {
		if (Twitup.getProp().getProperty("EXCHANGE_DIRECTORY") == null) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = chooser.showOpenDialog(mFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				Twitup.getProp().setProperty("EXCHANGE_DIRECTORY", chooser.getSelectedFile().getPath());
				PropertiesManager.writeProperties(Twitup.getProp(), Constants.CONFIGURATION_FILE);
				System.out.println(chooser.getSelectedFile().getPath());
			}
		}
		// Création de la fenetre principale
		mFrame = new JFrame("Baloss Twitter");
		mFrame.setSize(700, 400);
		mFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(Constants.ICON_PATH)));
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setLayout(new GridBagLayout());
		mMenuBar = new JMenuBar();
		mFichier = new JMenu("Fichier");
		mUser = new JMenu();
		if(ctrl.getUserCo() != null) mUser.setText("Utilisateur "+ ctrl.getUserCo().getName());
		else mUser.setText("Utilisateur invité");
		mExit = new JMenuItem("Quitter");
		mExit.setIcon(new ImageIcon(getClass().getResource(Constants.EXIT_ICON_PATH)));
		mApropos = new JMenuItem("A propos");
		mApropos.setIcon(new ImageIcon(getClass().getResource(Constants.APROPOS_ICON_PATH)));
		mParam = new JMenu("Paramètres");
		mFilechooser = new JMenuItem("Choisir le fichier de configuration ...");
		mParam.add(mFilechooser);

		mnewUser = new JMenuItem("Créer un nouveau compte ...");
		mAccount = new JMenuItem("Mon compte");
		mHome = new JMenuItem("Accueil");
		mDeco = new JMenuItem("Déconnexion");
		mFichier.add(mHome);
		mFichier.add(mApropos);
		mFichier.add(mParam);
		mFichier.add(mExit);
		mUser.add(mnewUser);
		mUser.add(mAccount);
		mUser.add(mDeco);
		
		mMenuBar.add(mFichier);
		mMenuBar.add(mUser);
		mFrame.setJMenuBar(mMenuBar);
		mPanel = new JPanel();
		mFrame.add(mPanel);
		mnewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				initCreate();
			}
		});
		mExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				ctrl.exitFrame();
			}
		});
		mApropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				ctrl.showApropos(mFrame);
			}
		});
		mFilechooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				ctrl.chooseFile(mFrame);
			}
		});
		
		mHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				ctrl.initHome();
			}
		});
		
		ctrl.initLogin();
	}

	public void initCreate() {
		ctrl.initCreate();
	}

	public void showView(IView v) {
		mPanel.removeAll();
		mPanel.add(v.getComponent(), new GridBagConstraints(
				1, 1,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.CENTER,
				new Insets(0,0,0,0),
				0, 0
				));
		mPanel.revalidate();
		mPanel.repaint();
	}
	
	public void refreshMenuLabel() {
		if(ctrl.getUserCo() != null) mUser.setText("Utilisateur "+ ctrl.getUserCo().getName());
		else mUser.setText("Utilisateur invité");
	}
}
