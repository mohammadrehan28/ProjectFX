package com.example.projectfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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

public class AddProjectController implements Initializable {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label AddEmployee;

    @FXML
    private VBox All;

    @FXML
    private HBox Manager;

    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private ComboBox<String> comboType;

    @FXML
    private TextField textCity;

    @FXML
    private TextField textContry;

    @FXML
    private DatePicker textEnd;

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
    private String IDD;

    @FXML
    void AddEmployeeListener(MouseEvent event) {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select Project_ID from Department";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            if(textSSN.getText().isEmpty()||textSSN.getText().isBlank()||textSSN.getText() == null) {
                JOptionPane.showMessageDialog(null, "The ID is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                return;
            }
            while (rs.next()) {
                String SSN = rs.getString(1);
                if(!textSSN.getText().equals(IDD) && SSN.equals(textSSN.getText())) {
                    JOptionPane.showMessageDialog(null, "The ID is already contains", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    return;
                }
            }
            if(textSSN.getText().isEmpty()||textCity.getText().isEmpty()||textContry.getText().isEmpty()||textName.getText().isEmpty()||textStreet.getText().isEmpty()||textNumber.getText().isEmpty()||textSSNM.getText().isEmpty()||textStart.getValue()==null||textEnd.getValue()==null){
                JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                return;
            }
            String SSN = textSSN.getText();
            String Name = textName.getText();
            String Number = textNumber.getText();
            String SSNM = textSSNM.getText();
            String Country = textContry.getText();
            String City = textCity.getText();
            String Street = textStreet.getText();
            String combo = comboType.getSelectionModel().getSelectedItem();
            String comboS = comboStatus.getSelectionModel().getSelectedItem();
            LocalDate Start = textStart.getValue();
            String FormatStart = "DATE '" + Start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +"'";
            LocalDate End = textEnd.getValue();
            String FormatEnd = "DATE '" + End.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +"'";
            if(screen2Controller.Flag) all = "INSERT INTO Project values("+SSN+",'"+Name+"',"+Number+",'"+combo+"','"+comboS+"','"+Country+"','"+City+"','"+Street+"',"+SSNM+","+FormatStart+","+FormatEnd+")";
            else all = "UPDATE Project\n" +
                    "SET Project_ID = "+SSN+", Name_project = '"+Name+"', Number_project = "+Number+",Type_project = '"+combo+"',status = '"+comboS+"', country = '"+Country+"', city = '"+City+"', street = '"+Street+"',SSN_manager = "+SSNM+",start_date = "+FormatStart+",Finish_date = "+FormatEnd+"\n" +
                    "WHERE Project_ID = "+IDD;
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
        comboType.getItems().addAll("Villa with score", "Villa", "score");
        comboType.setValue("Villa");
        comboStatus.getItems().addAll("On Working", "To Work", "Done");
        comboStatus.setValue("To Work");
        if(!screen2Controller.Flag) {
            AddEmployee.setText("Update Project");
            IDD = screen2Controller.EmpUbdate.get(0);
            textSSN.setText(screen2Controller.EmpUbdate.get(0));
            textName.setText(screen2Controller.EmpUbdate.get(1));
            textNumber.setText(screen2Controller.EmpUbdate.get(2));
            comboType.setValue(screen2Controller.EmpUbdate.get(3));
            comboStatus.setValue(screen2Controller.EmpUbdate.get(4));
            textContry.setText(screen2Controller.EmpUbdate.get(5));
            textCity.setText(screen2Controller.EmpUbdate.get(6));
            textStreet.setText(screen2Controller.EmpUbdate.get(7));
            textSSNM.setText(screen2Controller.EmpUbdate.get(8));
            String date1[] = screen2Controller.EmpUbdate.get(9).split(" ");
            textStart.setValue(LocalDate.parse(date1[0]));
            String date2[] = screen2Controller.EmpUbdate.get(10).split(" ");
            textEnd.setValue(LocalDate.parse(date2[0]));
        }
    }
}
