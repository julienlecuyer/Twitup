package com.iup.tp.twitup.ihm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.iup.tp.twitup.core.ITwitCtrl;
import com.iup.tp.twitup.datamodel.Twit;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TwitViewJFX implements IJFXView, ITwitObservable, ITwitView {

	protected ITwitCtrl observers;
	protected GridPane mGrid;
	protected GridPane listPane;

	public TwitViewJFX() {
		mGrid = new GridPane();
		listPane = new GridPane();
		mGrid.setAlignment(Pos.CENTER);
		mGrid.setHgap(10);
		mGrid.setVgap(10);
		TextField tsearch = new TextField();
		
		mGrid.add(tsearch, 0, 0);

		Button btn = new Button("Rechercher");
		//		btn.setOnAction((e) -> observers.checkUser(tlogin.getText(), tpass.getText()));
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.TOP_RIGHT);
		hbBtn.getChildren().add(btn);
		mGrid.add(hbBtn, 1, 0);


		createTwit();
		//		paneList = new JPanel();
		//		scrollPane = new JScrollPane(paneList);
		//		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		//		Border border = BorderFactory.createTitledBorder("Actualités");
		//		scrollPane.setBorder(border);
	}

	public void createTwit() {
		mGrid.add(new Label("Quoi de neuf ?"), 0, 1);
		TextArea tcreate = new TextArea();
		mGrid.add(tcreate, 0, 1);
		Button btncreate = new Button("Poster");
		btncreate.setOnAction((e) -> notifyTwitCreate(tcreate.getText()));
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btncreate);
		mGrid.add(hbBtn,0, 2);
	}

	public void listTwits(ArrayList<Twit> twits, String search) {
		int i = 0;
		for(Twit t : twits) {
			GridPane twitPane = new GridPane();
			// format date
			Date d = new Date(t.getEmissionDate());
	        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	        String dateText = df2.format(d);
	        Label userN = new Label("@"+t.getTwiter().getUserTag());
	        Label content = new Label(t.getText());
			Label date = new Label(dateText);
			//date.setFont(new Font("Arial", 12));
			date.setTextFill(Color.web("#0076a3"));
			date.setFont(new Font("Arial", 13));
			GridPane.setHalignment(date, HPos.RIGHT);
			userN.setTextFill(Color.GREY);
			userN.setFont(new Font("Arial", 14));
			content.setFont(new Font("Arial", 17));

			twitPane.add(userN, 0, 0);
			twitPane.add(content, 0, 1 );
			twitPane.add(date, 1, 0);
			listPane.add(twitPane, 0, i);
			i++;
		}
	}


	@Override
	public Pane getPane() {
		mGrid.add(listPane, 0, 3);
		//		JPanel pane = new JPanel();
		//		pane.setLayout(new GridBagLayout());
		//		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		//		pane.setPreferredSize(new Dimension((int)(screenSize.width/1.7), (int)(screenSize.height/1.5)));
		//
		//		pane.add(paneSearch, new GridBagConstraints(
		//				0, 0,
		//				1, 1,
		//				1.0, 1.0,
		//				GridBagConstraints.NORTH,
		//				GridBagConstraints.BOTH,
		//				new Insets(0,0,0,0),
		//				0, 0
		//				));
		//		pane.add(paneCreate, new GridBagConstraints(
		//				0, 1,
		//				1, 1,
		//				1.0, 5.0,
		//				GridBagConstraints.NORTH,
		//				GridBagConstraints.BOTH,
		//				new Insets(0,0,0,0),
		//				0, 0
		//				));
		//		pane.add(scrollPane, new GridBagConstraints(
		//				0, 2,
		//				1, 1,
		//				1.0, 20.0,
		//				GridBagConstraints.SOUTH,
		//				GridBagConstraints.BOTH,
		//				new Insets(0,0,0,0),
		//				0, 0
		//				));
		return mGrid;
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
		observers.listTwitCtrl(search);
	}
}


