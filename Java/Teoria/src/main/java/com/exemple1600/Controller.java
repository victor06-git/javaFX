package com.exemple1600;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Controller {

    @FXML
    private Button buttonSave, buttonClean, buttonLoad;

    @FXML
    private File lastOpenedFile;

    @FXML
    private TextField titleText;

    @FXML
    private TextArea textArea;

    // Carrega un arxiu .json a un quadre de text tipus "TextArea"
    @FXML
    private void actionLoadJSON() {
        Stage stage = (Stage) buttonLoad.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arxius JSON", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                String content = new String(Files.readAllBytes(selectedFile.toPath()));
                textArea.setText(content);
                lastOpenedFile = selectedFile; // Guarda el archivo abierto
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Guarda l'arxiu
    @FXML
    private void actionSaveJSON() {
        Stage stage = (Stage) buttonSave.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();

        // Usa el directorio del último archivo abierto, si existe
        if (lastOpenedFile != null) {
            fileChooser.setInitialDirectory(lastOpenedFile.getParentFile());
        } else {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        }

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arxius JSON", "*.json"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            try {
                String jsonData = textArea.getText();
                if (jsonData.substring(0, 1).equalsIgnoreCase("[")) {
                    JSONArray json = new JSONArray(jsonData);
                    Files.write(selectedFile.toPath(), json.toString(4).getBytes());
                } else {
                    JSONObject json = new JSONObject(jsonData);
                    Files.write(selectedFile.toPath(), json.toString(4).getBytes());
                }
                lastOpenedFile = selectedFile; // Actualiza el archivo guardado
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // @FXML
    // private void saveAction(ActionEvent event) {
    //     String title = titleText.getText(); 
    //     String text = textArea.getText();
    //     try{
    //         HashMap<String, Object> textMap = new HashMap<>();
    //         textMap.put("title", title); 
    //         textMap.put("text", text);
    //         JSONObject JsonObject = new JSONObject(textMap);
    //         Files.write(Paths.get("./data/text.json"), JsonObject.toString(4).getBytes());
    //     } catch (IOException e) {
    //         System.err.println(e.getMessage());
    //     }
    // }

    // @FXML
    // private void fillAction(ActionEvent event) {
    //     try {
    //         String content = new String(Files.readAllBytes(Paths.get("./data/text.json")));

    //         JSONObject jsonObject = new JSONObject(content);

    //         String title = jsonObject.getString("title");
    //         String text = jsonObject.getString("text");

    //         // titleText.setText(title);
    //         textArea.setText("Title: " + title + "\nText: " + text);

    //     } catch (IOException e) {
    //         System.err.println(e.getMessage());
    //     }
    // }

    @FXML
    private void cleanAction(ActionEvent event){
        titleText.clear();
        textArea.clear();
    }
}
