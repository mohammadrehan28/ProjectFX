package com.example.projectfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {

    @FXML
    private Label AddEmployee;

    @FXML
    private VBox All;

    @FXML
    private ComboBox<?> comboType;

    @FXML
    private TextField textColor;

    @FXML
    private TextField textID;

    @FXML
    private TextField textName;

    @FXML
    private TextField textQuantity;

    @FXML
    private TextField textSalary;

    @FXML
    private TextField textSize;

    @FXML
    void AddEmployeeListener(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
