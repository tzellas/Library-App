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
import java.time.format.DateTimeFormatter;

import static com.example.app.UserController.commentrateBook;
import static com.example.app.UserController.selectedBook;


public class BookListCellBorrowed extends ListCell<Borrowed> {

    @FXML
    private Label label1;

    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;



    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;

    @Override
    public void updateItem(Borrowed book, boolean empty) {
        super.updateItem(book, empty);

        if (empty || book == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("BookListCellBorrowed.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            label1.setText(book.getBook().getTitle());
            label2.setText(book.getBook().getIsbn());
            label3.setText(book.timestamp.format(dateFormatter));
            label4.setText(book.timestamp.plusDays(5).format(dateFormatter));

            setText(null);
            setGraphic(gridPane);

        }
    }
    @FXML
    public void commentrateBook(MouseEvent  event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentRate.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(commentrateBook.getBook().getTitle());
        stage.show();

    }
}
