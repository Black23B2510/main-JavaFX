package com.example.demo5.View;

import com.example.demo5.Controller.Login;
import com.example.demo5.Controller.Products;
import com.example.demo5.Controller.Register;
import com.example.demo5.Models.data.DBConnection;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    Stage window;
    Scene start, login, adminPage, userPage, registerPage;
    @Override
    public void start(Stage primaryStage) {
        DBConnection db = new DBConnection();
        Products pro  = new Products();
        Register reg = new Register();
        //Scene1
        Label labelStart =new Label("Welcome to DEN DEN pharmacy");
        Button btnLogin = new Button("Login");
        Button btnRegisters = new Button("Register");
        HBox btnHBox = new HBox();
        btnHBox.setSpacing(10);
        btnHBox.getChildren().addAll(btnLogin,btnRegisters);
        VBox layout1 = new VBox();
        layout1.setAlignment(Pos.CENTER);
        btnHBox.setAlignment(Pos.CENTER);
        btnLogin.setOnAction(actionEvent -> {
            window.setScene(login);
        });
        btnRegisters.setOnAction(actionEvent -> {
            window.setScene(registerPage);
        });
        layout1.getChildren().addAll(labelStart,btnHBox);
        layout1.setSpacing(10);
        start = new Scene(layout1, 300, 300);

        //RegisterPage
        Label register = new Label("REGISTER");
        register.setTextAlignment(TextAlignment.CENTER);
        Label userName = new Label("Username");
        Label address = new Label("Address");
        Label email = new Label("Email");
        Label password = new Label("Password");
        TextField txtUserName = new TextField();
        TextField txtAddress = new TextField();
        TextField txtEmail = new TextField();
        TextField txtPassword = new TextField();
        Button btnRegister = new Button("Register");
        btnRegister.setOnAction(actionEvent ->{
            reg.registerSubmit(db,txtUserName,txtEmail,txtAddress,txtPassword, login, window);
        });
        GridPane registerForm = new GridPane();
        registerForm.setAlignment(Pos.CENTER);
        registerForm.setVgap(10);
        registerForm.setHgap(10);
        registerForm.addRow(0,register);
        registerForm.addRow(1,userName,txtUserName);
        registerForm.addRow(2,email,txtEmail);
        registerForm.addRow(3,address,txtAddress);
        registerForm.addRow(4,password,txtPassword);
        registerForm.addRow(5,btnRegister);
        registerPage = new Scene(registerForm,500,500);
        //LoginPage
        Login lg = new Login();
        window = primaryStage;
        VBox vBox = new VBox();
        adminPage = new Scene(vBox);
        VBox vboxProducts = new VBox();
        VBox productDetail = new VBox();
        userPage = new Scene(vboxProducts);
        pro.getProductsforCustomers(db,vboxProducts, productDetail, window,userPage);
        VBox loginPage = lg.login(window,start,adminPage,db,userPage);
        pro.getProductsDisplay(db,vBox,window,start, adminPage);
        login = new Scene(loginPage);
        window.setScene(userPage);
        window.show();
    }
}
