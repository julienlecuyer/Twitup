package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.iup.tp.twitup.core.ITwitCtrl;
import com.iup.tp.twitup.datamodel.Twit;

public class TwitView implements IView, ITwitObs{

	protected JPanel paneCreate;
	protected JScrollPane scrollPane;
	protected JPanel paneList;
	protected JPanel paneSearch;
	JTextField tSearch;
	JButton bSearch;

	protected ITwitCtrl observers;

	public TwitView() {
		paneSearch = new JPanel();
		paneSearch.setSize(new Dimension(400, 400));
		tSearch = new JTextField();
		bSearch = new JButton("Rechercher");
		tSearch.setPreferredSize(new Dimension(200, 20));
		
		paneSearch.add(tSearch, new GridBagConstraints(
				0, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		paneSearch.add(bSearch, new GridBagConstraints(
				1, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,0),
				0, 0
				));
		createTwit();
		paneList = new JPanel();
		scrollPane = new JScrollPane(paneList);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
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
				2, 1,
				1.0, 1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,20,0,20),
				0, 0
				));
		paneCreate.add(bAddTwit, new GridBagConstraints(
				1, 2,
				1, 1,
				0.3, 1,
				GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0,0,0,20),
				0, 0
				));
		paneCreate.setPreferredSize(new Dimension(600, 200));
		bAddTwit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				notifyTwitCreate(tArea.getText());
			}
		});
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				notifyNeedListTwit(tSearch.getText());
			}
		});
	}

	public void listTwits(ArrayList<Twit> twits, String search) {
		paneList.removeAll();
		paneList.setLayout(new GridBagLayout());
		// paneList.setPreferredSize(new Dimension(700, 800));
		// Parcours la liste
		// Si l'obj n'existe pas dans la liste --> ajoute dans la map et ajoute le graphic avec,
		// Si existe affiche dans le graphique
		int i = 0;
		for(Twit t : twits) {
			JPanel pt = new JPanel();
			// format date
			Date d=new Date(t.getEmissionDate());
	        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	        String dateText = df2.format(d);
			JLabel date = new JLabel(dateText);
			date.setFont(new Font("Arial", Font.ITALIC, 12));
			date.setForeground(Color.gray);
			JLabel content = new JLabel(t.getText());
			content.setFont(new Font("Arial", Font.PLAIN, 18));
			content.setForeground(Color.BLACK);

			Border bt = BorderFactory.createTitledBorder(null, '@'+t.getTwiter().getUserTag(), TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial",Font.ITALIC,13), Color.gray);
			pt.setBorder(bt);
			pt.setLayout(new GridBagLayout());
			pt.add(content, new GridBagConstraints(
					0, 1,
					1, 1,
					1.0, 1.0,
					GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL,
					new Insets(0,0,0,0),
					0, 0
					));
			pt.add(date, new GridBagConstraints(
					2,0,
					1, 1,
					0.01, 1.0,
					GridBagConstraints.EAST,
					GridBagConstraints.HORIZONTAL,
					new Insets(-20,0,0,0),
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
		paneList.revalidate();
		paneList.repaint();
	}


	@Override
	public JComponent getComponent() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		pane.setPreferredSize(new Dimension((int)(screenSize.width/1.7), (int)(screenSize.height/1.5)));
		pane.add(paneSearch, new GridBagConstraints(
				0, 0,
				1, 1,
				1.0, 1.0,
				GridBagConstraints.NORTH,
				GridBagConstraints.BOTH,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(paneCreate, new GridBagConstraints(
				0, 1,
				1, 1,
				1.0, 5.0,
				GridBagConstraints.NORTH,
				GridBagConstraints.BOTH,
				new Insets(0,0,0,0),
				0, 0
				));
		pane.add(scrollPane, new GridBagConstraints(
				0, 2,
				1, 1,
				1.0, 20.0,
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
	public void notifyNeedListTwit(String search) {
		// TODO Auto-generated method stub
		observers.listTwitCtrl(search);
	}

}


