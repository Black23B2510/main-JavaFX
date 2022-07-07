package com.example.demo5.Controller;

import com.example.demo5.Models.admin;
import com.example.demo5.Models.customer;
import com.example.demo5.Models.data.DBConnection;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Login {
    private void LoginSuccess(TextField name) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("Hi "+name.getText());
        alert.setContentText("Login successfully!");
        alert.show();
    }
    private void LoginError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setContentText("Login fail!");
        alert.show();
    }
    public void customerLogin(DBConnection db, Stage window, TextField name, TextField pass, Scene scene3){
        ArrayList<customer> customers = new ArrayList<>();
        customers = db.getCustomers();
        String inputName = name.getText();
        String inputPass = pass.getText();
        if(inputName.equals(customers.get(0).cusName)&& inputPass.equals(customers.get(0).password)){
            LoginSuccess(name);
            window.setScene(scene3);
            name.setText("");
            pass.setText("");
        }else{
            LoginError();
        }
    }
    public void checkLogin(DBConnection db, Stage window, TextField name, TextField pass, Scene scene3){
        ArrayList<admin> ad = new ArrayList<>();
        ad = db.getAdmin();
        String inputName = name.getText();
        String inputPass = pass.getText();
        if(inputName.equals(ad.get(0).name)&& inputPass.equals(ad.get(0).password)){
            LoginSuccess(name);
            window.setScene(scene3);
            name.setText("");
            pass.setText("");
        }else{
            LoginError();
        }
    }
    public VBox login(Stage window, Scene scene1, Scene scene3, DBConnection db, Scene scene4){
        VBox loginPage = new VBox();
        Label labelLogin =new Label("LOGIN");
        Label Aname = new Label("Name: ");
        Label Apassword = new Label("Password: ");
        TextField name = new TextField();
        TextField pass= new TextField();
        HBox fieldName = new HBox();
        fieldName.getChildren().addAll(Aname,name);
        fieldName.setSpacing(10);
        fieldName.setAlignment(Pos.BASELINE_CENTER);
        HBox fieldPass = new HBox();
        fieldPass.getChildren().addAll(Apassword,pass);
        fieldPass.setSpacing(10);
        fieldPass.setAlignment(Pos.BASELINE_CENTER);
        Button btnGoBack = new Button("GO HOME");
        btnGoBack.setOnAction(actionEvent -> {
            window.setScene(scene1);
        });
        Button btnLogin = new Button("LOGIN as ADMIN");
        btnLogin.setOnAction(actionEvent -> {
            checkLogin(db,window, name, pass, scene3);
        });
        Button btnLogin1 = new Button("LOGIN as CUSTOMER");
        btnLogin1.setOnAction((ActionEvent e)->{
            customerLogin(db, window, name, pass,scene4);
        });
        HBox btnLoginPage = new HBox();
        btnLoginPage.getChildren().addAll(btnLogin,btnLogin1,btnGoBack);
        btnLoginPage.setSpacing(10);
        btnLoginPage.setAlignment(Pos.BASELINE_CENTER);
        loginPage.getChildren().addAll(labelLogin,fieldName,fieldPass,btnLoginPage);
        loginPage.setSpacing(15);
        loginPage.setAlignment(Pos.BASELINE_CENTER);
        return loginPage;
    }
}
