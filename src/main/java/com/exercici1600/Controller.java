package com.exercici1600;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class Controller {

    // @FXML
    // private Button buttonAdd;

    @FXML
    private Button buttonResult;

    @FXML
    private TextField number0;

    @FXML
    private TextField number1;

    @FXML
    private Text textCounter;

    // @FXML
    // private void actionAdd(ActionEvent event) {
    //     counter++;
    //     textCounter.setText(String.valueOf(counter));
    // }

    @FXML
    private void resultAction(ActionEvent event) {

        int value0 = Integer.parseInt(number0.getText());
        int value1 = Integer.parseInt(number1.getText());
        textCounter.setText(String.valueOf(value0 + value1));
    }

}
