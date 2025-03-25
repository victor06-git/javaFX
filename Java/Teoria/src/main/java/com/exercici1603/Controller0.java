package com.exercici1603;


import java.io.File;
import java.io.IOException;
import javafx.scene.control.Label;
import java.nio.file.Files;

import org.json.JSONObject;

import com.utils.*;

import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller0 {

    private File lastOpenedFile;
    private JSONObject currentJson;
    @FXML
    private Button button0, button1, buttonLoad;
    @FXML
    private AnchorPane container;
    @FXML
    private TextArea textArea;
    @FXML
    private ImageView imag;
    @FXML
    private Label nomText, actText, dataText;

    @FXML
    private void animateToView0(ActionEvent event) {
        UtilsViews.setViewAnimating("View0");
    }

    @FXML
    private void animateToView1(ActionEvent event) {
        if (lastOpenedFile != null) {
            Controller1 controller1 = (Controller1) UtilsViews.getController("View1");
            controller1.setJsonData(currentJson, lastOpenedFile); // Pasa los datos actuales
            UtilsViews.setViewAnimating("View1");
        } else {
            showAlert("Primero carga un archivo JSON");
        }
    }
            
    @FXML
    private void actionLoadJSON() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        
        File selectedFile = fileChooser.showOpenDialog(getStage()); //Determina el json a la variable selectedFile
        if (selectedFile != null) {
            try {
                String content = new String(Files.readAllBytes(selectedFile.toPath()));
                lastOpenedFile = selectedFile; //Json guardat a la variable lastOpenedFile
                currentJson = new JSONObject(content);
                
                // Actualizar todos los campos
                nomText.setText(currentJson.has("nom") ? currentJson.getString("nom") : "");
                actText.setText(currentJson.has("activitat") ? currentJson.getString("activitat") : "");
                dataText.setText(currentJson.has("data_naixement") ? currentJson.getString("data_naixement") : "");
                textArea.setText(currentJson.has("text") ? currentJson.getString("text") : "");
                
                // Cargar imagen 
                if (currentJson.has("imageName")) { //Si existe una imagen en jsonObject
                    String imagePath = "/assets/images/" + currentJson.getString("imageName");
                    try {
                        Image image = new Image(getClass().getResourceAsStream(imagePath));
                        if (!image.isError()) { 
                            imag.setImage(image); //set image a la imageView
                        }
                    } catch (Exception e) {
                        System.out.println("Error cargando imagen: " + e.getMessage());
                    }
                }
                
            } catch (IOException e) {
                showAlert("Error al cargar el archivo: " + e.getMessage());
            } catch (org.json.JSONException e) {
                showAlert("El archivo no tiene formato JSON válido");
            }
        }
    }

    private Stage getStage() {
        return (Stage) buttonLoad.getScene().getWindow();
    }

    private void showAlert(String message) { //Función muestra un mensaje emergente
        Alert alert = new Alert(Alert.AlertType.WARNING); //alerta
        alert.setTitle("Advertencia"); //titulo de la alert
        alert.setHeaderText(null); 
        alert.setContentText(message); //Contenido de la alerta
        alert.showAndWait();
    }
    
        
}
    