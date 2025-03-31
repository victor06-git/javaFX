package com.exercici0602;

import com.utils.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerPokeCard {

    private int number;

    public void loadPokemon(int number) {
        AppData db = AppData.getInstance();

        ArrayList<HashMap<String, Object>> llistaPokemons = db.query(String.format("SELECT * FROM pokemons WHERE number = 'd%';", this.number));
        if (llistaPokemons.size() == 1) {
            HashMap<String, Object> pokemon = llistaPokemons.get(0);
        }
    }
}