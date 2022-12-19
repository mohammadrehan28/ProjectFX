package com.example.projectfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class AddProviderRelController implements Initializable {

    public TextField textQuan;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label AddEmployee;

    @FXML
    private VBox All;

    @FXML
    private TextField textID;

    @FXML
    private TextField textName;
    //private String IDD;

    void SetText(String S1, String S2) {
        textName.setText(S1);
        textID.setText(S2);
    }

    @FXML
    void AddEmployeeListener(MouseEvent event) {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select * from Department_Have_Items";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            if(textID.getText().isEmpty() && textName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The ID is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            while (rs.next()) {
                String ID = rs.getString(1);
                String ID2 = rs.getString(2);
                if(ID2.equals(textName.getText()) && ID.equals(textID.getText())) {//!textID.getText().equals(IDD)
                    JOptionPane.showMessageDialog(null, "The ID is already contains", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    throw new Exception();
                }
            }
            if(textID.getText().isEmpty()||textName.getText().isEmpty()||textQuan.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            String ID = textID.getText();
            String Name = textName.getText();
            String Quan = textQuan.getText();
            all = "INSERT INTO Department_Have_Items values('"+ID+"','"+Name+"',"+Quan+")";
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
        if(!screen2Controller.Flag) {
            AddEmployee.setText("Update Buyer");
        }
    }
}
