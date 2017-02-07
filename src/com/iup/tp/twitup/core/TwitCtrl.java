package com.iup.tp.twitup.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ITwitObs;
import com.iup.tp.twitup.ihm.TwitView;

public class TwitCtrl implements ITwitCtrl, IDatabaseObserver {
	protected IDatabase mDatabase;
	protected ITwitObs view;
	protected EntityManager mEntityManager;
	protected ITwitupObs obs;
	
	public TwitCtrl(IDatabase mDatabase, EntityManager mEntityManager, TwitView view) {
		this.mDatabase = mDatabase;
		this.mEntityManager = mEntityManager;
		this.view = view;
		view.addTwitCtrl(this);
	}

	@Override
	public void addObserver(ITwitupObs ctrl) {
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
		listTwitCtrl();
		if(obs != null)
			obs.twitCreated(view);
	}

	public void listTwitCtrl() {
		Set<Twit> setT = mDatabase.getTwits();
		ArrayList<Twit> listT = new ArrayList<Twit>();
		listT.addAll(setT);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}
}