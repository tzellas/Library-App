package com.example.app;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.app.UserController.selectedBook;


public class BookListCellPublic extends ListCell<Book> {

    @FXML
    private Label label1;

    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;


    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;

    @Override
    public void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);

        if (empty || book == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("BookListCellPublic.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //κάθε βιβλίο να εμφανίζεται ο τίτλος, ο συγγραφέας, το ΙSBN, ο μέσος όρος της
            //βαθμολογίας και το πλήθος των χρηστών που το έχουν βαθμολογήσει.
            label1.setText(book.getTitle());
            label2.setText(book.getAuthor());
            label3.setText(book.getPublisher());
            label4.setText(String.valueOf(book.rating));
            label5.setText(String.valueOf(book.ratingList.size()));


            setText(null);
            setGraphic(gridPane);
        }

    }
}