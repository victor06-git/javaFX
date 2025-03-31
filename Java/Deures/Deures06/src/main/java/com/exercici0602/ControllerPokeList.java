package com.exercici0602;

import com.utils.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ControllerPokeList {

    @FXML
    private VBox list;

    public void loadList() {
        
            AppData db = AppData.getInstance();
            db.connect("./data/pokemons.sqlite");

            ArrayList<HashMap<String, Object>> llistaPokemons = db.query("SELECT *  FROM pokemons;");
            setList(llistaPokemons);
        }
    
    private void setList(ArrayList<HashMap<String, Object>> llistaPokemons) {
        try {

            list.getChildren().clear();

            for (HashMap<String, Object> pokemon : llistaPokemons) {
                String name = (String) pokemon.get("name");
                String type = (String) pokemon.get("type");
                Integer number = (Integer) pokemon.get("number");
                String imagePath = (String) pokemon.get("image");

                URL resource = this.getClass().getResource("/assets/subviewPokeList.fxml");
                FXMLLoader loader = new FXMLLoader(resource);
                Parent itemPane = loader.load();
                ControllerItemList itemController = loader.getController();

                itemController.setImatge(imagePath);
                itemController.setName(name);
                itemController.setType(type);
                itemController.setNumber(number.toString());

                list.getChildren().add(itemPane);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar la lista de personajes");
            e.printStackTrace();
        } 
    }

    public void addPokemon(ActionEvent event) {
        //TODO
    }

}