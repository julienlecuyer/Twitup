package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.iup.tp.twitup.core.ITwitCtrl;
import com.iup.tp.twitup.datamodel.Twit;

public class TwitView implements IView, ITwitObs{

	protected JPanel paneCreate;
	protected JPanel paneList;
	protected JPanel pane;
	protected ITwitCtrl observers;

	public TwitView() {
		createTwit();
		//notifyNeedListTwit();
		pane = new JPanel();
//		pane.setLayout(new GridBagLayout());
//		pane.add(paneCreate);
//		pane.add(paneList);

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

		bAddTwit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				notifyTwitCreate(tArea.getText());
			}
		});

	}
	
//	public void listTwits(List<Twit> twist) {
//		paneList = new JPanel();
//		Border border = BorderFactory.createTitledBorder("");
//		paneList.setBorder(border);
//		for ()
//			panel
//			Map<Twit,paneList> 
//	}
//	
	@Override
	public JComponent getComponent() {
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


	@Override
	public void listTwits(List<Twit> twits) {
		
	}

}


