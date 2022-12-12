package com.example.projectfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProviderController implements Initializable {

    @FXML
    private Label AddEmployee;

    @FXML
    private VBox All;

    @FXML
    private HBox PaneArgi;

    @FXML
    private HBox PaneDel;

    @FXML
    private VBox VBoxType;

    @FXML
    private ComboBox<?> comboType;

    @FXML
    private TextField textCity;

    @FXML
    private TextField textCountry;

    @FXML
    private TextField textDriv;

    @FXML
    private TextField textID;

    @FXML
    private TextField textName;

    @FXML
    private TextField textPhone;

    @FXML
    private TextField textSize;

    @FXML
    private TextField textStreet;

    @FXML
    private TextField textTime;

    @FXML
    private TextField textTypeCars;

    @FXML
    void AddEmployeeListener(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
