package com.iup.tp.twitup.ihm;


import java.io.File;
import javax.swing.JOptionPane;
import com.iup.tp.twitup.core.ICreateCtrl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class CreateViewJFX implements IJFXView, ICreateObservable, ICreateView {

	protected GridPane pane;
	protected ICreateCtrl observers;
	protected String avatarPath = "";
	
	public CreateViewJFX() {

		pane = new GridPane();
		Label tag = new Label("Tag :");
		Label avatar = new Label("Avatar :");
		Label nom = new Label("Nom :");
		Label mdp = new Label("Mot de passe :");
		Label mdp2 = new Label("Confirmer le mot de passe :");
		TextField tTag = new TextField();
		TextField tNom = new TextField();
		PasswordField tmdp = new PasswordField();
		PasswordField tmdp2 = new PasswordField();
		Button bCreate = new Button("Créer");
		Button bAvatar = new Button("Choisir un fichier ...");
		
		/* Placement des champs de création de compte */
		pane.add(tag, 0, 0);
		pane.add(tTag, 1, 0);
		pane.add(avatar, 0, 1);
		pane.add(bAvatar, 1, 1);
		pane.add(nom, 0, 2);
		pane.add(tNom, 1, 2);
		pane.add(mdp, 0, 3);
		pane.add(tmdp, 1, 3);
		pane.add(mdp2, 0, 4);
		pane.add(tmdp2, 1, 4);
		pane.add(bCreate, 0, 5);

		bCreate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (tmdp.getText().equals(tmdp2.getText())) {
					notifyCreate(tTag.getText(), tNom.getText(), tmdp.getText(), avatarPath);
				} else {
					JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		bAvatar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser chooser = new FileChooser();
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif");
                chooser.getExtensionFilters().add(filter);
				File returnVal = chooser.showOpenDialog(null);
				if(returnVal != null) {
					bAvatar.setText(returnVal.getAbsolutePath());
					avatarPath = returnVal.getAbsolutePath();
				}
			}
		});
		
//		tmdp2.addKeyListener(new java.awt.event.KeyAdapter() {  
//			public void keyPressed(java.awt.event.KeyEvent e) {
//				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
//					if(new String(tmdp.getPassword()).equals(new String(tmdp2.getPassword())))
//						notifyCreate(tTag.getText(), tNom.getText(), new String(tmdp.getPassword()), avatarPath);
//					else
//						JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas", "Erreur", JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		});


	}



	@Override
	public void addCreateCtrl(ICreateCtrl cc) {
		observers = cc;
	}

	@Override
	public void notifyCreate(String login, String nom, String mdp, String aPath) {
		if(!observers.addUser(login, nom, mdp, aPath)) {
			JOptionPane.showMessageDialog(null, "Tag @"+login +" non disponible", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public Pane getPane() {
		// TODO Auto-generated method stub
		return pane;
	}
}