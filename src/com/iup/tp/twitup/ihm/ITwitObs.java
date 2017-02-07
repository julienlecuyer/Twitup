package com.iup.tp.twitup.ihm;

import java.util.ArrayList;

import com.iup.tp.twitup.core.ITwitCtrl;
import com.iup.tp.twitup.datamodel.Twit;

public interface ITwitObs extends IView{
	void addTwitCtrl(ITwitCtrl cc);
	void notifyTwitCreate(String twit);
	void notifyNeedListTwit();
	void listTwits(ArrayList<Twit> twits, String search);	
}
