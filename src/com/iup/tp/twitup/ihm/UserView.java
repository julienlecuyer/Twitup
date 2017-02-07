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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.iup.tp.twitup.core.IUserCtrl;
import com.iup.tp.twitup.datamodel.User;

public class UserView implements IView, IUserObs{

	protected JPanel pane;
	protected IUserCtrl observers;
	
	JTextField tNom;
	JTextField tTag;
	JPasswordField tMdp;
	JButton bSubmit;
	
	public UserView() {

		pane = new JPanel();
		pane.setPreferredSize(new Dimension(800,600));
		Border border = BorderFactory.createTitledBorder("Mon profil");
		pane.setBorder(border);
		pane.setLayout(new GridBagLayout());
		JLabel lNom = new JLabel("Nom : ");
		JLabel lTag = new JLabel("Tag : ");
		JLabel lMdp = new JLabel("Mot de passe : ");
		tNom = new JTextField();
		
		tTag = new JTextField();
		tTag.setEnabled(false);
		
		tMdp = new JPasswordField();
		tMdp.setEnabled(false);
		
		bSubmit = new JButton();
		bSubmit.setText("Modifier");

		
		pane.add(lNom, new GridBagConstraints(
				0, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tNom, new GridBagConstraints(
				1, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(bSubmit, new GridBagConstraints(
				2, 0,
				1, 1,
				0.1, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,500,0,0),
				0, 0
				));
		pane.add(lTag, new GridBagConstraints(
				0, 1,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tTag, new GridBagConstraints(
				1, 1,
				1, 1,
				10.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(lMdp, new GridBagConstraints(
				0, 2,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tMdp, new GridBagConstraints(
				1, 2,
				1, 1,
				10.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));

	
		
		bSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				notifyModifNom(tNom.getText());
			}
		});
	}
	
	
	@Override
	public JComponent getComponent() {
		return pane;
	}
	
	public void addUserCtrl(IUserCtrl cc) {
		observers = cc;
	}

	public void getUser(User u) {
		tNom.setText(u.getName());
		tTag.setText(u.getUserTag());
		tMdp.setText(u.getUserPassword());
	}
	
	@Override
	public void notifyModifNom(String login) {
		System.out.println("Login de l'user changé : "+login);
		observers.modifLoginUser(login);
	}
}
