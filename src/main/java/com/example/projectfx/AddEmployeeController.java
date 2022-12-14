package com.example.projectfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    public StackPane StackAll;
    public AnchorPane scenePane;
    @FXML
    private Label AddEmployee;

    @FXML
    private VBox All;

    @FXML
    private HBox PaneDrive;

    @FXML
    private HBox PaneManager;

    @FXML
    private HBox PaneNursery;

    @FXML
    private HBox PaneProject;

    @FXML
    private VBox VBoxType;

    @FXML
    private ComboBox<String> comboType;

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
    private String SSNE;

    @FXML
    void AddEmployeeListener(MouseEvent event) {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select SSN from Employee";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            if(textSSN.getText().isEmpty()||textSSN.getText().isBlank()||textSSN.getText() == null) {
                JOptionPane.showMessageDialog(null, "The SSN is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                return;
            }
            while (rs.next()) {
                String SSN = rs.getString(1);
                if(!textSSN.getText().equals(SSNE) && SSN.equals(textSSN.getText())) {
                    JOptionPane.showMessageDialog(null, "The SSN is already contains", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    return;
                }
            }
            if(textSSN.getText().isEmpty()||textBirth.getValue() == null||textPhone.getText().isEmpty()||textFirst.getText().isEmpty()||textSecond.getText().isEmpty()||textLast.getText().isEmpty()||textCity.getText().isEmpty()||textStreet.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                return;
            }
            String SSN = textSSN.getText();
            LocalDate Birth = textBirth.getValue();
            //DATE '1998-12-17'
            String FormatBirth = "DATE '" + Birth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +"'";
            String Phone = textPhone.getText();
            String First = textFirst.getText();
            String Second = textSecond.getText();
            String Last = textLast.getText();
            String Gender = textGender.getText();
            if(Gender.toLowerCase().equals("male")) Gender = "M";
            else if (Gender.toLowerCase().equals("female")) Gender = "F";
            else {
                JOptionPane.showMessageDialog(null, "The Gender is not allowed", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                return;
            }
            String City = textCity.getText();
            String Street = textStreet.getText();
            if(comboType.getSelectionModel().getSelectedItem().equals("Manager Employee")) {
                if(textDegreeManager.getText().isEmpty()||textQual.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    return;
                }
                String Degree = textDegreeManager.getText();
                String Qual = textQual.getText();
                if(screen2Controller.Flag) all = "INSERT INTO Employee values("+SSN+","+FormatBirth+","+Phone+",'"+First+"','"+Second+"','"+Last+"','"+Gender+"','"+City+"','"+Street+"',1,'"+Degree+"','"+Qual+"',0,null,0,null,null,0,null)";
                else all = "UPDATE Employee\n" +
                        "SET SSN = "+SSN+", BirthDate = "+FormatBirth+", phone_number = "+Phone+", first_name = '"+First+"', Middle_name = '"+Second+"', Last_name = '"+Last+"', Gender='"+Gender+"', City = '"+City+"',Manager_flag = 1, degree_manager = '"+Degree+"', Qualification = '"+Qual+"',Driver_flag = 0, Driving_lisence = null, nursery_flag = 0, department_id = null, degree_nursery= null,project_flag = 0, Experience_year=null\n" +
                        "WHERE SSN = "+SSNE;
            }
            else if (comboType.getSelectionModel().getSelectedItem().equals("Driver Employee")) {
                if(textDriv.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    return;
                }
                String Drive = textDriv.getText();
                if(screen2Controller.Flag) all = "INSERT INTO Employee values("+SSN+","+FormatBirth+","+Phone+",'"+First+"','"+Second+"','"+Last+"','"+Gender+"','"+City+"','"+Street+"',0,null,null,1,'"+Drive+"',0,null,null,0,null)";
                else all = "UPDATE Employee\n" +
                        "SET SSN = "+SSN+", BirthDate = "+FormatBirth+", phone_number = "+Phone+", first_name = '"+First+"', Middle_name = '"+Second+"', Last_name = '"+Last+"', Gender='"+Gender+"', City = '"+City+"',Manager_flag = 0, degree_manager = null, Qualification = null,Driver_flag = 1, Driving_lisence = '"+Drive+"', nursery_flag = 0, department_id = null, degree_nursery= null,project_flag = 0, Experience_year=null\n" +
                        "WHERE SSN = "+SSNE;
            }
            else if (comboType.getSelectionModel().getSelectedItem().equals("Nursery Employee")) {
                if(textDep.getText().isEmpty()||textDegree.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    return;
                }
                String Dep = textDep.getText();
                String Degree = textDegree.getText();
                if(screen2Controller.Flag) all = "INSERT INTO Employee values("+SSN+","+FormatBirth+","+Phone+",'"+First+"','"+Second+"','"+Last+"','"+Gender+"','"+City+"','"+Street+"',0,null,null,0,null,1,"+Dep+",'"+Degree+"',0,null)";
                else all = "UPDATE Employee\n" +
                        "SET SSN = "+SSN+", BirthDate = "+FormatBirth+", phone_number = "+Phone+", first_name = '"+First+"', Middle_name = '"+Second+"', Last_name = '"+Last+"', Gender='"+Gender+"', City = '"+City+"',Manager_flag = 0, degree_manager = null, Qualification = null,Driver_flag = 0, Driving_lisence = null, nursery_flag = 1, department_id = '"+Dep+"', degree_nursery= '"+Degree+"',project_flag = 0, Experience_year=null\n" +
                        "WHERE SSN = "+SSNE;
            }
            else {
                if(textExp.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Field is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                    con.close();
                    return;
                }
                String Exp = textExp.getText();
                if(screen2Controller.Flag) all = "INSERT INTO Employee values("+SSN+","+FormatBirth+","+Phone+",'"+First+"','"+Second+"','"+Last+"','"+Gender+"','"+City+"','"+Street+"',0,null,null,0,null,0,null,null,1,'"+Exp+"')";
                else all = "UPDATE Employee\n" +
                        "SET SSN = "+SSN+", BirthDate = "+FormatBirth+", phone_number = "+Phone+", first_name = '"+First+"', Middle_name = '"+Second+"', Last_name = '"+Last+"', Gender='"+Gender+"', City = '"+City+"',Manager_flag = 0, degree_manager = null, Qualification = null,Driver_flag = 0, Driving_lisence = null, nursery_flag = 0, department_id = null, degree_nursery= null,project_flag = 1, Experience_year='"+Exp+"'\n" +
                        "WHERE SSN = "+SSNE;
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
        comboType.getItems().addAll("Manager Employee", "Driver Employee", "Nursery Employee", "Project Employee");
        comboType.setValue("Manager Employee");
        if(!screen2Controller.Flag) {
            AddEmployee.setText("Update Employee");
            SSNE = screen2Controller.EmpUbdate.get(0);
            textSSN.setText(screen2Controller.EmpUbdate.get(0));
            String date2[] = screen2Controller.EmpUbdate.get(1).split(" ");
            textBirth.setValue(LocalDate.parse(date2[0]));
            textPhone.setText(screen2Controller.EmpUbdate.get(2));
            textFirst.setText(screen2Controller.EmpUbdate.get(3));
            textSecond.setText(screen2Controller.EmpUbdate.get(4));
            textLast.setText(screen2Controller.EmpUbdate.get(5));
            String Gender = "";
            if(screen2Controller.EmpUbdate.get(6).equals("M")) Gender = "Male";
            else if (screen2Controller.EmpUbdate.get(6).equals("F")) Gender = "Female";
            textGender.setText(Gender);
            textCity.setText(screen2Controller.EmpUbdate.get(7));
            textStreet.setText(screen2Controller.EmpUbdate.get(8));
            if(screen2Controller.EmpUbdate.get(9).equals("1")) {
                PaneManager.toFront();
                comboType.setValue("Manager Employee");
                textDegreeManager.setText(screen2Controller.EmpUbdate.get(10));
                textQual.setText(screen2Controller.EmpUbdate.get(11));
            }
            else if(screen2Controller.EmpUbdate.get(12).equals("1")) {
                PaneDrive.toFront();
                comboType.setValue("Driver Employee");
                textDriv.setText(screen2Controller.EmpUbdate.get(13));
            }
            else if(screen2Controller.EmpUbdate.get(14).equals("1")) {
                PaneNursery.toFront();
                comboType.setValue("Nursery Employee");
                textDep.setText(screen2Controller.EmpUbdate.get(15));
                textDegree.setText(screen2Controller.EmpUbdate.get(16));
            }
            else {
                PaneProject.toFront();
                comboType.setValue("Project Employee");
                textExp.setText(screen2Controller.EmpUbdate.get(18));
            }
        }

    }

    public void ComboAction(ActionEvent actionEvent) {
        if (comboType.getSelectionModel().getSelectedItem().equals("Manager Employee")) {
            PaneManager.toFront();
        } else if (comboType.getSelectionModel().getSelectedItem().equals("Driver Employee")) {
            PaneDrive.toFront();
        } else if (comboType.getSelectionModel().getSelectedItem().equals("Nursery Employee")) {
            PaneNursery.toFront();
        } else {
            PaneProject.toFront();
        }
    }
}
