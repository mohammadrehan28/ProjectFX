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

public class AddDepartmentController implements Initializable {

    @FXML
    private Label AddDep;

    @FXML
    private VBox All;

    @FXML
    private ComboBox<?> comboType;

    @FXML
    private TextField textCity;

    @FXML
    private TextField textCountry;

    @FXML
    private TextField textHours;

    @FXML
    private TextField textID;

    @FXML
    private TextField textStreet;

    @FXML
    void AddEmployeeListener(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
