package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.iup.tp.twitup.core.ILoginCtrl;

public class LoginView implements IView, ILoginObs {

	protected JPanel pane;
	protected ILoginCtrl observers;
	protected JLabel lError;
	
	public LoginView() {

		pane = new JPanel();
		Border border = BorderFactory.createTitledBorder("Se connecter");
		pane.setBorder(border);
		pane.setLayout(new GridBagLayout());
		JLabel bienvenue = new JLabel("Bienvenue sur Baloss Twitter");
		JLabel login = new JLabel("Nom d'utilisateur : ");
		JLabel mdp = new JLabel("Mot de passe : ");
		JTextField tLogin = new JTextField();
		JPasswordField tmdp = new JPasswordField();
		JButton bLogin = new JButton("Se connecter");
		lError = new JLabel();
		lError.setForeground(Color.RED);
		lError.setText("Utilisateur/Mot de passe incorrect");
		lError.setVisible(false);

		pane.add(bienvenue, new GridBagConstraints(
				0, 0,
				2, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(login, new GridBagConstraints(
				0, 1,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(mdp, new GridBagConstraints(
				0, 2,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tLogin, new GridBagConstraints(
				2, 1,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tmdp, new GridBagConstraints(
				2, 2,
				1, 1,
				10.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(bLogin, new GridBagConstraints(
				2, 4,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(lError, new GridBagConstraints(
				0, 4,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));

		pane.setPreferredSize(new Dimension(500, 200));
		bLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				notifyLogin(tLogin.getText(), new String(tmdp.getPassword()));
			}
		});
	}

	@Override
	public JComponent getComponent() {
		return pane;
	}

	@Override
	public void addLoginCtrl(ILoginCtrl cc) {
		observers = cc;
	}

	@Override
	public void notifyLogin(String login, String mdp) {
		boolean res = observers.checkUser(login, mdp);
		if(res == false) {
			lError.setVisible(true);
		} else {
			lError.setVisible(false);
		}
	}
}
