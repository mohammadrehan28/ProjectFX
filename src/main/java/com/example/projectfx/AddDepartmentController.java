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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddDepartmentController implements Initializable {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label AddDep;

    @FXML
    private VBox All;

    @FXML
    private ComboBox<String> comboType;

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
    private String IDD;
    @FXML
    void AddDepListener(MouseEvent event) {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select Department_ID from Department";
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
            if(textID.getText().isEmpty()||textCountry.getText().isEmpty()||textCity.getText().isEmpty()||textHours.getText().isEmpty()||textStreet.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                return;
            }
            String ID = textID.getText();
            String Hours = textHours.getText();
            String Country = textCountry.getText();
            String City = textCity.getText();
            String Street = textStreet.getText();
            String combo = comboType.getSelectionModel().getSelectedItem();
            if(screen2Controller.Flag) all = "INSERT INTO Department values("+ID+",'"+combo+"','"+Hours+"','"+Country+"','"+City+"','"+Street+"')";
            else all = "UPDATE Department\n" +
                    "SET Department_ID = "+ID+", Type = '"+combo+"', Hours_Working = '"+Hours+"', country = '"+Country+"', city = '"+City+"', street = '"+Street+"'\n" +
                    "WHERE Department_ID = "+IDD;
            stmt.executeUpdate(all);
            con.commit();
            con.close();
            throw new Exception();
        } catch(Exception e) {
            System.out.println(e);
            Stage stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboType.getItems().addAll("storehouse", "Roses gallery", "Planter");
        comboType.setValue("storehouse");
        if(!screen2Controller.Flag) {
            AddDep.setText("Update Department");
            IDD = screen2Controller.EmpUbdate.get(0);
            textID.setText(screen2Controller.EmpUbdate.get(0));
            comboType.setValue(screen2Controller.EmpUbdate.get(1));
            textHours.setText(screen2Controller.EmpUbdate.get(2));
            textCountry.setText(screen2Controller.EmpUbdate.get(3));
            textCity.setText(screen2Controller.EmpUbdate.get(4));
            textStreet.setText(screen2Controller.EmpUbdate.get(5));
        }
    }
}
