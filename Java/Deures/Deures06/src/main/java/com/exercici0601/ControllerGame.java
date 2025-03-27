package com.exercici0601;



import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ControllerGame {
    @FXML
    private Label nom, game;

    @FXML
    private ImageView image;

    public void setNom(String nom){
        this.nom.setText(nom);
    }

    public void setGame(String game) {
        this.game.setText(game);
    }

    public void setImage(Image image){
        this.image.setImage(image);
    }


}
