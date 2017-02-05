package com.iup.tp.twitup.datamodel;

public class ConsoleDatabaseObserver implements IDatabaseObserver{

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		System.out.println("Tweet ajouté : "+addedTwit.getText());
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		System.out.println("Tweet supprimé : "+deletedTwit.getText());
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		System.out.println("Tweet modifié : "+modifiedTwit.getText());
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		System.out.println("User ajouté : "+addedUser.getName());
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		System.out.println("User supprimé : "+deletedUser.getName());
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		System.out.println("User modifié : "+modifiedUser.getName());
	}
}
