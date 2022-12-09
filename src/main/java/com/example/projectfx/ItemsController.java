package com.example.projectfx;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemsController implements Initializable {

    public Label NameLabel;
    public Label SizeLabel;
    public Label ColorLabel;
    public Label PrizeLabel;
    public ImageView Available;
    public ImageView Delivery;
    public void SetData(String Name, String Size, String Color, String Prize, boolean Available, boolean Delivery) {
        NameLabel.setText(Name);
        SizeLabel.setText(Size);
        ColorLabel.setText(Color);
        PrizeLabel.setText(Prize);
        Image image;
        Image image2;
        if(Available) image = new Image(getClass().getResourceAsStream("@Ava.png"));
        else image = new Image(getClass().getResourceAsStream("@NotAva.png"));
        if(Delivery) image2 = new Image(getClass().getResourceAsStream("@Del.png"));
        else image2 = new Image(getClass().getResourceAsStream("@NotDel.png"));
        this.Available.setImage(image);
        this.Delivery.setImage(image2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
