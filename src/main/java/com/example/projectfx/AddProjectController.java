package com.example.projectfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProjectController implements Initializable {

    @FXML
    private Label AddEmployee;

    @FXML
    private VBox All;

    @FXML
    private HBox Manager;

    @FXML
    private ComboBox<?> comboStatus;

    @FXML
    private ComboBox<?> comboType;

    @FXML
    private TextField textCity;

    @FXML
    private TextField textContry;

    @FXML
    private TextField textDegree;

    @FXML
    private TextField textDep;

    @FXML
    private TextField textDriv;

    @FXML
    private DatePicker textEnd;

    @FXML
    private TextField textExp;

    @FXML
    private TextField textName;

    @FXML
    private TextField textNumber;

    @FXML
    private TextField textSSN;

    @FXML
    private TextField textSSNM;

    @FXML
    private DatePicker textStart;

    @FXML
    private TextField textStreet;

    @FXML
    void AddEmployeeListener(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
