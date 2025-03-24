package com.exercici1603;

import org.json.JSONObject;

import com.utils.*;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;

public class Controller1 {

    @FXML
    private Button buttonSave;

    @FXML
    private TextField nomInput, actInput, dataInput;

    @FXML
    private TextArea textArea;

    @FXML
    private void animateToView0(ActionEvent event) {
        UtilsViews.setViewAnimating("View0");
    }

    @FXML
    private void animateToView1(ActionEvent event) {
        UtilsViews.setViewAnimating("View1");
    }

    public void setJsonContent(String jsonContent) {
        try {
            // Parsear el contenido del JSON
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Mostrar los datos en los campos
            nomInput.setText(jsonObject.getString("nom"));
            actInput.setText(jsonObject.getString("activitat"));
            dataInput.setText(jsonObject.getString("data_naixement"));
            textArea.setText(jsonObject.getString("text"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void saveJSON() {
        try {
            // Crear un nuevo JSON con los datos editados
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nom", nomInput.getText());
            jsonObject.put("activitat", actInput.getText());
            jsonObject.put("data_naixement", dataInput.getText());
            jsonObject.put("text", textArea.getText());

            // Guardar el JSON en el archivo original
            System.out.println("JSON actualizado: " + jsonObject.toString(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}