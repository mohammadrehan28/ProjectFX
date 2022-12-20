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
    private String IDD;

    @FXML
    void AddEmployeeListener(MouseEvent event) {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select Provider_ID from Department";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            if(textID.getText().isEmpty()||textID.getText().isBlank()||textID.getText() == null) {
                JOptionPane.showMessageDialog(null, "The ID is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                return;
            }
            while (rs.next()) {
                String ID = rs.getString(1);
                if(!textID.getText().equals(IDD) && ID.equals(textID.getText())) {
                    JOptionPane.showMessageDialog(null, "The ID is already contains", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    return;
                }
            }
            if(textID.getText().isEmpty()||textCity.getText().isEmpty()||textCountry.getText().isEmpty()||textName.getText().isEmpty()||textStreet.getText().isEmpty()||textPhone.getText().isEmpty()||textDesc.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                return;
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
                    return;
                }
                String Del = textTime.getText();
                if(screen2Controller.Flag) all = "INSERT INTO Provider values("+ID+",'"+Name+"',"+Phone+",'"+Country+"','"+City+"','"+Street+"','"+combo+"',"+Del+",null,null,null,'"+Desc+"')";
                else all = "UPDATE Provider\n" +
                        "SET Provider_ID = "+ID+", Name_Provider = '"+Name+"', Phone_Number = "+Phone+", country = '"+Country+"', city = '"+City+"', street = '"+Street+"',Type_Provider = '"+combo+"',Dilivery_time = "+Del+",Type_of_cars = null,size_cars=null,Driving_lisence=null,description='"+Desc+"'\n" +
                        "WHERE Provider_ID = "+IDD;
            }
            else {
                if(textSize.getText().isEmpty()||textDriv.getText().isEmpty()||textTypeCars.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    return;
                }
                String Driv = textDriv.getText();
                String TypeCars = textTypeCars.getText();
                String Size = textSize.getText();
                if(screen2Controller.Flag) all = "INSERT INTO Employee values("+ID+",'"+Name+"',"+Phone+",'"+Country+"','"+City+"','"+Street+"','"+combo+"',null,'"+TypeCars+"','"+Size+"','"+Driv+"','"+Desc+"')";
                else all = "UPDATE Provider\n" +
                        "SET Provider_ID = "+ID+", Name_Provider = '"+Name+"', Phone_Number = "+Phone+", country = '"+Country+"', city = '"+City+"', street = '"+Street+"',Type_Provider = '"+combo+"',Dilivery_time = null,Type_of_cars = '"+TypeCars+"',size_cars='"+Size+"',Driving_lisence='"+Driv+"',description='"+Desc+"'\n" +
                        "WHERE Provider_ID = "+IDD;
            }
            stmt.executeUpdate(all);
            con.commit();
            con.close();
            throw new Exception();
        } catch(Exception e) {
            //System.out.println(e);
            Stage stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboType.getItems().addAll("Agriculture equipment", "Delivery");
        comboType.setValue("Agriculture equipment");
        if(!screen2Controller.Flag) {
            AddEmployee.setText("Update Provider");
            IDD = screen2Controller.EmpUbdate.get(0);
            textID.setText(screen2Controller.EmpUbdate.get(0));
            textName.setText(screen2Controller.EmpUbdate.get(1));
            textPhone.setText(screen2Controller.EmpUbdate.get(2));
            textCountry.setText(screen2Controller.EmpUbdate.get(3));
            textCity.setText(screen2Controller.EmpUbdate.get(4));
            textStreet.setText(screen2Controller.EmpUbdate.get(5));
            textDesc.setText(screen2Controller.EmpUbdate.get(11));
            if(screen2Controller.EmpUbdate.get(6).equals("Agriculture equipment")) {
                PaneArgi.toFront();
                comboType.setValue("Agriculture equipment");
                textTime.setText(screen2Controller.EmpUbdate.get(7));
            }
            else {
                PaneDel.toFront();
                comboType.setValue("Delivery");
                textTypeCars.setText(screen2Controller.EmpUbdate.get(8));
                textSize.setText(screen2Controller.EmpUbdate.get(9));
                textDriv.setText(screen2Controller.EmpUbdate.get(10));
            }
        }
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
