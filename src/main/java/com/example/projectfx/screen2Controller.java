package com.example.projectfx;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import oracle.jdbc.pool.OracleDataSource;
import javafx.scene.control.TableColumn.CellDataFeatures;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class screen2Controller implements Initializable {

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
    private TableView tableEmployee;
    @FXML
    private TableView tableDepartment;
    @FXML
    private TableView tableProject;
    @FXML
    private TableView tableProvider;
    @FXML
    private TableView tableBuyer;
    @FXML
    private TextField searchText;
    @FXML
    private ResultSet rs;


    @FXML
    private ObservableList<ObservableList> employee;
    @FXML
    private ObservableList<ObservableList> department;
    @FXML
    private ObservableList<ObservableList> project;
    @FXML
    private ObservableList<ObservableList> provider;
    @FXML
    private ObservableList<ObservableList> buyer;
    @FXML
    private ObservableList<ObservableList> datadepartment;

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
    private TextField searchB;

    @FXML
    private TextField searchD;

    @FXML
    private TextField searchE;

    @FXML
    private TextField searchP;

    @FXML
    private TextField searchProvider;
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
    //End For Items

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //data.clear();
        //datadepartment.clear();
        getFromAllData(employee, tableEmployee,  18, searchE, -1,"Select * from employee");
        getFromAllData(department, tableDepartment,  5, searchD, -1,"Select * from department");
        getFromAllData(project, tableProject,  10, searchP, -1,"Select * from project");
        getFromAllData(provider, tableProvider,  11, searchProvider, -1,"Select * from provider");
        getFromAllData(buyer, tableBuyer,  1, searchB, -1,"Select * from buyer");
        // Ecombo =new ComboBox<>();
        Ecombo.getItems().addAll("Maneger", "Driver", "Nursery", "Project", "All");
        selectedCombo.getItems().addAll("All","Name","Color","Available","Size","Salary");
        pane_state.setBackground(new Background(new BackgroundFill(Color.rgb(162, 217, 206), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    @FXML
    public void itemStateChanged(ActionEvent event) {
        if (Ecombo.getSelectionModel().getSelectedItem().equals("All")) {
            getFromAllData(employee, tableEmployee,  18, searchE, -1,"Select * from employee");
        } else if (Ecombo.getSelectionModel().getSelectedItem().equals("Maneger")) {
            getFromAllData(employee, tableEmployee,  18, searchE, 9,"Select * from employee ");
        } else if (Ecombo.getSelectionModel().getSelectedItem().equals("Driver")) {
            getFromAllData(employee, tableEmployee,  18, searchE, 12,"Select * from employee");
        } else if (Ecombo.getSelectionModel().getSelectedItem().equals("Nursery")) {
            getFromAllData(employee, tableEmployee,  18, searchE, 14,"Select * from employee");
        } else if (Ecombo.getSelectionModel().getSelectedItem().equals("Project")) {
            getFromAllData(employee, tableEmployee, 18, searchE, 17,"Select * from employee");
        }
    }

    public void itemcheck(ActionEvent event) {
        if(checkEM.isSelected()){
            getFromAllData(employee, tableEmployee,  18, searchE, 6,"Select * from employee where GENDER='M'");

        }
        else if(checkEF.isSelected()){
            getFromAllData(employee, tableEmployee,  18, searchE, 6,"Select * from employee where GENDER='F'");
        }
        else if (!checkEM.isSelected() && !checkEF.isSelected()){
            getFromAllData(employee, tableEmployee,  18, searchE, 6,"Select * from employee");
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
    void AddListener(ActionEvent event) {
        if (event.getSource() == addE) {
            
        }
        else if (event.getSource() == addD) {

        }
        else if (event.getSource() == addP) {

        }
        else if (event.getSource() == addProvider) {

        }
        else if (event.getSource() == addB) {

        }
    }

    @FXML
    void DeleteListener(ActionEvent event) {
        if (event.getSource() == DeleteE) {

        }
        else if (event.getSource() == DeleteD) {

        }
        else if (event.getSource() == DeleteP) {

        }
        else if (event.getSource() == DeleteProvider) {

        }
        else if (event.getSource() == DeleteB) {

        }
    }

    public void getFromAllData(ObservableList<ObservableList> datadepartment, TableView tabledepartment, int columnNumber, TextField searchText, int selectedCombo,String qry2) {
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
            searchText.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(ObservableList -> {
                    if (newValue.isBlank() || newValue.isEmpty() || newValue == null) {
                        return true;
                    }
                    try {
                        String SearchKey = newValue.toLowerCase();
                        if (selectedCombo != -1) {
                            if (ObservableList.get(selectedCombo).toString().toLowerCase().indexOf(SearchKey) > -1) {
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
            SortedList<ObservableList> sortedData = new SortedList<>(filter);
            sortedData.comparatorProperty().bind(tabledepartment.comparatorProperty());
            tabledepartment.setItems(sortedData);
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void SelectAction(ActionEvent actionEvent) {
        int A = selectedCombo.getSelectionModel().getSelectedIndex();
        if (A == 0) selectCombo = -1;
        else if(A == 1) selectCombo = 1;
        else if(A == 2) selectCombo = 3;
        else if(A == 3) selectCombo = 4;
        else if(A == 4) selectCombo = 6;
        else if(A == 5) selectCombo = 7;
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
            FilteredList<ObservableList> filter = new FilteredList<>(datadepartment, b -> true);
            TextS.textProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println(1);
                filter.setPredicate(ObservableList -> {
                    if (newValue.isBlank() || newValue.isEmpty() || newValue == null) {
                        return true;
                    }
                    try {
                        String SearchKey = newValue.toLowerCase();
                        if (selectCombo != -1) {
                            if (selectCombo != 4) {
                                if (ObservableList.get(selectCombo).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                            } else {
                                if (Integer.parseInt(ObservableList.get(4).toString()) != 0) {
                                    return true;
                                }
                            }
                            return false;
                        }
                        if (ObservableList.get(1).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        } else if (ObservableList.get(3).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        } else if (ObservableList.get(4).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        } else if (ObservableList.get(6).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        } else if (ObservableList.get(7).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        } else return false;
                    } catch (Exception e) {
                        return false;
                    }
                });
            });
            System.out.println(3);
            SortedList<ObservableList> sortedData = new SortedList<>(filter);
            gridItems.getChildren().clear();
            System.out.println(3);
            for (int i = 0; i < sortedData.size(); i++) {
                System.out.println(2);
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