package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.iup.tp.twitup.core.IUserCtrl;

public class UserView implements IView, IUserObs{

	protected JPanel pane;
	protected IUserCtrl observers;
	
	public UserView() {

		pane = new JPanel();
		pane.setPreferredSize(new Dimension(800,600));
		Border border = BorderFactory.createTitledBorder("Mon profil");
		pane.setBorder(border);
		pane.setLayout(new GridBagLayout());
		JLabel lNom = new JLabel("Nom : ");
		JLabel lTag = new JLabel("Tag : ");
		JLabel lMdp = new JLabel("Mot de passe : ");
		JTextField tNom = new JTextField();
		JTextField tTag = new JTextField();
		JPasswordField tMdp = new JPasswordField();

		pane.add(lNom, new GridBagConstraints(
				0, 0,
				2, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
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
		pane.add(lMdp, new GridBagConstraints(
				0, 2,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tNom, new GridBagConstraints(
				2, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tTag, new GridBagConstraints(
				2, 1,
				1, 1,
				10.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(tMdp, new GridBagConstraints(
				2, 2,
				1, 1,
				10.0, 1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		
//		bLogin.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				notifyLogin(tLogin.getText(), new String(tmdp.getPassword()));
//			}
//		});
	}
	
	
	@Override
	public JComponent getComponent() {
		return pane;
	}
	
	public void addUserCtrl(IUserCtrl cc) {
		observers = cc;
	}


}
