package com.example.demo5.Controller;

import com.example.demo5.Models.data.DBConnection;
import com.example.demo5.Models.product;
import com.example.demo5.Controller.ProductDetail;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Products {
    public void getProductsDisplay(DBConnection db, VBox vBox, Stage window, Scene scene1, Scene scene3){
        Label id = new Label("Id_product");
        Label nameProduct = new Label("Name");
        Label NSX = new Label("NSX");
        Label image = new Label("Image");
        Label price = new Label("Price");
        Label quantity = new Label("Quantity");
        Label edit = new Label("EDIT");
        Label delete = new Label("DELETE");
        GridPane table = new GridPane();
        table.setMinSize(400, 200);
        table.setPadding(new Insets(10, 10, 10, 10));
        table.setVgap(10);
        table.setHgap(10);
        table.setAlignment(Pos.CENTER);
        Label list = new Label("LIST MEDICINES");
        list.setTextFill(Color.GREEN);
        table.addRow(0,list);
        table.addRow(1,id,nameProduct,NSX,image,price,quantity, edit,delete);
        ArrayList<product> listProducts = db.getProduct();
        for(int i=0; i<listProducts.size();i++){
            Image linkImage = new Image(listProducts.get(i).image);
            ImageView imgView = new ImageView();
            imgView.setImage(linkImage);
            imgView.setFitWidth(100);
            imgView.setFitHeight(100);
            Label idPro = new Label(""+listProducts.get(i).id);
            Label namePro = new Label(listProducts.get(i).name);
            Label NSXpro = new Label(listProducts.get(i).NSX);
            Label pricePro = new Label(""+listProducts.get(i).price);
            Label quantityPro = new Label(""+listProducts.get(i).quantity);
            Button btnEdit = new Button("EDIT");
            btnEdit.setId(String.valueOf(listProducts.get(i).id));
            int finalI = i;
            btnEdit.setOnAction((ActionEvent e)->{
                updateProduct(db, vBox,listProducts.get(finalI).id,listProducts.get(finalI).name,listProducts.get(finalI).NSX,listProducts.get(finalI).image,listProducts.get(finalI).quantity,listProducts.get(finalI).price, listProducts.get(finalI).description, window, scene1, scene3);
            });
            Button btnDelete = new Button("DELETE");
            btnDelete.setId(String.valueOf(listProducts.get(i).id));
            btnDelete.setOnAction((ActionEvent e)->{
                deleteProduct(db,Integer.parseInt(btnDelete.getId()),vBox,window,scene1, scene3);
            });
            table.addRow(i+2,idPro,namePro,NSXpro,imgView,pricePro, quantityPro,btnEdit,btnDelete);
        }
        Button btnExit = new Button("EXIT");
        Button btnAdd = new Button("ADD PRODUCT");
        HBox btnHbox = new HBox();
        btnHbox.setSpacing(20);
        btnHbox.getChildren().addAll(btnAdd,btnExit);
        btnAdd.setOnAction((ActionEvent e)->{
            vBox.getChildren().removeAll(vBox.getChildren());
            addProduct(vBox,db,window,scene1, scene3);
        });
        btnExit.setOnAction((ActionEvent e)->{
            window.setScene(scene1);
        });
        Label title = new Label("DENDEN PHARMACY");
        title.setTextFill(Color.GREEN);
        title.setAlignment(Pos.BASELINE_CENTER);
        vBox.getChildren().addAll(title,table,btnHbox);
    }
    private void addError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setContentText("Can not add product");
        alert.show();
    }
    public void addProduct(VBox vBox,DBConnection db, Stage window, Scene scene1, Scene scene3){
        GridPane addProducts = new GridPane();
        addProducts.setMinSize(400, 200);
        addProducts.setPadding(new Insets(10, 10, 10, 10));
        addProducts.setVgap(10);
        addProducts.setHgap(10);
        addProducts.setAlignment(Pos.CENTER);
        Label addName = new Label("Name:");
        Label addNSX = new Label("NSX:");
        Label addImage = new Label("Image");
        Label addPrice = new Label("Price:");
        Label addQuantity = new Label("Quantity:");
        Label addDes = new Label("Description");
        TextField txtName = new TextField();
        TextField txtNSX = new TextField();
        TextField txtPrice = new TextField();
        TextField txtQuantity = new TextField();
        TextField txtDes = new TextField();
        TextField txtImage = new TextField();
        Button btnAdd = new Button("ADD");
        btnAdd.setOnAction((ActionEvent e)->{
            if(txtName.getText() =="" || txtNSX.getText()=="" || txtPrice.getText()=="" || txtQuantity.getText()=="" || txtDes.getText()==""){
                addError();
            }
            else{
                db.insertProduct(new product(
                        txtName.getText(),
                        txtNSX.getText(),
                        txtImage.getText(),
                        Float.valueOf(txtPrice.getText()),
                        Integer.valueOf(txtQuantity.getText()),
                        txtDes.getText()

                ));
                vBox.getChildren().removeAll(vBox.getChildren());
                getProductsDisplay(db,vBox,window, scene1, scene3);
            }
        });
        addProducts.addRow(0,addName,txtName);
        addProducts.addRow(1,addNSX,txtNSX);
        addProducts.addRow(2,addImage,txtImage);
        addProducts.addRow(3,addPrice,txtPrice);
        addProducts.addRow(4,addQuantity,txtQuantity);
        addProducts.addRow(5,addDes,txtDes);
        addProducts.addRow(6,btnAdd);
        vBox.getChildren().addAll(addProducts);
    }
    public void deleteProduct(DBConnection db, int id, VBox vBox,Stage window, Scene scene1, Scene scene3){
        db.deleteProduct(id);
        vBox.getChildren().removeAll(vBox.getChildren());
        getProductsDisplay(db,vBox, window, scene1, scene3);

    }
    public void updateProduct(DBConnection db, VBox vBox,int id, String name, String NSX,String img, int quantity, Float price,String des, Stage window, Scene scene1, Scene scene3){
        GridPane updateProducts = new GridPane();
        updateProducts.setMinSize(400, 200);
        updateProducts.setPadding(new Insets(10, 10, 10, 10));
        updateProducts.setVgap(10);
        updateProducts.setHgap(10);
        updateProducts.setAlignment(Pos.CENTER);
        Label addName = new Label("Name:");
        Label addNSX = new Label("NSX:");
        Label addImage = new Label("Link image");
        Label addPrice = new Label("Price:");
        Label addQuantity = new Label("Quantity:");
        Label addDescription = new Label("Description");
        TextField txtName = new TextField();
        txtName.setText(name);
        TextField txtNSX = new TextField();
        txtNSX.setText(NSX);
        TextField txtPrice = new TextField();
        txtPrice.setText(""+price);
        TextField txtQuantity = new TextField();
        txtQuantity.setText(""+quantity);
        TextField txtDes = new TextField();
        txtDes.setText(des);
        TextField txtImage = new TextField();
        txtImage.setText(img);
        Button btnUpdate = new Button("SAVE PRODUCT");
        btnUpdate.setOnAction((ActionEvent e)->{
            String updateName = txtName.getText();
            String updateNSX = txtNSX.getText();
            Float updatePrice = Float.valueOf(txtPrice.getText());
            int updateQuantity = Integer.parseInt(txtQuantity.getText());
            String updateDes = txtDes.getText();
            String image = txtImage.getText();
            db.updateProduct(new product(id,updateName,updateNSX,image,updatePrice,updateQuantity,updateDes));
            vBox.getChildren().removeAll(vBox.getChildren());
            getProductsDisplay(db, vBox, window, scene1, scene3);
        });
        Button goBack = new Button("GO BACK");
        goBack.setOnAction((ActionEvent e)->{
            window.setScene(scene3);
        });
        updateProducts.addRow(0,addName,txtName);
        updateProducts.addRow(1,addNSX,txtNSX);
        updateProducts.addRow(2,addImage,txtImage);
        updateProducts.addRow(3,addPrice,txtPrice);
        updateProducts.addRow(4,addQuantity,txtQuantity);
        updateProducts.addRow(5,addDescription,txtDes);
        updateProducts.addRow(6,btnUpdate, goBack);
        vBox.getChildren().removeAll(vBox.getChildren());
        vBox.getChildren().addAll(updateProducts);
    }
    public void getProductsforCustomers(DBConnection db, VBox vBox,VBox vbDetail, Stage window, Scene scene){
        ProductDetail proDetail = new ProductDetail();
        Label title = new Label("CAC SAN PHAM TAI CUA HANG");
        GridPane listProducts = new GridPane();
        listProducts.addRow(0,title);
        ArrayList<product> products = db.getProduct();
        HBox allProducts = new HBox();
        allProducts.setSpacing(20);
        for(int i = 0; i< products.size(); i++){
            Label price = new Label(""+ products.get(i).price+"VND");
            Label nameProduct = new Label(products.get(i).name);
            price.setStyle("-fx-font-weight: bold");
            Image linkImage = new Image(products.get(i).image);
            ImageView imgView = new ImageView();
            imgView.setImage(linkImage);
            imgView.setFitWidth(100);
            imgView.setFitHeight(100);
            Button btnCart = new Button("Add to cart");
            Button btnView = new Button("View more");
            int finalI = i;
            btnView.setOnAction((ActionEvent e)->{
                Scene sn = proDetail.displayProductById(db,vbDetail,products.get(finalI).id,window, scene);
                window.setScene(sn);
            });
            HBox button = new HBox();
            button.setSpacing(5);
            button.getChildren().addAll(btnCart,btnView);
            VBox product = new VBox();
            product.getChildren().addAll(imgView,nameProduct,price,button);
            allProducts.getChildren().addAll(product);
        }
        vBox.getChildren().addAll(title,allProducts);
    }
}
