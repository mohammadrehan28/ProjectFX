package com.example.projectfx;

import animatefx.animation.FadeIn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import oracle.jdbc.pool.OracleDataSource;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DepartmentViewController implements Initializable {
    public TableView tableDepartment;

    @FXML
    public ObservableList<ObservableList> department;

    static ObservableList<String> EmpUbdate = FXCollections.observableArrayList();
    static boolean Flag = false;

    public void TableEmpListener(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            Flag = true;
            EmpUbdate = (ObservableList<String>) tableDepartment.getSelectionModel().getSelectedItem();
            Stage stage = (Stage) tableDepartment.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Flag = false;
        getFromAllDataDep(department, tableDepartment,  5,"select D.Department_ID,D.Type,D.Hours_Working,D.country,D.city,D.Street from Department D, department_have_items DH,Item i where D.Department_ID = DH.Department_ID And I.Item_ID = DH.Item_ID And I.Item_ID = "+BuyerController.IDItem+"",true);
    }

    public void getFromAllDataDep(ObservableList<ObservableList> datadepartment, TableView tabledepartment, int columnNumber, String qry2, boolean flag) {
        try {
            datadepartment = FXCollections.observableArrayList();
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String qry = qry2 ;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            if(flag == true) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
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
            SortedList<ObservableList> sortedData = new SortedList<>(filter);
            sortedData.comparatorProperty().bind(tabledepartment.comparatorProperty());
            tabledepartment.setItems(sortedData);
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
