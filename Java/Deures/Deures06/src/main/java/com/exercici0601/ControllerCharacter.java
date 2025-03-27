package com.exercici0601;


import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ControllerCharacter {
    @FXML
    private Label nom;

    public void setNom(String nom){
        this.nom.setText(nom);
    }
}
