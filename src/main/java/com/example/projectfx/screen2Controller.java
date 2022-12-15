package com.example.projectfx;

import animatefx.animation.FadeIn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import oracle.jdbc.pool.OracleDataSource;
import javafx.scene.control.TableColumn.CellDataFeatures;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.util.Callback;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import java.io.*;
import java.util.Map;

public class screen2Controller implements Initializable {

    @FXML
    private Button ReportBuyer;
    @FXML
    private Button ReportPr;
    @FXML
    private TextField TextReportBuyer;
    @FXML
    public Button SearchItem;
    @FXML
    public ComboBox<String> ComboSearchItem;
    @FXML
    public ComboBox<String> ComboDep;
    public TextField TextReportPro;
    @FXML
    private Button DeleteB;

    @FXML
    private Button DeleteD;

    @FXML
    private Button DeleteE;

    @FXML
    private Button DeleteP;

    @FXML
    private Button DeleteProvider;
    @FXML
    private Button addB;

    @FXML
    private Button addD;

    @FXML
    private Button addE;

    @FXML
    private Button addP;

    @FXML
    private Button addProvider;
    @FXML
    public CheckBox checkEM;
    @FXML
    public CheckBox checkEF;
    @FXML
    public TableView tableEmployee;
    @FXML
    public TableView tableDepartment;
    @FXML
    public TableView tableProject;
    @FXML
    public TableView tableProvider;
    @FXML
    public TableView tableBuyer;
    @FXML
    public TextField searchText;
    @FXML
    private ResultSet rs;
    @FXML
    public TextField searchB;


    @FXML
    public TextField searchBID;

    @FXML
    public TextField searchD;

    @FXML
    public TextField searchDID;

    @FXML
    public TextField searchE;

    @FXML
    public TextField searchEID;

    @FXML
    public TextField searchP;

    @FXML
    public TextField searchPID;

    @FXML
    public TextField searchProvider;

    @FXML
    public TextField searchProviderID;


    @FXML
    public ObservableList<ObservableList> employee;
    @FXML
    public ObservableList<ObservableList> department;
    @FXML
    public ObservableList<ObservableList> project;
    @FXML
    public ObservableList<ObservableList> provider;
    @FXML
    public ObservableList<ObservableList> buyer;
    @FXML
    public ObservableList<ObservableList> datadepartment;

    static ObservableList<String> EmpUbdate = FXCollections.observableArrayList();
    static boolean Flag = true;

    @FXML
    private Button buyer_btn;

    @FXML
    private Button department_btn;

    @FXML
    private Button employee_btn;

    @FXML
    private Button exit_btn;

    @FXML
    private ImageView icon2;

    @FXML
    private Button item_btn;

    @FXML
    private Pane pane_state;

    @FXML
    private Button project_btn;

    @FXML
    private Button provider_btn;

    @FXML
    private Label text_state;
    @FXML
    private GridPane grid1;

    @FXML
    private GridPane grid2;

    @FXML
    private GridPane grid3;

    @FXML
    private GridPane grid4;

    @FXML
    private GridPane grid5;

    @FXML
    private GridPane grid6;
    @FXML
    private ComboBox<String> Ecombo;

    //For Items
    @FXML
    public VBox VBoxOutDoor;
    @FXML
    public VBox VBoxInDoor;
    @FXML
    public VBox VBoxArt;
    @FXML
    public VBox VBoxPlants;
    @FXML
    public VBox VBoxEqu;
    @FXML
    public VBox VBoxOthers;
    @FXML
    public VBox VBoxGrass;
    @FXML
    public ScrollPane scrollItems;
    @FXML
    public GridPane gridItems;
    public String Items[] = {"OutDoor", "InDoor", "Art", "Plant", "Equipment", "Other", "Grass"};
    public int Indicator = 0;
    private ItemsController ItemController;
    public TextField TextS;
    public int selectCombo = -1;
    @FXML
    public ComboBox<String> selectedCombo;
    @FXML
    public AnchorPane AnchorItems;
    public int selectedComboBuyer = -1;
    public int selectedComboDepartment = -1;
    public int selectedComboProvider = -1;
    public int selectedComboProject = -1;
    public int selectedComboEmployee = -1;
    FilteredList<ObservableList> filterEmp;
    @FXML
    private ComboBox<String> ComboBuyer;

    @FXML
    private ComboBox<String> ComboProject;

    @FXML
    private ComboBox<String> ComboProvider;
    //End For Items

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //data.clear();
        //datadepartment.clear();
        getFromAllDataEmp(employee, tableEmployee, 18, searchE,"Select * from employee",true);
        getFromAllDataDep(department, tableDepartment,  5, searchD,"select D.Department_ID,D.Type,D.Hours_Working,D.country,D.city,D.Street,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item from Department D, department_have_items DH,Item i where D.Department_ID = DH.Department_ID And I.Item_ID = DH.Item_ID",true);
        getFromAllDataPro(project, tableProject,  10, searchP,"Select * from project",true);
        getFromAllDataProvider(provider, tableProvider,  11, searchProvider,"select D.Provider_ID,D.Name_Provider,D.Phone_number,D.country,D.city,D.street,D.Type_Provider,D.Dilivery_Time,D.Type_of_cars,D.Size_Cars,D.Driving_Lisence,D.Description,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item from Provider D, provider_provide_items DH ,Item i where D.Provider_ID = DH.Provider_ID And I.Item_ID = DH.Item_ID",true);
        getFromAllDataBuyer(buyer, tableBuyer,  1, searchB,"select D.Buyer_ID,D.Name_Buyer,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item,DD.Department_ID from Buyer D, Buyer_Buy_Items DH,Item i, Department DD where D.Buyer_ID = DH.Buyer_ID And I.Item_ID = DH.Item_ID And DD.Department_ID = DH.Department_ID Order by D.Name_Buyer",true);
        // Ecombo =new ComboBox<>();
        Ecombo.getItems().addAll("Maneger", "Driver", "Nursery", "Project", "All");
        Ecombo.setValue("All");
        ComboDep.getItems().addAll("All", "Type");
        ComboDep.setValue("All");
        ComboBuyer.getItems().addAll("All", "Name");
        ComboBuyer.setValue("All");
        ComboProject.getItems().addAll("All", "Name");
        ComboProject.setValue("All");
        ComboProvider.getItems().addAll("All", "Name");
        ComboProvider.setValue("All");
        ComboSearchItem.getItems().addAll("Name","Salary less than", "Salary more than");
        ComboSearchItem.setValue("Name");
        //selectedCombo.getItems().addAll("All","Name","Color","Available","Size","Salary");
        //selectedCombo.setValue("All");
        pane_state.setBackground(new Background(new BackgroundFill(Color.rgb(162, 217, 206), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @FXML
    public void itemStateChanged(ActionEvent event) {
        if (Ecombo.getSelectionModel().getSelectedItem().equals("All")) {
            selectedComboEmployee = -1;
        } else if (Ecombo.getSelectionModel().getSelectedItem().equals("Maneger")) {
            selectedComboEmployee = 9;
        } else if (Ecombo.getSelectionModel().getSelectedItem().equals("Driver")) {
            selectedComboEmployee = 12;
        } else if (Ecombo.getSelectionModel().getSelectedItem().equals("Nursery")) {
            selectedComboEmployee = 14;
        } else if (Ecombo.getSelectionModel().getSelectedItem().equals("Project")) {
            selectedComboEmployee = 17;
        }
    }

    @FXML
    public void reportAction(ActionEvent event){

            if(event.getSource() == ReportPr) {
                try {
                    OracleDataSource ods = new OracleDataSource();
                    ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                    ods.setUser("mohammad");
                    ods.setPassword("123456");
                    Connection con = ods.getConnection();
                    String qry = "select name_project from project where project_id = " + searchPID.getText();
                    Statement stmt = con.createStatement();
                    rs = stmt.executeQuery(qry);
                    rs.next();
                    Map<String, Object> parameter = new HashMap<String, Object>();
                    parameter.put("ProjectP1", searchPID.getText());
                    parameter.put("ProjectP2", TextReportPro.getText());
                    parameter.put("ProjectP3", rs.getString(1));//rs.getString(1)


                    InputStream input = new FileInputStream(new File("projectReport.jrxml"));
                    JasperDesign jd = JRXmlLoader.load(input);
                    JasperReport jr = JasperCompileManager.compileReport(jd);
                    JasperPrint jp = JasperFillManager.fillReport(jr, parameter, con);
                    //as pdf dirictly
           /* OutputStream os=new FileOutputStream(new File("EmplyeeSUM.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,os);
            os.close();
            input.close();*/
                    //as JFrame
                    JFrame frame = new JFrame("Report");
                    frame.getContentPane().add(new JRViewer(jp));
                    frame.pack();
                    frame.setVisible(true);
                    con.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    OracleDataSource ods = new OracleDataSource();
                    ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                    ods.setUser("mohammad");
                    ods.setPassword("123456");
                    Connection con = ods.getConnection();
                    String qry = "select name_Buyer from Buyer where buyer_id = '" + searchBID.getText()+"'";

                    Statement stmt = con.createStatement();
                    rs = stmt.executeQuery(qry);
                    rs.next();
                    System.out.println(rs.getString(1));
                    Map<String, Object> parameter2 = new HashMap<String, Object>();
                    parameter2.put("Parameter1", searchBID.getText());
                    parameter2.put("Parameter2", TextReportBuyer.getText());
                    parameter2.put("Parameter3", rs.getString(1));//rs.getString(1)


                    InputStream input = new FileInputStream(new File("REPORT2.jrxml"));
                    JasperDesign jd = JRXmlLoader.load(input);
                    JasperReport jr = JasperCompileManager.compileReport(jd);
                    JasperPrint jp = JasperFillManager.fillReport(jr, parameter2, con);
                    //as pdf dirictly
           /* OutputStream os=new FileOutputStream(new File("EmplyeeSUM.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,os);
            os.close();
            input.close();*/
                    //as JFrame
                    JFrame frame = new JFrame("Report");
                    frame.getContentPane().add(new JRViewer(jp));
                    frame.pack();
                    frame.setVisible(true);
                    con.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
    }

    @FXML
    public void itemStateChangedDep(ActionEvent actionEvent) {
        if (ComboDep.getSelectionModel().getSelectedItem().equals("All")) {
            selectedComboDepartment = -1;
        }
        else if (ComboDep.getSelectionModel().getSelectedItem().equals("Type")){
            selectedComboDepartment = 1;
        }
    }

    @FXML
    void itemStateChangedBuyer(ActionEvent event) {
        if (ComboBuyer.getSelectionModel().getSelectedItem().equals("All")) {
            selectedComboBuyer = -1;
        }
        else if (ComboBuyer.getSelectionModel().getSelectedItem().equals("Name")){
            selectedComboBuyer = 1;
        }
    }

    @FXML
    void itemStateChangedPro(ActionEvent event) {
        if (ComboProject.getSelectionModel().getSelectedItem().equals("All")) {
            selectedComboProject = -1;
        }
        else if (ComboProject.getSelectionModel().getSelectedItem().equals("Name")){
            selectedComboProject = 1;
        }
    }

    @FXML
    void itemStateChangedProvider(ActionEvent event) {
        if (ComboProvider.getSelectionModel().getSelectedItem().equals("All")) {
            selectedComboProvider = -1;
        }
        else if (ComboProvider.getSelectionModel().getSelectedItem().equals("Name")){
            selectedComboProvider = 1;
        }
    }

    public void itemcheck(ActionEvent event) {
        if(checkEM.isSelected()){
            selectedComboEmployee = 6;
            getFromAllDataEmp(employee, tableEmployee,  18, searchE,"Select * from employee where GENDER='M'",false);
        }
        else if(checkEF.isSelected()){
            selectedComboEmployee = 6;
            getFromAllDataEmp(employee, tableEmployee,  18, searchE,"Select * from employee where GENDER='F'",false);
        }
        else if (!checkEM.isSelected() && !checkEF.isSelected()){
            selectedComboEmployee = -1;
            getFromAllDataEmp(employee, tableEmployee,  18, searchE,"Select * from employee",false);
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == employee_btn) {
            text_state.setText("Employee");
            grid1.toFront();

        } else if (event.getSource() == department_btn) {
            text_state.setText("Department");
            grid2.toFront();
        } else if (event.getSource() == item_btn) {
            text_state.setText("Item");
            grid3.toFront();

        } else if (event.getSource() == project_btn) {
            text_state.setText("Project");
            grid4.toFront();
        } else if (event.getSource() == provider_btn) {
            text_state.setText("Provider");
            grid5.toFront();
        } else if (event.getSource() == buyer_btn) {
            text_state.setText("Buyer");
            grid6.toFront();
        }

    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) exit_btn.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void TableEmpListener(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            Flag = false;
            if(event.getSource() == tableEmployee) {
                EmpUbdate = (ObservableList<String>) tableEmployee.getSelectionModel().getSelectedItem();
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
                    stage.setTitle("Our Big Project!!");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                    getFromAllDataEmp(employee, tableEmployee, 18, searchE, "Select * from employee", false);
                    new FadeIn(root).play();
                } catch (Exception e) {
                    System.out.println(e);
                }
                //System.out.println(EmpUbdate);
            }
            else if(event.getSource() == tableDepartment) {
                EmpUbdate = (ObservableList<String>) tableDepartment.getSelectionModel().getSelectedItem();
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AddDepartment.fxml"));
                    stage.setTitle("Our Big Project!!");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                    getFromAllDataDep(department, tableDepartment,  5, searchD,"select D.Department_ID,D.Type,D.Hours_Working,D.country,D.city,D.Street,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item from Department D, department_have_items DH,Item i where D.Department_ID = DH.Department_ID And I.Item_ID = DH.Item_ID",false);
                    new FadeIn(root).play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            else if(event.getSource() == tableProject) {
                EmpUbdate = (ObservableList<String>) tableProject.getSelectionModel().getSelectedItem();
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AddProject.fxml"));
                    stage.setTitle("Our Big Project!!");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                    getFromAllDataPro(project, tableProject,  10, searchP,"Select * from project",false);
                    new FadeIn(root).play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            else if(event.getSource() == tableProvider) {
                EmpUbdate = (ObservableList<String>) tableProvider.getSelectionModel().getSelectedItem();
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AddProvider.fxml"));
                    stage.setTitle("Our Big Project!!");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                    getFromAllDataProvider(provider, tableProvider,  11, searchProvider,"select D.Provider_ID,D.Name_Provider,D.Phone_number,D.country,D.city,D.street,D.Type_Provider,D.Dilivery_Time,D.Type_of_cars,D.Size_Cars,D.Driving_Lisence,D.Description,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item from Provider D, provider_provide_items DH ,Item i where D.Provider_ID = DH.Provider_ID And I.Item_ID = DH.Item_ID",false);
                    new FadeIn(root).play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            else if(event.getSource() == tableBuyer) {
                EmpUbdate = (ObservableList<String>) tableBuyer.getSelectionModel().getSelectedItem();
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AddBuyer.fxml"));
                    stage.setTitle("Our Big Project!!");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                    getFromAllDataBuyer(buyer, tableBuyer,  1, searchB,"select D.Buyer_ID,D.Name_Buyer,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item,DD.Department_ID from Buyer D, Buyer_Buy_Items DH,Item i, Department DD where D.Buyer_ID = DH.Buyer_ID And I.Item_ID = DH.Item_ID And DD.Department_ID = DH.Department_ID Order by D.Name_Buyer",false);
                    new FadeIn(root).play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    @FXML
    void AddListener(ActionEvent event) {
        Flag = true;
        if (event.getSource() == addE) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
                stage.setTitle("Our Big Project!!");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                getFromAllDataEmp(employee, tableEmployee,  18, searchE,"Select * from employee",false);
                new FadeIn(root).play();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else if (event.getSource() == addD) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AddDepartment.fxml"));
                stage.setTitle("Our Big Project!!");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                getFromAllDataDep(department, tableDepartment,  5, searchD,"Select * from department",false);
                new FadeIn(root).play();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else if (event.getSource() == addP) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AddProject.fxml"));
                stage.setTitle("Our Big Project!!");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                getFromAllDataPro(project, tableProject,  10, searchP,"Select * from project",false);
                new FadeIn(root).play();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else if (event.getSource() == addProvider) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AddProvider.fxml"));
                stage.setTitle("Our Big Project!!");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                getFromAllDataProvider(provider, tableProvider,  11, searchProvider,"select D.Provider_ID,D.Name_Provider,D.Phone_number,D.country,D.city,D.street,D.Type_Provider,D.Dilivery_Time,D.Type_of_cars,D.Size_Cars,D.Driving_Lisence,D.Description,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item from Provider D, provider_provide_items DH ,Item i where D.Provider_ID = DH.Provider_ID And I.Item_ID = DH.Item_ID",false);
                new FadeIn(root).play();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else if (event.getSource() == addB) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AddBuyer.fxml"));
                stage.setTitle("Our Big Project!!");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                getFromAllDataBuyer(buyer, tableBuyer,  1, searchB,"select D.Buyer_ID,D.Name_Buyer,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item,DD.Department_ID from Buyer D, Buyer_Buy_Items DH,Item i, Department DD where D.Buyer_ID = DH.Buyer_ID And I.Item_ID = DH.Item_ID And DD.Department_ID = DH.Department_ID Order by D.Name_Buyer",false);
                new FadeIn(root).play();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }

    @FXML
    void DeleteListener(ActionEvent event) {
        Connection con;
        OracleDataSource ods;
        try {
            ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            con = ods.getConnection();
            con.setAutoCommit(false);
        if (event.getSource() == DeleteE) {
            String exe = searchEID.getText();
            searchEID.setText("");
            if(exe.isBlank() || exe.isEmpty() || exe == null) {
                con.close();
                return;
            }
            String all = "delete from Employee where SSN = "+exe;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(all);
            con.commit();
            con.close();
            getFromAllDataEmp(employee, tableEmployee,  18, searchE,"Select * from employee",false);
        }
        else if (event.getSource() == DeleteD) {
            String exe = searchDID.getText();
            searchDID.setText("");
            if(exe.isBlank() || exe.isEmpty() || exe == null) {
                con.close();
                return;
            }
            String all = "delete from Department where Department_ID = "+exe;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(all);
            con.commit();
            con.close();
            getFromAllDataDep(department, tableDepartment,  5, searchD,"select D.Department_ID,D.Type,D.Hours_Working,D.country,D.city,D.Street,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item from Department D, department_have_items DH,Item i where D.Department_ID = DH.Department_ID And I.Item_ID = DH.Item_ID",false);
        }
        else if (event.getSource() == DeleteP) {
            String exe = searchPID.getText();
            searchPID.setText("");
            if(exe.isBlank() || exe.isEmpty() || exe == null) {
                con.close();
                return;
            }
            String all = "delete from Project where Project_ID = "+exe;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(all);
            con.commit();
            con.close();
            getFromAllDataPro(project, tableProject,  10, searchP,"Select * from project",false);
        }
        else if (event.getSource() == DeleteProvider) {
            String exe = searchProviderID.getText();
            searchProviderID.setText("");
            if(exe.isBlank() || exe.isEmpty() || exe == null) {
                con.close();
                return;
            }
            String all = "delete from Provider where Provider_ID = "+exe;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(all);
            con.commit();
            con.close();
            getFromAllDataProvider(provider, tableProvider,  11, searchProvider,"select D.Provider_ID,D.Name_Provider,D.Phone_number,D.country,D.city,D.street,D.Type_Provider,D.Dilivery_Time,D.Type_of_cars,D.Size_Cars,D.Driving_Lisence,D.Description,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item from Provider D, provider_provide_items DH ,Item i where D.Provider_ID = DH.Provider_ID And I.Item_ID = DH.Item_ID",false);
        }
        else if (event.getSource() == DeleteB) {
            String exe = searchBID.getText();
            searchBID.setText("");
            if(exe.isBlank() || exe.isEmpty() || exe == null) {
                con.close();
                return;
            }
            String all = "delete from Buyer where Buyer_ID = '"+exe+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(all);
            con.commit();
            con.close();
            getFromAllDataBuyer(buyer, tableBuyer,  1, searchB,"select D.Buyer_ID,D.Name_Buyer,DH.Quantity,I.Item_ID,I.Name_Item,I.Color,I.Size_Item,DD.Department_ID from Buyer D, Buyer_Buy_Items DH,Item i, Department DD where D.Buyer_ID = DH.Buyer_ID And I.Item_ID = DH.Item_ID And DD.Department_ID = DH.Department_ID Order by D.Name_Buyer",false);
        }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void getFromAllDataBuyer(ObservableList<ObservableList> datadepartment, TableView tabledepartment, int columnNumber, TextField searchText,String qry2,boolean flag) {
        try {
            datadepartment = FXCollections.observableArrayList();
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String qry = qry2 ;
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(qry);
            if(flag == true) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            if (param.getValue().get(j) == null) return new SimpleStringProperty("");
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });

                    tabledepartment.getColumns().add(col);
                }
            }
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    if (rs.getString(i) == null) row.add("null");
                    else row.add(rs.getString(i));
                }
                datadepartment.add(row);

            }
            //FINALLY ADDED TO TableView
            tabledepartment.setItems(datadepartment);
            FilteredList<ObservableList> filter = new FilteredList<>(datadepartment, b -> true);
            if(flag == true) {
                searchText.textProperty().addListener((observable, oldValue, newValue) -> {
                    filter.setPredicate(ObservableList -> {
                        if (newValue.isBlank() || newValue.isEmpty() || newValue == null) {
                            return true;
                        }
                        try {
                            String SearchKey = newValue.toLowerCase();
                            if (selectedComboBuyer != -1) {
                                if (ObservableList.get(selectedComboBuyer).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                                return false;
                            }

                            for (int i = 0; i <= columnNumber; i++) {
                                if (ObservableList.get(i).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                            }
                            return false;
                        } catch (Exception e) {
                            return false;
                        }
                    });
                });
            }
            SortedList<ObservableList> sortedData = new SortedList<>(filter);
            sortedData.comparatorProperty().bind(tabledepartment.comparatorProperty());
            tabledepartment.setItems(sortedData);
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getFromAllDataProvider(ObservableList<ObservableList> datadepartment, TableView tabledepartment, int columnNumber, TextField searchText,String qry2,boolean flag) {
        try {
            datadepartment = FXCollections.observableArrayList();
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String qry = qry2 ;
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(qry);
            if(flag == true) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            if (param.getValue().get(j) == null) return new SimpleStringProperty("");
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });

                    tabledepartment.getColumns().add(col);
                }
            }
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    if (rs.getString(i) == null) row.add("null");
                    else row.add(rs.getString(i));
                }
                datadepartment.add(row);

            }
            //FINALLY ADDED TO TableView
            tabledepartment.setItems(datadepartment);
            FilteredList<ObservableList> filter = new FilteredList<>(datadepartment, b -> true);
            if(flag == true) {
                searchText.textProperty().addListener((observable, oldValue, newValue) -> {
                    filter.setPredicate(ObservableList -> {
                        if (newValue.isBlank() || newValue.isEmpty() || newValue == null) {
                            return true;
                        }
                        try {
                            String SearchKey = newValue.toLowerCase();
                            if (selectedComboProvider != -1) {
                                if (ObservableList.get(selectedComboProvider).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                                return false;
                            }

                            for (int i = 0; i <= columnNumber; i++) {
                                if (ObservableList.get(i).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                            }
                            return false;
                        } catch (Exception e) {
                            return false;
                        }
                    });
                });
            }
            SortedList<ObservableList> sortedData = new SortedList<>(filter);
            sortedData.comparatorProperty().bind(tabledepartment.comparatorProperty());
            tabledepartment.setItems(sortedData);
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getFromAllDataPro(ObservableList<ObservableList> datadepartment, TableView tabledepartment, int columnNumber, TextField searchText,String qry2,boolean flag) {
        try {
            datadepartment = FXCollections.observableArrayList();
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String qry = qry2 ;
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(qry);
            if(flag == true) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            if (param.getValue().get(j) == null) return new SimpleStringProperty("");
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });

                    tabledepartment.getColumns().add(col);
                }
            }
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    if (rs.getString(i) == null) row.add("null");
                    else row.add(rs.getString(i));
                }
                datadepartment.add(row);

            }
            //FINALLY ADDED TO TableView
            tabledepartment.setItems(datadepartment);
            FilteredList<ObservableList> filter = new FilteredList<>(datadepartment, b -> true);
            if(flag == true) {
                searchText.textProperty().addListener((observable, oldValue, newValue) -> {
                    filter.setPredicate(ObservableList -> {
                        if (newValue.isBlank() || newValue.isEmpty() || newValue == null) {
                            return true;
                        }
                        try {
                            String SearchKey = newValue.toLowerCase();
                            if (selectedComboProject != -1) {
                                if (ObservableList.get(selectedComboProject).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                                return false;
                            }

                            for (int i = 0; i <= columnNumber; i++) {
                                if (ObservableList.get(i).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                            }
                            return false;
                        } catch (Exception e) {
                            return false;
                        }
                    });
                });
            }
            SortedList<ObservableList> sortedData = new SortedList<>(filter);
            sortedData.comparatorProperty().bind(tabledepartment.comparatorProperty());
            tabledepartment.setItems(sortedData);
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getFromAllDataEmp(ObservableList<ObservableList> datadepartment, TableView tabledepartment, int columnNumber, TextField searchText,String qry2,boolean flag) {
        try {
            employee = FXCollections.observableArrayList();
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String qry = qry2 ;
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(qry);
            if(flag == true) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            if (param.getValue().get(j) == null) return new SimpleStringProperty("");
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });

                    tabledepartment.getColumns().add(col);
                }
            }
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    if (rs.getString(i) == null) row.add("null");
                    else row.add(rs.getString(i));
                }
                employee.add(row);

            }
            //FINALLY ADDED TO TableView
            tabledepartment.setItems(employee);
            filterEmp = new FilteredList<>(employee, b -> true);
            if(flag == true) {
                searchText.textProperty().addListener((observable, oldValue, newValue) -> {
                    filterEmp.setPredicate(ObservableList -> {
                        if (newValue.isBlank() || newValue.isEmpty() || newValue == null) {
                            return true;
                        }
                        try {
                            String SearchKey = newValue.toLowerCase();
                            if (selectedComboEmployee != -1) {
                                if (ObservableList.get(selectedComboEmployee).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                                return false;
                            }

                            for (int i = 0; i <= columnNumber; i++) {
                                if (ObservableList.get(i).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                            }
                            return false;
                        } catch (Exception e) {
                            return false;
                        }
                    });
                });
            }
            SortedList<ObservableList> sortedData = new SortedList<>(filterEmp);
            sortedData.comparatorProperty().bind(tabledepartment.comparatorProperty());
            tabledepartment.setItems(sortedData);
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getFromAllDataDep(ObservableList<ObservableList> datadepartment, TableView tabledepartment, int columnNumber, TextField searchText,String qry2,boolean flag) {
        try {
            datadepartment = FXCollections.observableArrayList();
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String qry = qry2 ;
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(qry);
            if(flag == true) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                            if (param.getValue().get(j) == null) return new SimpleStringProperty("");
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });

                    tabledepartment.getColumns().add(col);
                }
            }
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    if (rs.getString(i) == null) row.add("null");
                    else row.add(rs.getString(i));
                }
                datadepartment.add(row);

            }
            //FINALLY ADDED TO TableView
            tabledepartment.setItems(datadepartment);
            FilteredList<ObservableList> filter = new FilteredList<>(datadepartment, b -> true);
            if(flag == true) {
                searchText.textProperty().addListener((observable, oldValue, newValue) -> {
                    filter.setPredicate(ObservableList -> {
                        if (newValue.isBlank() || newValue.isEmpty() || newValue == null) {
                            return true;
                        }
                        try {
                            String SearchKey = newValue.toLowerCase();
                            if (selectedComboDepartment != -1) {
                                if (ObservableList.get(selectedComboDepartment).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                                return false;
                            }

                            for (int i = 0; i <= columnNumber; i++) {
                                if (ObservableList.get(i).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                            }
                            return false;
                        } catch (Exception e) {
                            return false;
                        }
                    });
                });
            }
            SortedList<ObservableList> sortedData = new SortedList<>(filter);
            sortedData.comparatorProperty().bind(tabledepartment.comparatorProperty());
            tabledepartment.setItems(sortedData);
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleClicksItems(MouseEvent mouseEvent) {
        AnchorItems.toFront();
        if (mouseEvent.getSource() == VBoxOutDoor) {
            text_state.setText("OutDoor");
            Indicator = 0;
        } else if (mouseEvent.getSource() == VBoxInDoor) {
            text_state.setText("InDoor");
            Indicator = 1;
        } else if (mouseEvent.getSource() == VBoxArt) {
            text_state.setText("Art");
            Indicator = 2;
        } else if (mouseEvent.getSource() == VBoxPlants) {
            text_state.setText("Plants");
            Indicator = 3;
        } else if (mouseEvent.getSource() == VBoxEqu) {
            text_state.setText("Equipment");
            Indicator = 4;
        } else if (mouseEvent.getSource() == VBoxOthers) {
            text_state.setText("Others");
            Indicator = 5;
        } else if (mouseEvent.getSource() == VBoxGrass) {
            text_state.setText("Grass");
            Indicator = 6;
        }
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select * from Item";
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(all);
            gridItems.getChildren().clear();
            int column = 0;
            int row = 1;
            datadepartment = FXCollections.observableArrayList();
            while (rs.next()) {
                if (rs.getString(3).equals(Items[Indicator])) {
                    ObservableList<String> row1 = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        //Iterate Column
                        if (rs.getString(i) == null) row1.add("null");
                        else row1.add(rs.getString(i));
                    }
                    datadepartment.add(row1);
                }
            }
            gridItems.getChildren().clear();
            for (int i = 0; i < datadepartment.size(); i++) {
                FXMLLoader fxmlLoad = new FXMLLoader();
                fxmlLoad.setLocation(getClass().getResource("Items.fxml"));
                HBox Item = fxmlLoad.load();
                ItemController = fxmlLoad.getController();
                boolean ava;
                if (Integer.parseInt(datadepartment.get(i).get(4).toString()) > 0) ava = true;
                else ava = false;
                ItemController.SetData(datadepartment.get(i).get(1).toString(), datadepartment.get(i).get(6).toString(), datadepartment.get(i).get(3).toString(), datadepartment.get(i).get(7).toString(), ava, true);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                gridItems.add(Item, column++, row);
                GridPane.setMargin(Item, new Insets(20));
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void SearchActionItem(ActionEvent actionEvent) {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select * from Item";
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(all);
            gridItems.getChildren().clear();
            int column = 0;
            int row = 1;
            datadepartment = FXCollections.observableArrayList();
            while (rs.next()) {
                if (rs.getString(3).equals(Items[Indicator])) {
                    ObservableList<String> row1 = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        //Iterate Column
                        if (rs.getString(i) == null) row1.add("null");
                        else row1.add(rs.getString(i));
                    }
                    datadepartment.add(row1);
                }
            }
            FilteredList<ObservableList> filter = new FilteredList<>(datadepartment, b -> true);
            String newValue = TextS.getText();
            filter.setPredicate(ObservableList -> {
                try {
                    if(TextS.getText().isEmpty()) {
                        return true;
                    }
                    String SearchKey = newValue.toLowerCase();
                    if (ComboSearchItem.getSelectionModel().getSelectedItem().equals("Name")) {
                        if (ObservableList.get(1).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        }
                    } else if (ComboSearchItem.getSelectionModel().getSelectedItem().equals("Salary less than")) {
                        if (Integer.parseInt(ObservableList.get(7).toString()) <= (Integer.parseInt(SearchKey))) {
                            return true;
                        }
                    } else {
                        if (Integer.parseInt(ObservableList.get(7).toString()) >= (Integer.parseInt(SearchKey))) {
                            return true;
                        }
                    }
                    return false;
                } catch (Exception e) {
                    return false;
                }
            });
            SortedList<ObservableList> sortedData = new SortedList<>(filter);
            gridItems.getChildren().clear();
            for (int i = 0; i < sortedData.size(); i++) {
                FXMLLoader fxmlLoad = new FXMLLoader();
                fxmlLoad.setLocation(getClass().getResource("Items.fxml"));
                HBox Item = fxmlLoad.load();
                ItemController = fxmlLoad.getController();
                boolean ava;
                if (Integer.parseInt(sortedData.get(i).get(4).toString()) > 0) ava = true;
                else ava = false;
                ItemController.SetData(sortedData.get(i).get(1).toString(), sortedData.get(i).get(6).toString(), sortedData.get(i).get(3).toString(), sortedData.get(i).get(7).toString(), ava, true);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                gridItems.add(Item, column++, row);
                GridPane.setMargin(Item, new Insets(20));
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}