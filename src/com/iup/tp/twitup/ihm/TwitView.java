package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.iup.tp.twitup.core.ITwitCtrl;
import com.iup.tp.twitup.datamodel.Twit;

public class TwitView implements IView, ITwitObs{

	protected JPanel paneCreate;
	protected JScrollPane scrollPane;
	protected JPanel paneList;
	protected ITwitCtrl observers;

	public TwitView() {
		createTwit();
		paneList = new JPanel();
		scrollPane = new JScrollPane(paneList);
		Border border = BorderFactory.createTitledBorder("Actualités");
		scrollPane.setBorder(border);
	}

	public void createTwit() {
		paneCreate = new JPanel();
		Border border = BorderFactory.createTitledBorder("");
		paneCreate.setBorder(border);
		paneCreate.setLayout(new GridBagLayout());
		JLabel lTwit = new JLabel("Quoi de neuf ?");
		JTextArea tArea = new JTextArea(3,1);
		JButton bAddTwit = new JButton("Poster");

		paneCreate.add(lTwit, new GridBagConstraints(
				0, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		paneCreate.add(tArea, new GridBagConstraints(
				0, 1,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,20,0,20),
				0, 0
				));
		paneCreate.add(bAddTwit, new GridBagConstraints(
				0, 2,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,100,0,0),
				0, 0
				));
		paneCreate.setPreferredSize(new Dimension(600, 200));
		bAddTwit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				notifyTwitCreate(tArea.getText());
			}
		});
	}

	public void listTwits(ArrayList<Twit> twits) {
		paneList.removeAll();
		paneList.setLayout(new GridBagLayout());
		// paneList.setPreferredSize(new Dimension(700, 800));
		// Parcours la liste
		// Si l'obj n'existe pas dans la liste --> ajoute dans la map et ajoute le graphic avec,
		// Si existe affiche dans le graphique
		int i = 0;
		for(Twit t : twits) {
			JPanel pt = new JPanel();
			Border bt = BorderFactory.createTitledBorder(t.getTwiter().getUserTag());
			pt.setBorder(bt);
			pt.setLayout(new GridBagLayout());
			pt.add(new JLabel(t.getText()), new GridBagConstraints(
					0, 0,
					1, 1,
					1.0, 1.0,
					GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL,
					new Insets(0,0,0,0),
					0, 0
					));
			paneList.add(pt, new GridBagConstraints(
					0, i,
					1, 1,
					1.0, 1.0,
					GridBagConstraints.NORTH,
					GridBagConstraints.HORIZONTAL,
					new Insets(0,0,0,0),
					0, 0
					));
			i++;
		}
	}


	@Override
	public JComponent getComponent() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		pane.setPreferredSize(new Dimension((int)(screenSize.width/2), (int)(screenSize.height/1.5)));
		pane.add(paneCreate, new GridBagConstraints(
				0, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.NORTH,
				GridBagConstraints.BOTH,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(scrollPane, new GridBagConstraints(
				0, 1,
				1, 1,
				1.0, 10.0,
				GridBagConstraints.SOUTH,
				GridBagConstraints.BOTH,
				new Insets(0,0,0,0),
				0, 0
				));
		return pane;
	}


	@Override
	public void addTwitCtrl(ITwitCtrl cc) {
		observers = cc;
	}


	@Override
	public void notifyTwitCreate(String twit) {
		observers.CreateTwit(twit);
	}


	@Override
	public void notifyNeedListTwit() {
		// TODO Auto-generated method stub
		observers.listTwitCtrl();
	}

}


