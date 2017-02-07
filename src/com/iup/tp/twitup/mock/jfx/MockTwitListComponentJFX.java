package com.iup.tp.twitup.mock.jfx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.mock.ITwitListObserver;

public class MockTwitListComponentJFX extends Pane implements ITwitListObserver {

	protected Map<Twit, MockTwitComponentJFX> twitMap = new HashMap<Twit, MockTwitComponentJFX>();

	protected ScrollPane scrollPane;

	protected GridPane contentPane;

	public MockTwitListComponentJFX() {
				initComponent();
	}

	protected void initComponent() {
		contentPane = new GridPane();
		contentPane.setPrefSize(370, 320);
		scrollPane = new ScrollPane(contentPane);
		scrollPane.setMinSize(390, 320);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		this.getChildren().add(scrollPane);
	}

	@Override
	public synchronized void notifyTwitListHasChanged(List<Twit> twits) {
		List<MockTwitComponentJFX> newTwitComponents = new ArrayList<MockTwitComponentJFX>();
		for (Twit twit : twits) {

			MockTwitComponentJFX component = twitMap.get(twit);

			// Nouveau twit
			if (component == null) {
				MockTwitComponentJFX newTwitComponent = this
						.createTwitComponent(twit);
				this.addTwitComponent(twit, newTwitComponent);
				newTwitComponents.add(newTwitComponent);
			}
		}

		List<MockTwitComponentJFX> deletedTwitComponents = new ArrayList<MockTwitComponentJFX>();
		List<Twit> toRemove = new ArrayList<Twit>();
		for (Twit oldTwit : twitMap.keySet()) {
			if (twits.contains(oldTwit) == false) {
				MockTwitComponentJFX oldTwitComponent = twitMap.get(oldTwit);
				if (oldTwitComponent != null) {
					deletedTwitComponents.add(oldTwitComponent);
				}
				toRemove.add(oldTwit);
			}
		}
		for (Twit remove : toRemove) {
			twitMap.remove(remove);
		}

		Runnable r = new Runnable() {

			@Override
			public void run() {
				updateTwitsComponents(deletedTwitComponents, newTwitComponents, twits);
			}
		};

		Thread t = new Thread(r);
		t.start();
	}

	protected void updateTwitsComponents(
			List<MockTwitComponentJFX> deletedTwitComponents,
			List<MockTwitComponentJFX> newTwitComponents,
			List<Twit> allTwitToShow) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				
				// Ajout des animations de disparition
				for (MockTwitComponentJFX oldTwitComponent : deletedTwitComponents) {
					oldTwitComponent.hideTwit();
				}

				// Placement des composants � ajouter (en conservant la liste tri�e)
				replaceTwit(allTwitToShow);

				// Ajout des animations d'apparition
				for (MockTwitComponentJFX newTwitComponent : newTwitComponents) {
					newTwitComponent.showTwit();
				}
			}
		});
	}

	private void replaceTwit(List<Twit> twits) {

		int posY = 0;

		for (Twit twit : twits) {
			MockTwitComponentJFX component = twitMap.get(twit);

			if (component != null) {
				GridPane.setConstraints(component, 0, posY);
				posY++;
			}
		}
	}

	protected void addTwitComponent(Twit twit, MockTwitComponentJFX component) {
		twitMap.put(twit, component);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				contentPane.add(component, 0, 0);
				GridPane.setFillWidth(component, true);
			}
		});
	}

	protected MockTwitComponentJFX createTwitComponent(Twit twit) {
		MockTwitComponentJFX mockTwitComponent = new MockTwitComponentJFX(twit);
		mockTwitComponent.setVisible(false);

		return mockTwitComponent;
	}
}
