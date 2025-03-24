package com.exercici1603;


import java.io.File;
import java.io.IOException;
import javafx.scene.control.Label;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import com.utils.*;

import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller0 {

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

    public File lastOpenedFile;


    @FXML
    private void animateToView0(ActionEvent event) {
        UtilsViews.setViewAnimating("View0");
    }

    @FXML
    private void animateToView1(ActionEvent event) {
            UtilsViews.setViewAnimating("View1");        
    }

    
    @FXML
    private void loadImageFromJSON(String jsonContent) {
        try {
            JSONObject jsonObject = new JSONObject(jsonContent);
            if (jsonObject.has("imageName")) {
                String imageName = jsonObject.getString("imageName");
    
                // Cargar la imagen desde el classpath
                String imagePath = "/assets/images/" + imageName;
                System.out.println("Intentando cargar la imagen desde: " + imagePath);
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                if (image.isError()) {
                    System.out.println("No se pudo cargar la imagen: " + imagePath);
                } else {
                    System.out.println("Contenido del JSON: " + jsonContent);
                    System.out.println("Ruta de la imagen: " + imagePath);
                    imag.setImage(image);
                    System.out.println("Imagen cargada correctamente: " + imagePath);
                }
            } else {
                System.out.println("El JSON no contiene el campo 'imageName'.");
            }
        } catch (org.json.JSONException e) {
            System.out.println("Error al parsear el JSON: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Carrega un arxiu .json a un quadre de text tipus "TextArea"
    @FXML
    private void actionLoadJSON() {
        Stage stage = (Stage) buttonLoad.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arxius JSON", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(stage); //Donde se encuentra el archivo seleccionado
        if (selectedFile != null) {
            try {
                String content = new String(Files.readAllBytes(selectedFile.toPath()));
                lastOpenedFile = selectedFile; // Guarda el archivo abierto
                loadImageFromJSON(content);
                JSONObject jsonObject = new JSONObject(content);
                nomText.setText((String) jsonObject.get("nom"));
                actText.setText((String) jsonObject.get("activitat"));
                dataText.setText((String) jsonObject.get("data_naixement"));
                textArea.setText((String) jsonObject.get("text"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    

   
    
}
   