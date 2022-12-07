package com.example.projectfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oracle.jdbc.*;
import javafx.stage.StageStyle;
import oracle.jdbc.pool.OracleDataSource;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

    public TextField Text1;
    public TextField Text2;
    public TextArea Text3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onButton(ActionEvent actionEvent) {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url,"mohammad","123456");
            con.setAutoCommit(false);
            String Id = Text1.getText();
            String name = Text2.getText();
            Text1.setText("");
            Text2.setText("");
            String all = "Insert into Temp values('"+Id+"','"+name+"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(all);
            con.commit();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onButtonSelect(ActionEvent actionEvent) {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            String all = "select * from Temp";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            Text3.setText("ID\tName\n");
            Text3.appendText("-------------------------\n");
            while(rs.next()){
                String ID = rs.getString(1);
                String Name = rs.getString(2);
                Text3.appendText(ID+"\t"+Name+"\n");
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
