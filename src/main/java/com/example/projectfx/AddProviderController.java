package com.example.projectfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddProviderController implements Initializable {


    @FXML
    private AnchorPane scenePane;
    @FXML
    private StackPane StackP;
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
    private ComboBox<String> comboType;

    @FXML
    private TextField textCity;

    @FXML
    private TextField textCountry;

    @FXML
    private TextField textDesc;

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
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select SSN from Department";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            if(textID.getText().isEmpty()||textID.getText().isBlank()||textID.getText() == null) {
                JOptionPane.showMessageDialog(null, "The SSN is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            while (rs.next()) {
                String ID = rs.getString(1);
                if(ID.equals(textID.getText())) {
                    JOptionPane.showMessageDialog(null, "The SSN is already contains", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    throw new Exception();
                }
            }
            if(textID.getText().isEmpty()||textCity.getText().isEmpty()||textCountry.getText().isEmpty()||textName.getText().isEmpty()||textStreet.getText().isEmpty()||textPhone.getText().isEmpty()||textDesc.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            String ID = textID.getText();
            String Name = textName.getText();
            String Phone = textPhone.getText();
            String Country = textCountry.getText();
            String City = textCity.getText();
            String Street = textStreet.getText();
            String Desc = textDesc.getText();
            String combo = comboType.getSelectionModel().getSelectedItem();
            if(combo.equals("Agriculture equipment")){
                if(textTime.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    throw new Exception();
                }
                String Del = textTime.getText();
                all = "INSERT INTO Employee values("+ID+",'"+Name+"',"+Phone+",'"+Country+"','"+City+"','"+Street+"','"+combo+"',"+Del+",null,null,null,'"+Desc+"')";
            }
            else {
                if(textSize.getText().isEmpty()||textDriv.getText().isEmpty()||textTypeCars.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    throw new Exception();
                }
                String Driv = textDriv.getText();
                String TypeCars = textTypeCars.getText();
                String Size = textSize.getText();
                all = "INSERT INTO Employee values("+ID+",'"+Name+"',"+Phone+",'"+Country+"','"+City+"','"+Street+"','"+combo+"',null,'"+TypeCars+"','"+Size+"','"+Driv+"','"+Desc+"')";
            }
            stmt.executeUpdate(all);
            con.commit();
            con.close();
            Stage stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        } catch(Exception e) {
            //System.out.println(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboType.getItems().addAll("Agriculture equipment", "Delivery");
        comboType.setValue("Agriculture equipment");
    }

    public void ComboAction(ActionEvent actionEvent) {
        if (comboType.getSelectionModel().getSelectedItem().equals("Agriculture equipment")) {
            PaneArgi.toFront();
        }
        else {
            PaneDel.toFront();
        }
    }
}
