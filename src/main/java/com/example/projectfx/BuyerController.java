package com.example.projectfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BuyerController {

    public GridPane grid3;
    @FXML
    private Button exit_btn;
    @FXML
    private Label text_state;
    public VBox VBoxOutDoor;
    public VBox VBoxInDoor;
    public VBox VBoxArt;
    public VBox VBoxPlants;
    public VBox VBoxEqu;
    public VBox VBoxOthers;
    public VBox VBoxGrass;
    public ScrollPane scrollItems;
    public GridPane gridItems;
    public String Items[] = {"OutDoor","InDoor","Art","Plant","Equipment","Other","Grass"};
    public int Indicator = 0;

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

    @FXML
    public void handleClicksItems(MouseEvent mouseEvent) {
        scrollItems.toFront();
        if(mouseEvent.getSource() == VBoxOutDoor) {
            text_state.setText("OutDoor");
            Indicator = 0;
        }
        else if(mouseEvent.getSource() == VBoxInDoor) {
            text_state.setText("InDoor");
            Indicator = 1;
        }
        else if(mouseEvent.getSource() == VBoxArt) {
            text_state.setText("Art");
            Indicator = 2;
        }
        else if(mouseEvent.getSource() == VBoxPlants) {
            text_state.setText("Plants");
            Indicator = 3;
        }
        else if(mouseEvent.getSource() == VBoxEqu) {
            text_state.setText("Equipment");
            Indicator = 4;
        }
        else if(mouseEvent.getSource() == VBoxOthers) {
            text_state.setText("Others");
            Indicator = 5;
        }
        else if(mouseEvent.getSource() == VBoxGrass) {
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
            ResultSet rs = stmt.executeQuery(all);
            gridItems.getChildren().clear();
            ItemsController ItemController;
            int column = 0;
            int row = 1;
            while (rs.next()) {
                if(rs.getString(3).equals(Items[Indicator])) {
                    FXMLLoader fxmlLoad = new FXMLLoader();
                    fxmlLoad.setLocation(getClass().getResource("Items.fxml"));
                    HBox Item = fxmlLoad.load();
                    ItemController = fxmlLoad.getController();
                    boolean ava;
                    if(rs.getInt(5) > 0) ava = true;
                    else ava = false;
                    ItemController.SetData(rs.getString(2),rs.getString(7),rs.getString(4),rs.getString(8),ava,true);
                    if(column == 2){
                        column = 0;
                        row++;
                    }
                    gridItems.add(Item,column++,row);
                    GridPane.setMargin(Item,new Insets(20));
                }
            }
            con.close();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void LogoPressed(MouseEvent mouseEvent) {
        text_state.setText("Item");
        grid3.toFront();
    }
}
