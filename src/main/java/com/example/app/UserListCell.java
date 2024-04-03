package com.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class UserListCell extends ListCell<User> {
    @FXML
    private Label labelusername;
    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;
    @Override
    public void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if (empty || user == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("UserListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            labelusername.setText(user.getUsername());

            setText(null);
            setGraphic(gridPane);
        }

    }


}
