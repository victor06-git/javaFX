package com.exercici1603;

import org.json.JSONObject;

import com.utils.*;

import java.io.File;
import java.nio.file.Files;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;

public class Controller1 {

    public File lastOpenedFile;

    public void setLastOpenedFile(File file) {
        this.lastOpenedFile = file;
    }

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


    public void setJsonContent() {
        if (lastOpenedFile != null) {
            try {
                // Leer el archivo JSON desde el controlador anterior
                String content = new String(Files.readAllBytes(lastOpenedFile.toPath()));

                // Parsear el contenido del JSON
                JSONObject jsonObject = new JSONObject(content);

                // Mostrar los datos en los campos
                nomInput.setText(jsonObject.getString("nom"));
                actInput.setText(jsonObject.getString("activitat"));
                dataInput.setText(jsonObject.getString("data_naixement"));
                textArea.setText(jsonObject.getString("text"));
            } catch (java.io.IOException e) {
                System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            } catch (org.json.JSONException e) {
                System.err.println("Error al parsear el JSON: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún archivo JSON");
        }
    }

    @FXML
    private void saveJSON() {
        if (lastOpenedFile != null) {
            try {
                // Crear un nuevo JSON con los datos editados
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("nom", nomInput.getText());
                jsonObject.put("activitat", actInput.getText());
                jsonObject.put("data_naixement", dataInput.getText());
                jsonObject.put("text", textArea.getText());

                // Guardar el JSON en el archivo original
                Files.write(lastOpenedFile.toPath(), jsonObject.toString(4).getBytes());
                System.out.println("Archivo JSON actualizado: " + lastOpenedFile.getPath());
            } catch (java.io.IOException e) {
                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
            } catch (org.json.JSONException e) {
                System.err.println("Error al crear el JSON: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún json");
        }
    }

}
    
