package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.iup.tp.twitup.core.ICreateCtrl;

public class CreateViewS implements ISwingView, ICreateObservable, ICreateView {

	protected JPanel pane;
	protected ICreateCtrl observers;
	protected String avatarPath = "";
	
	public CreateViewS() {

		pane = new JPanel();
		Border border = BorderFactory.createTitledBorder("Créer un compte");
		pane.setBorder(border);
		pane.setLayout(new GridBagLayout());
		JLabel tag = new JLabel("Tag :");
		JLabel avatar = new JLabel("Avatar :");
		JLabel nom = new JLabel("Nom :");
		JLabel mdp = new JLabel("Mot de passe :");
		JLabel mdp2 = new JLabel("Confirmer le mot de passe :");
		JTextField tTag = new JTextField();
		JTextField tNom = new JTextField();
		JPasswordField tmdp = new JPasswordField();
		JPasswordField tmdp2 = new JPasswordField();
		JButton bCreate = new JButton("Créer");
		JButton bAvatar = new JButton("Choisir un fichier ...");
		pane.add(tag, new GridBagConstraints(
				0, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tTag, new GridBagConstraints(
				1, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(avatar, new GridBagConstraints(
				0, 1,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(bAvatar, new GridBagConstraints(
				1, 1,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(nom, new GridBagConstraints(
				0, 2,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tNom, new GridBagConstraints(
				1, 2,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(mdp, new GridBagConstraints(
				0, 3,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tmdp, new GridBagConstraints(
				1, 3,
				1, 1,
				10.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(mdp2, new GridBagConstraints(
				0, 4,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tmdp2, new GridBagConstraints(
				1, 4,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(bCreate, new GridBagConstraints(
				1, 5,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));

		pane.setPreferredSize(new Dimension(500, 200));

		tmdp2.addKeyListener(new java.awt.event.KeyAdapter() {  
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(new String(tmdp.getPassword()).equals(new String(tmdp2.getPassword())))
						notifyCreate(tTag.getText(), tNom.getText(), new String(tmdp.getPassword()), avatarPath);
					else
						JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		bCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(new String(tmdp.getPassword()).equals(new String(tmdp2.getPassword())))
					notifyCreate(tTag.getText(), tNom.getText(), new String(tmdp.getPassword()), avatarPath);
				else
					JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		});
		bAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "jpeg", "png", "gif");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(pane);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					bAvatar.setText(chooser.getSelectedFile().getPath());
					avatarPath = chooser.getSelectedFile().getPath();
				}
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
	public void notifyCreate(String login, String nom, String mdp, String aPath) {
		if(!observers.addUser(login, nom, mdp, aPath)) {
			JOptionPane.showMessageDialog(null, "Tag @"+login +" non disponible", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}