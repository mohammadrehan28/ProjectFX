package com.example.projectfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {

    public AnchorPane scenePane;
    @FXML
    private Label AddEmployee;

    @FXML
    private VBox All;

    @FXML
    private ComboBox<String> comboType;

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
    private String IDD;

    @FXML
    void AddEmployeeListener(MouseEvent event) {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select Item_ID from Item";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            if(textID.getText().isEmpty()||textID.getText().isBlank()||textID.getText() == null) {
                JOptionPane.showMessageDialog(null, "The ID is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            while (rs.next()) {
                String ID = rs.getString(1);
                if(!textID.getText().equals(IDD) && ID.equals(textID.getText())) {
                    JOptionPane.showMessageDialog(null, "The ID is already contains", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    throw new Exception();
                }
            }
            if(textID.getText().isEmpty()||textName.getText().isEmpty()||textColor.getText().isEmpty()||textQuantity.getText().isEmpty()||textSize.getText().isEmpty()||textSalary.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            String ID = textID.getText();
            String Name = textName.getText();
            String Color = textColor.getText();
            String Quantity = textQuantity.getText();
            String Size = textSize.getText();
            String Salary = textSalary.getText();
            String combo = comboType.getSelectionModel().getSelectedItem();
            String Ava;
            if(Integer.parseInt(Quantity) > 0) {
                Ava = "Available";
            }
            else {
                Ava = "NotAvailable";
            }
            if(screen2Controller.Flag) all = "INSERT INTO Item values("+ID+",'"+Name+"','"+combo+"','"+Color+"','"+Quantity+"','"+Ava+"','"+Size+"','"+Salary+"')";
            /*else all = "UPDATE Department\n" +
                    "SET Department_ID = "+ID+", Type = '"+combo+"', Hours_Working = '"+Hours+"', country = '"+Country+"', city = '"+City+"', street = '"+Street+"'\n" +
                    "WHERE Department_ID = "+IDD;*/
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
        comboType.getItems().addAll("OutDoor", "InDoor", "Art","Plant","Equipment","Others","Grass");
        comboType.setValue("OutDoor");
        /*if(!screen2Controller.Flag) {
            AddEmployee.setText("Update Item");
            IDD = screen2Controller.EmpUbdate.get(0);
            textID.setText(screen2Controller.EmpUbdate.get(0));
            comboType.setValue(screen2Controller.EmpUbdate.get(1));
            textName.setText(screen2Controller.EmpUbdate.get(2));
            te.setText(screen2Controller.EmpUbdate.get(3));
            textCity.setText(screen2Controller.EmpUbdate.get(4));
            textStreet.setText(screen2Controller.EmpUbdate.get(5));
        }*/
    }
}
