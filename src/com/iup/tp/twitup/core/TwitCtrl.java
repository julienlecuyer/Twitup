package com.iup.tp.twitup.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ITwitObservable;

public class TwitCtrl implements ITwitCtrl, IDatabaseObserver {
	protected IDatabase mDatabase;
	protected ITwitObservable view;
	protected EntityManager mEntityManager;
	protected ITwitupObservateur obs;

	public TwitCtrl(IDatabase mDatabase, EntityManager mEntityManager, ITwitObservable view) {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
		this.view = view;
		view.addTwitCtrl(this);
	}

	@Override
	public void addObserver(ITwitupObservateur ctrl) {
		obs = ctrl;
	}

	@Override
	public boolean CreateTwit(String twit) {
		Twit t = new Twit(obs.getUserCo(), twit);
		mEntityManager.sendTwit(t);
		return true;
	}

	@Override
	public void notifyTwitCreated() {
		listTwitCtrl(null);
		if(obs != null)
			obs.twitCreated(view);
	}

	public void listTwitCtrl(String search) {
		Set<Twit> setT = mDatabase.getTwits();
		ArrayList<Twit> listT = new ArrayList<Twit>();
		if(search == null || search.equals("")) {
			listT.clear();
			setT.clear();
			setT = mDatabase.getTwits();
			listT.addAll(setT);
		} else {
			if(search.startsWith("@")) {
				listT.clear();
				setT.clear();
				search = search.substring(1);
				setT = mDatabase.getTwitsWithUserTag('@'+search);
				listT.addAll(setT);
				setT.clear();
				setT = mDatabase.getTwits();
				for(Twit t : setT) {
					if(t.getTwiter().getUserTag().equals(search) || t.getText().contains(search)) {
						listT.add(t);
					}
				}
			} else if(search.startsWith("#")) {
				listT.clear();
				setT.clear();
				search = search.substring(1);
				setT = mDatabase.getTwitsWithTag(search);
				listT.addAll(setT);
			}
		}
		Collections.sort(listT, new Comparator<Twit>() {
			@Override
			public int compare(Twit t1, Twit t2) {
				return Long.compare(t2.getEmissionDate(), t1.getEmissionDate()); 
			}
		});
		view.listTwits(listT, null);
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		notifyTwitCreated();
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {

	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {

	}

	@Override
	public void notifyUserAdded(User addedUser) {

	}

	@Override
	public void notifyUserDeleted(User deletedUser) {

	}

	@Override
	public void notifyUserModified(User modifiedUser) {

	}
}