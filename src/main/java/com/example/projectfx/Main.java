package com.example.projectfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import oracle.jdbc.pool.OracleDataSource;
import javafx.scene.control.TableColumn.CellDataFeatures;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javax.swing.*;
public class Main implements Initializable {

    @FXML
    private Button addE;
    @FXML
    private TableView table1;
    @FXML
    private TableView tabledepartment;
    @FXML
    private ObservableList<ObservableList> data;
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

    @Override
    public void initialize (URL location,ResourceBundle resources){
        //data.clear();
        //datadepartment.clear();
        getFromAllData(data, table1,"Employee");
        getFromAllData(datadepartment, tabledepartment, "Department");
    }
    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource()==employee_btn){
            text_state.setText("Employee");
            pane_state.setBackground(new Background(new BackgroundFill(Color.rgb(162, 217, 206), CornerRadii.EMPTY, Insets.EMPTY)));
            grid1.toFront();
            //getFromAllData(data, table1,"Employee");
        }
        else  if(event.getSource()==department_btn){
            text_state.setText("Department");
            pane_state.setBackground(new Background(new BackgroundFill(Color.rgb(162, 217, 206), CornerRadii.EMPTY, Insets.EMPTY)));
            grid2.toFront();
            //getFromAllData(datadepartment, tabledepartment, "Department");
        }
        else if(event.getSource()==item_btn){
            text_state.setText("Item");
            pane_state.setBackground(new Background(new BackgroundFill(Color.rgb(162, 217, 206), CornerRadii.EMPTY, Insets.EMPTY)));
            grid3.toFront();

        }
        else if(event.getSource()==project_btn){
            text_state.setText("Project");
            pane_state.setBackground(new Background(new BackgroundFill(Color.rgb(162, 217, 206), CornerRadii.EMPTY, Insets.EMPTY)));
            grid4.toFront();
        }
        else if(event.getSource()==provider_btn){
            text_state.setText("Provider");
            pane_state.setBackground(new Background(new BackgroundFill(Color.rgb(162, 217, 206), CornerRadii.EMPTY, Insets.EMPTY)));
            grid5.toFront();
        }
        else if(event.getSource()==buyer_btn){
            text_state.setText("Buyer");
            pane_state.setBackground(new Background(new BackgroundFill(Color.rgb(162, 217, 206), CornerRadii.EMPTY, Insets.EMPTY)));
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
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void getFromAllData(ObservableList<ObservableList> datadepartment, TableView tabledepartment, String name) {
        try {
            datadepartment = FXCollections.observableArrayList();
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String qry = "Select * from " + name;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        if(param.getValue().get(j)==null) return new SimpleStringProperty("");
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tabledepartment.getColumns().add(col);
            }

            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                datadepartment.add(row);

            }

            //FINALLY ADDED TO TableView
            tabledepartment.setItems(datadepartment);
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
