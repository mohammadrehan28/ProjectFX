package com.example.projectfx;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemsController implements Initializable {

    public Label NameLabel;
    public Label NameLabel2;
    public Label SizeLabel;
    public Label ColorLabel;
    public Label PrizeLabel;
    public ImageView Available;
    public ImageView Delivery;
    public HBox VBoxAll;

    public void SetData(String Name, String Size, String Color1, String Prize, boolean Available, boolean Delivery) {
        NameLabel.setText(Name);
        NameLabel.setFont(Font.font(14));
        NameLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            //create temp Text object with the same text as the label
            //and measure its width using default label font size
            newValue = "Name: " + newValue;
            Text tmpText = new Text(newValue);
            tmpText.setFont(Font.font(14));

            double textWidth = tmpText.getLayoutBounds().getWidth();

            //check if text width is smaller than maximum width allowed
            if (textWidth <= 131) {
                NameLabel.setFont(Font.font(14));
                NameLabel2.setFont(Font.font(14));
            } else {
                //and if it isn't, calculate new font size,
                // so that label text width matches MAX_TEXT_WIDTH
                double newFontSize = 14 * 131 / textWidth;
                NameLabel.setFont(Font.font(Font.font(14).getFamily(), newFontSize));
                NameLabel2.setFont(Font.font(Font.font(14).getFamily(), newFontSize));
            }
        });
        SizeLabel.setText(Size);
        ColorLabel.setText(Color1);
        PrizeLabel.setText(Prize);
        Image image;
        Image image2;
        if(Available){
            try {
                InputStream stream = new FileInputStream("C:/Users/MohammadRehan/IdeaProjects/ProjectFX/src/main/resources/com/example/projectfx/Ava2.png");
                image = new Image(stream);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                InputStream stream = new FileInputStream("C:/Users/MohammadRehan/IdeaProjects/ProjectFX/src/main/resources/com/example/projectfx/NotAva2.png");
                image = new Image(stream);
                VBoxAll.setStyle("-fx-border-color: #FC6464; -fx-border-width: 7px; -fx-border-radius: 10px; -fx-background-color: #ffffff; -fx-background-radius: 10px;");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if(Delivery) {
            try {
                InputStream stream = new FileInputStream("C:/Users/MohammadRehan/IdeaProjects/ProjectFX/src/main/resources/com/example/projectfx/Del2.png");
                image2 = new Image(stream);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                InputStream stream = new FileInputStream("C:/Users/MohammadRehan/IdeaProjects/ProjectFX/src/main/resources/com/example/projectfx/NotDel2.png");
                image2 = new Image(stream);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        this.Available.setImage(image);
        this.Delivery.setImage(image2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
