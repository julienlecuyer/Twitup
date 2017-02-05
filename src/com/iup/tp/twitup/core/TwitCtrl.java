package com.iup.tp.twitup.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.ITwitObs;
import com.iup.tp.twitup.ihm.TwitView;

public class TwitCtrl implements ITwitCtrl {
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
		notifyTwitCreated();
		return true;
	}

	@Override
	public void notifyTwitCreated() {
		// listTwt affichage
		System.out.println("YTwitt créé");
	}
	
	public void listTwitCtrl() {
		Set<Twit> setT = mDatabase.getTwits();
		List<Twit> listT = new ArrayList<Twit>();
		listT.addAll(setT);
		//listT.sort(new Comparator());
		
		//view.listTwits(mDatabase.getTwits());
	}
}