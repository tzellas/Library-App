package com.example.app;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;
import static com.example.app.Book.bookList;
import static com.example.app.User.userList;


public class PrimaryController implements Initializable {
    @FXML
    private Button btlogin;
    @FXML
    private Button btsignup;

    @FXML
    private TextField fldusername;
    @FXML
    private PasswordField fldpassword;
    @FXML
    private Label nouserfound;
    @FXML
    private ListView<Book> listView;

    @FXML
    private void signup(ActionEvent event) throws IOException, ClassNotFoundException {
        // Load the FXML file for the new scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signupscene.fxml"));
        Parent root = loader.load();

        // Get the Stage from the current Scene
        Stage stage = (Stage) btsignup.getScene().getWindow();

        // Create a new scene with the loaded FXML file
        Scene signupscene = new Scene(root);

        // Set the new scene on the stage
        stage.setScene(signupscene);
        // Set the title of the new scene if needed
        stage.show(); // Show the new scene


    }
    public static User curuser;
    @FXML
    private void login(ActionEvent event) throws IOException {
        String username = fldusername.getText();
        String password = fldpassword.getText();
        if (username.equals("medialab") && password.equals("medialab_2024")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btlogin.getScene().getWindow();
            Scene adminscene = new Scene(root);
            stage.setScene(adminscene);
            stage.show();
        } else {

            curuser = User.finduser(username, password, userList);

            if (curuser == null) {
                nouserfound.setText("Invalid Username or Password");
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btlogin.getScene().getWindow();
                Scene userscene = new Scene(root);
                stage.setScene(userscene);
                stage.show();
            }


        }
    }
    public static List<Book> bestBooks = Book.bestBooks();

    ObservableList<Book> observablebooks = FXCollections.observableList(bestBooks);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(observablebooks);
        listView.setCellFactory(BookListView -> new BookListCellPublic());

    }
}