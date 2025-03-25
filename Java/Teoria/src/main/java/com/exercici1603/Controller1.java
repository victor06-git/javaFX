package com.exercici1603;

import org.json.JSONObject;

import com.utils.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller1 {

    public File currentFile;

    private JSONObject originalJson; //JsonObject del json conseguido

    public void setJsonData(JSONObject json, File file) {
        this.originalJson = json;
        this.currentFile = file;
        updateFields();
        loadImage();  // Cargar imagen al inicializar
    }

    private void updateFields() {
        if (originalJson != null) {
            nomInput.setText(originalJson.has("nom") ? originalJson.getString("nom") : "");
            actInput.setText(originalJson.has("activitat") ? originalJson.getString("activitat") : "");
            dataInput.setText(originalJson.has("data_naixement") ? originalJson.getString("data_naixement") : "");
            textArea.setText(originalJson.has("text") ? originalJson.getString("text") : "");
        }
    }

    @FXML
    private Button buttonSave, changeImage;

    @FXML
    private TextField nomInput, actInput, dataInput;

    @FXML
    private TextArea textArea;

    @FXML
    private ImageView image;

    @FXML
    private void animateToView0(ActionEvent event) {
        UtilsViews.setViewAnimating("View0");
    }

    @FXML
    private void animateToView1(ActionEvent event) {
        UtilsViews.setViewAnimating("View1");
    }

    @FXML
    private void saveJSON(ActionEvent event) {
        try {
            JSONObject updatedJson = new JSONObject();
            updatedJson.put("nom", nomInput.getText());
            updatedJson.put("activitat", actInput.getText());
            updatedJson.put("data_naixement", dataInput.getText());
            updatedJson.put("text", textArea.getText());
            
            // Preservar la imagen y otros campos no editables
            if (originalJson != null) {
                if (originalJson.has("imageName")) {
                    updatedJson.put("imageName", originalJson.getString("imageName"));
                }
            }
            
            // Guardar con formato legible (indentación 4 espacios)
            Files.write(currentFile.toPath(), updatedJson.toString(4).getBytes());
            
            showAlert("Datos guardados correctamente", AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error al guardar: " + e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    private void changeImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar nueva imagen");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        
        File selectedFile = fileChooser.showOpenDialog(getStage());
        if (selectedFile != null) {
            try {
                String destPath = "src/main/resources/assets/images/" + selectedFile.getName();
                Files.copy(selectedFile.toPath(), Paths.get(destPath), StandardCopyOption.REPLACE_EXISTING);
                
                originalJson.put("imageName", selectedFile.getName());
                loadImage();  // ← Aquí estaba el error, ahora usa loadImage()
                
                showAlert("Imagen cambiada correctamente", AlertType.INFORMATION);
            } catch (IOException e) {
                showAlert("Error al cambiar imagen", AlertType.ERROR);
            }
        }
    }

    private void loadImage() {
        if (originalJson != null && originalJson.has("imageName")) {
            String imagePath = "/assets/images/" + originalJson.getString("imageName");
            try {
                Image img = new Image(getClass().getResourceAsStream(imagePath));
                if (!img.isError()) {
                    image.setImage(img);
                } else {
                    System.err.println("Error al cargar la imagen: " + imagePath);
                }
            } catch (Exception e) {
                System.err.println("Error cargando imagen: " + e.getMessage());
            }
        }
    }

    private Stage getStage() {
        return (Stage) changeImage.getScene().getWindow();
    }

    private void showAlert(String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(type == AlertType.ERROR ? "Error" : "Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
    
