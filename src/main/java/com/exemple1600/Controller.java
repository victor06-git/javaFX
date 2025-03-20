package com.exemple1600;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller {

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonClean;

    @FXML
    private TextField titleText;

    @FXML
    private TextArea textArea;

    @FXML
    private void saveAction(ActionEvent event) {
        String title = titleText.getText(); 
        String text = textArea.getText();
        try{
            HashMap<String, Object> textMap = new HashMap<>();
            textMap.put("title", title); 
            textMap.put("text", text);
            JSONObject JsonObject = new JSONObject(textMap);
            Files.write(Paths.get("./data/text.json"), JsonObject.toString(4).getBytes());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void fillAction(ActionEvent event) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("./data/text.json")));

            JSONObject jsonObject = new JSONObject(content);

            String title = jsonObject.getString("title");
            String text = jsonObject.getString("text");

            // titleText.setText(title);
            textArea.setText("Title: " + title + "\nText: " + text);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void cleanAction(ActionEvent event){
        String cleanScreen = "";
        textArea.setText(cleanScreen);
    }
}
