package com.example.demo5.Controller;

import com.example.demo5.Models.data.DBConnection;
import com.example.demo5.Models.product;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductDetail {
    public Scene displayProductById(DBConnection db, VBox vBox, int id, Stage window,Scene userPage){
        product pro = db.getProductById(id);
        Label title = new Label("Product detail");
        Image linkImage = new Image(pro.image);
        ImageView imgView = new ImageView();
        imgView.setImage(linkImage);
        imgView.setFitWidth(100);
        imgView.setFitHeight(100);
        Label txtName = new Label("Name: ");
        Label name = new Label(pro.name);
        Label txtPrice = new Label("Price: ");
        Label price = new Label(""+pro.price);
        Label txtNSX = new Label("Producer: ");
        Label NSX = new Label(pro.NSX);
        Label txtDes = new Label("Description: ");
        Label description = new Label(pro.description);
        Label txtQuantity = new Label("Quantity: ");
        TextField quantity = new TextField();
        Button btnAdd = new Button("Add to cart");
        Button btnBuy = new Button("Buy now");
        Button btnGoBack = new Button("Go back home");
        btnGoBack.setOnAction((ActionEvent e)->{
            window.setScene(userPage);
            vBox.getChildren().removeAll(vBox.getChildren());
        });
        GridPane product = new GridPane();
        product.setMinSize(200, 200);
//        product.setPadding(new Insets(10, 10, 10, 10));
        product.setVgap(10);
        product.setHgap(10);
        product.setAlignment(Pos.CENTER);
        product.addRow(0,title);
        product.addRow(1,txtName,name);
        product.addRow(2,txtPrice,price);
        product.addRow(3,txtNSX,NSX);
        product.addRow(4,txtDes,description);
        product.addRow(5,txtQuantity,quantity);
        product.addRow(6,btnAdd,btnBuy,btnGoBack);
        HBox productDetail = new HBox();
        productDetail.setAlignment(Pos.CENTER);
        productDetail.getChildren().addAll(imgView,product);
//        int number = Integer.parseInt(txtQuantity.getText());
        vBox.getChildren().addAll(title,productDetail);
        vBox.setAlignment(Pos.CENTER);
        Scene proDetail = new Scene(vBox);
        return proDetail;
    }
}
