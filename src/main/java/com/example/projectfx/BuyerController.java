package com.example.projectfx;

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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BuyerController implements Initializable {

    public AnchorPane AnchorItems;
    public ComboBox ComboSearchItem;
    public Button SearchItem;
    public TextField ItemID;
    public TextField BuyerID;
    public Button BuyItem;
    public Button ReturnItem;
    private ItemsController ItemController;
    private ResultSet rs;
    private ObservableList<ObservableList> datadepartment;
    public GridPane grid3;
    public TextField TextS;
    public ComboBox<String> selectedCombo;
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
    public int selectCombo = -1;

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
        AnchorItems.toFront();
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
            rs = stmt.executeQuery(all);
            gridItems.getChildren().clear();
            int column = 0;
            int row = 1;
            datadepartment = FXCollections.observableArrayList();
            while (rs.next()) {
                if(rs.getString(3).equals(Items[Indicator])) {
                    ObservableList<String> row1 = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        //Iterate Column
                        if (rs.getString(i) == null) row1.add("null");
                        else row1.add(rs.getString(i));
                    }
                    datadepartment.add(row1);
                }
            }
            FilteredList<ObservableList> filter = new FilteredList<>(datadepartment, b->true);
            TextS.textProperty().addListener((observable,oldValue,newValue)->{
                System.out.println(1);
                filter.setPredicate(ObservableList->{
                    if(newValue.isBlank()||newValue.isEmpty()||newValue==null){
                        return true;
                    }
                    try {
                        String SearchKey = newValue.toLowerCase();
                        if(selectCombo!=-1) {
                            if(selectCombo != 4) {
                                if (ObservableList.get(selectCombo).toString().toLowerCase().indexOf(SearchKey) > -1) {
                                    return true;
                                }
                            }
                            else {
                                if (Integer.parseInt(ObservableList.get(4).toString()) != 0) {
                                    return true;
                                }
                            }
                            return false;
                        }
                        if (ObservableList.get(1).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        }
                        else if (ObservableList.get(3).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        }
                        else if (ObservableList.get(4).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        }
                        else if (ObservableList.get(6).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        }
                        else if (ObservableList.get(7).toString().toLowerCase().indexOf(SearchKey) > -1) {
                            return true;
                        }
                        else return false;
                    }catch(Exception e){
                        return false;
                    }
                });
            });
            System.out.println(3);
            SortedList<ObservableList> sortedData = new SortedList<>(filter);
            gridItems.getChildren().clear();
            System.out.println(3);
            for(int i=0;i<sortedData.size();i++) {
                System.out.println(2);
                FXMLLoader fxmlLoad = new FXMLLoader();
                fxmlLoad.setLocation(getClass().getResource("Items.fxml"));
                HBox Item = fxmlLoad.load();
                ItemController = fxmlLoad.getController();
                boolean ava;
                if(Integer.parseInt(sortedData.get(i).get(4).toString()) > 0) ava = true;
                else ava = false;
                ItemController.SetData(sortedData.get(i).get(1).toString(),sortedData.get(i).get(6).toString(),sortedData.get(i).get(3).toString(),sortedData.get(i).get(7).toString(),ava,true,sortedData.get(i).get(0).toString());
                if(column == 2){
                    column = 0;
                    row++;
                }
                gridItems.add(Item,column++,row);
                GridPane.setMargin(Item,new Insets(20));
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

    public void SelectAction(ActionEvent actionEvent) {
        int A = selectedCombo.getSelectionModel().getSelectedIndex();
        if (A == 0) selectCombo = -1;
        else if(A == 1) selectCombo = 1;
        else if(A == 2) selectCombo = 3;
        else if(A == 3) selectCombo = 4;
        else if(A == 4) selectCombo = 6;
        else if(A == 5) selectCombo = 7;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //selectedCombo.getItems().addAll("All","Name","Color","Available","Size","Salary");
    }

    public void SearchActionItem(ActionEvent actionEvent) {
    }

    public void DeleteActionItem(ActionEvent actionEvent) { //Buy Item
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select Item_ID,Quantity from Item";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            if(ItemID.getText().isEmpty() || BuyerID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The ID is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            String ID = ItemID.getText();
            String Buy = BuyerID.getText();
            boolean Flag = true;
            while(rs.next()) {
                if(ID == rs.getString(1)) {
                    Flag = false;
                    if(Integer.parseInt(rs.getString(2)) == 0) {
                        JOptionPane.showMessageDialog(null, "Item ID does not Available", "ERROR", JOptionPane.ERROR_MESSAGE);
                        con.close();
                        throw new Exception();
                    }
                    break;
                }
            }
            if(Flag) {
                JOptionPane.showMessageDialog(null, "Item ID does not Contain", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            all = "select Buyer_ID from Buyer";
            rs = stmt.executeQuery(all);
            Flag = true;
            while(rs.next()) {
                if(Buy == rs.getString(1)) {
                    Flag = false;
                    break;
                }
            }
            if(Flag) {
                JOptionPane.showMessageDialog(null, "Buy ID does not Contain", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            //TOGO
            all = "UPDATE Item\n" +
                    "SET Quantity = Quantity - 1\n" +
                    "WHERE Itme_ID = "+ID;
            stmt.executeUpdate(all);
            all = "select * from Buyer_Buy_Items";
            rs = stmt.executeQuery(all);
            Flag = true;
            while(rs.next()) {
                if(ID == rs.getString(1) && Buy == rs.getString(2)) {
                    Flag = false;
                    all = "UPDATE Buyer_Buy_Items\n" +
                            "SET Quantity = Quantity + 1\n" +
                            "WHERE Itme_ID = "+ID+" and Buyer_ID = '" + Buy+"'";
                    stmt.executeUpdate(all);
                    break;
                }
            }
            if(Flag) {
                all = "INSERT INTO Employee values('"+ID+"','"+Buy+"',1,)";
                stmt.executeUpdate(all);
            }
            con.commit();
            con.close();
        }
        catch(Exception e) {
            //System.out.println(e);
        }
    }

    public void AddActionItem(ActionEvent actionEvent) { //Return Item
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select Item_ID from Item";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            if(ItemID.getText().isEmpty() || BuyerID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The ID is Empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            String ID = ItemID.getText();
            String Buy = BuyerID.getText();
            boolean Flag = true;
            while(rs.next()) {
                if(ID == rs.getString(1)) {
                    Flag = false;
                    break;
                }
            }
            if(Flag) {
                JOptionPane.showMessageDialog(null, "Item ID does not Contain", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            all = "select Buyer_ID from Buyer";
            rs = stmt.executeQuery(all);
            Flag = true;
            while(rs.next()) {
                if(Buy == rs.getString(1)) {
                    Flag = false;
                    break;
                }
            }
            if(Flag) {
                JOptionPane.showMessageDialog(null, "Buy ID does not Contain", "ERROR", JOptionPane.ERROR_MESSAGE);
                con.close();
                throw new Exception();
            }
            //TOGO
            JOptionPane.showMessageDialog(null, "We are Up your Request Success", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            con.close();
        }
        catch(Exception e) {
            //System.out.println(e);
        }
    }
}
