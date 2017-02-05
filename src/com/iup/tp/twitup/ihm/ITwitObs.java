package com.iup.tp.twitup.ihm;

import java.util.List;

import com.iup.tp.twitup.core.ITwitCtrl;
import com.iup.tp.twitup.datamodel.Twit;

public interface ITwitObs {
	void addTwitCtrl(ITwitCtrl cc);
	void notifyTwitCreate(String twit);
	void notifyNeedListTwit();
	void listTwits(List<Twit> twits);	
}
