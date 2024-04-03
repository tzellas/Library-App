package com.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class CommentListCell extends ListCell<Comment> {
    @FXML
    private Label label1;

    @FXML
    private Label label2;



    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;

    @Override
    public void updateItem(Comment comment, boolean empty) {
        super.updateItem(comment, empty);

        if (empty || comment == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("CommentListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }




            label1.setText(comment.getUsernamecom());
            label2.setText(comment.getComment());


            setText(null);
            setGraphic(gridPane);

        }
    }
}
