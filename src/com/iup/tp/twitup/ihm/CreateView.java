package com.iup.tp.twitup.ihm;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.iup.tp.twitup.core.ICreateCtrl;

public class CreateView implements IView, ICreateObs {

	protected JPanel pane;
	protected ICreateCtrl observers;

	public CreateView() {

		pane = new JPanel();
		Border border = BorderFactory.createTitledBorder("Créer un compte");
		pane.setBorder(border);
		pane.setLayout(new GridBagLayout());
		JLabel login = new JLabel("Nom d'utilisateur : ");
		JLabel mdp = new JLabel("Mot de passe : ");
		JLabel mdp2 = new JLabel("Confirmer le mot de passe : ");
		JTextField tLogin = new JTextField();
		JPasswordField tmdp = new JPasswordField();
		JPasswordField tmdp2 = new JPasswordField();
		JButton bCreate = new JButton("Créer");

		pane.add(login, new GridBagConstraints(
			0, 0,
			1, 1,
			1.0, 1.0,
			GridBagConstraints.WEST,
			GridBagConstraints.HORIZONTAL,
			new Insets(0,0,0,0),
			0, 0
		));
		pane.add(mdp, new GridBagConstraints(
				0, 1,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
		));
		pane.add(mdp2, new GridBagConstraints(
				0, 2,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
		));
		pane.add(tLogin, new GridBagConstraints(
				1, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
		));
		pane.add(tmdp, new GridBagConstraints(
				1, 1,
				1, 1,
				10.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
		));
		pane.add(tmdp2, new GridBagConstraints(
				1, 2,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
		));
		pane.add(bCreate, new GridBagConstraints(
				1, 3,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
		));

		pane.setPreferredSize(new Dimension(500, 200));
		
		bCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(new String(tmdp.getPassword()).equals(new String(tmdp2.getPassword())))
					notifyCreate(tLogin.getText(), new String(tmdp.getPassword()));
				else
					JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		});

	}

	@Override
	public JComponent getComponent() {
		return pane;
	}

	@Override
	public void addCreateCtrl(ICreateCtrl cc) {
		observers = cc;
	}

	@Override
	public void notifyCreate(String login, String mdp) {
		if(!observers.addUser(login, mdp)) {
			JOptionPane.showMessageDialog(null, "Tag @"+login +" non disponible", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}