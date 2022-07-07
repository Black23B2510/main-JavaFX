package com.example.demo5.Controller;

import com.example.demo5.Models.customer;
import com.example.demo5.Models.data.DBConnection;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Register {
    public void registerSuccessfully(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setHeaderText("REGISTRATION");
        alert.setContentText("Register successfully!");
        alert.show();
    }
    public void registerError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setContentText("Please fill out all of field");
        alert.show();
    }
    public void registerSubmit(DBConnection db, TextField txtUserName, TextField txtEmail, TextField txtAddress, TextField txtPassword, Scene LoginPage, Stage window){
        if(txtUserName.getText() == ""||txtEmail.getText() == ""|| txtAddress.getText() == "" || txtPassword.getText() == ""){
            registerError();
        }
        else{
            db.insertCustomer(new customer(
                    txtUserName.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    txtPassword.getText()
            ));
            registerSuccessfully();
            txtUserName.setText("");
            txtEmail.setText("");
            txtAddress.setText("");
            txtPassword.setText("");
            window.setScene(LoginPage);
        }
    }
}
