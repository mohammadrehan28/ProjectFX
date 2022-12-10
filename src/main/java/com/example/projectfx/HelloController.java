package com.example.projectfx;

import animatefx.animation.FadeIn;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public Button CloseB;
    public TextField PasswordText;
    public TextField UserNameText;
    public Button LoginB;
    public Label IncorrectLabel;
    public BorderPane All;
    public AnchorPane TopAnchor;
    public Label LogText;
    public AnchorPane BottomAnchor;
    public boolean FlagLog = true;
    public ImageView SSNImage;
    public Label SSNLabel;
    public TextField SSNText;
    private double xOffset = 0;
    private double yOffset = 0;

    public void LoginButton(ActionEvent actionEvent) throws IOException {
        try {
            if(FlagLog) {
                OracleDataSource ods = new OracleDataSource();
                ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                ods.setUser("mohammad");
                ods.setPassword("123456");
                Connection con = ods.getConnection();
                String all = "select * from Admin_User";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(all);
                while (rs.next()) {
                    String ID = rs.getString(1);
                    String Password = rs.getString(2);
                    if (UserNameText.getText().equals(ID) && !PasswordText.getText().equals(Password)) {
                        IncorrectLabel.setText("Invalid Password");
                        break;
                    } else if (UserNameText.getText().equals(ID) && PasswordText.getText().equals(Password)) {
                        FXMLLoader fxmlLoader;
                        if(rs.getString(3).equals("Admin")) {
                            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("screen2.fxml"));
                        }
                        else if(rs.getString(3).equals("User")) {
                            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("screen2.fxml"));
                        }
                        else {
                            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Buyer.fxml"));
                        }
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = (Stage) LoginB.getScene().getWindow();
                        stage.setScene(scene);
                        break;
                    }
                }
                if (!rs.next()) IncorrectLabel.setText("Invalid UserName");
                con.close();
            }
            else {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                String url = "jdbc:oracle:thin:@localhost:1521:xe";
                Connection con = DriverManager.getConnection(url,"mohammad","123456");
                con.setAutoCommit(false);
                String all = "Select Buyer_id from Buyer";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(all);
                String name = UserNameText.getText();
                String Pass = PasswordText.getText();
                String SSN = SSNText.getText();
                while(rs.next()) {
                    if(rs.getString(1).equals(SSNText.getText())) break;
                }
                if(!rs.next()) {
                    all = "INSERT INTO Buyer values('"+SSN+"','"+name+"')";
                    stmt.executeUpdate(all);
                }
                UserNameText.setText("");
                PasswordText.setText("");
                SSNText.setText("");
                all = "INSERT INTO Admin_User values('"+name+"','"+Pass+"','Buyer','"+SSN+"')";
                stmt.executeUpdate(all);
                con.commit();
                con.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void CloseButton(ActionEvent actionEvent) {
        TranslateTransition T1 = new TranslateTransition(Duration.seconds(1), TopAnchor);
        TranslateTransition T2 = new TranslateTransition(Duration.seconds(1), BottomAnchor);
        if(FlagLog) {
            T1.setToY(TopAnchor.getLayoutY() - 70);
            T2.setToY(BottomAnchor.getLayoutY() - 230);
            CloseB.setText("SignIn");
            new FadeIn(SSNImage).play();
            new FadeIn(SSNText).play();
            new FadeIn(SSNLabel).play();
            SSNImage.setVisible(true);
            SSNText.setVisible(true);
            SSNLabel.setVisible(true);
            LogText.setText("SignUp Info");
            LoginB.setText("SignUp");
        }
        else {
            T1.setToY(TopAnchor.getLayoutY() - 30);
            T2.setToY(BottomAnchor.getLayoutY() - 250);
            CloseB.setText("SignUp");
            LogText.setText("Login Info");
            LoginB.setText("Login");
            SSNImage.setVisible(true);
            SSNText.setVisible(true);
            SSNLabel.setVisible(true);
        }
        T1.play();
        T2.play();
        T1.setOnFinished((e) -> {
            if(FlagLog) {
                SSNImage.setVisible(true);
                SSNText.setVisible(true);
                SSNLabel.setVisible(true);
                FlagLog = false;
            }
            else {
                SSNImage.setVisible(false);
                SSNText.setVisible(false);
                SSNLabel.setVisible(false);
                FlagLog = true;
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SSNImage.setVisible(false);
        SSNText.setVisible(false);
        SSNLabel.setVisible(false);
    }

    public void MousePressListener(MouseEvent mouseEvent) {
        xOffset = mouseEvent.getSceneX() + 8;
        yOffset = mouseEvent.getSceneY() + 30;
    }

    public void MouseReleaseListener(MouseEvent mouseEvent) {
        Stage stage = (Stage) All.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - xOffset);
        stage.setY(mouseEvent.getScreenY() - yOffset);
    }
}