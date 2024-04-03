package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.app.PrimaryController.curuser;
import static com.example.app.User.userList;


public class SignUpSceneController {

    @FXML
    private TextField fldnewusername;

    @FXML
    private PasswordField  fldnewpassword;
    @FXML
    private TextField fldonoma;
    @FXML
    private TextField fldeponimo;
    @FXML
    private TextField fldadt;
    @FXML
    private TextField fldemail;

    @FXML
    private Button btnewuser;

    public void newuser(ActionEvent event) throws IOException, ClassNotFoundException {
        String username = fldnewusername.getText();
        String password = fldnewpassword.getText();
        String onoma = fldonoma.getText();
        String eponimo = fldeponimo.getText();
        String adt = fldadt.getText();
        String email = fldemail.getText();

        User newUser = new User(username,password,onoma,eponimo,adt,email);
        User.adder(newUser);
        curuser = newUser;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnewuser.getScene().getWindow();
        Scene usernscene = new Scene(root);
        stage.setScene(usernscene);
        stage.show();




    }

}
