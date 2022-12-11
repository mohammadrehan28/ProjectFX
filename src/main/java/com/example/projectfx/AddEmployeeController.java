package com.example.projectfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    @FXML
    private Label AddEmployee;

    @FXML
    private HBox Manager;

    @FXML
    private ComboBox<?> comboType;

    @FXML
    private DatePicker textBirth;

    @FXML
    private TextField textCity;

    @FXML
    private TextField textDegree;

    @FXML
    private TextField textDegreeManager;

    @FXML
    private TextField textDep;

    @FXML
    private TextField textDriv;

    @FXML
    private TextField textExp;

    @FXML
    private TextField textFirst;

    @FXML
    private TextField textGender;

    @FXML
    private TextField textLast;

    @FXML
    private TextField textPhone;

    @FXML
    private TextField textQual;

    @FXML
    private TextField textSSN;

    @FXML
    private TextField textSecond;

    @FXML
    private TextField textStreet;

    @FXML
    void AddEmployeeListener(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
