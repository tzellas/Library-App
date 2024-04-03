package com.example.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.app.Book.searchBooks;
import static com.example.app.PrimaryController.curuser;
import static com.example.app.User.userList;

public class UserController  {

    @FXML
    private Button btnsearch;

    @FXML
    private TextField fldauthor;

    @FXML
    private TextField fldtitle;

    @FXML
    private TextField fldyear;
    public static Book selectedBook;
    public static Borrowed commentrateBook;

    @FXML
    public  ListView<Book> searchlistview;
    @FXML
    public ListView<Book> bookoverview1;
    @FXML
    public ListView<Borrowed> bookoverview2;


    @FXML
    private void initialize() {

            searchlistview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedBook = newValue; });

            bookoverview2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                commentrateBook= newValue; });
                curuser.checkBorrowingStatus();

            ObservableList<Book> obsbooks1 = FXCollections.observableList(curuser.claimedBooks);
            bookoverview1.setItems(obsbooks1);
            bookoverview1.setCellFactory(BookListView -> new BookListCellPublic());

            ObservableList<Borrowed> obsbooks2 = FXCollections.observableList(curuser.borrowedList);
            bookoverview2.setItems(obsbooks2);
            bookoverview2.setCellFactory(BookListView -> new BookListCellBorrowed());


    }


    @FXML
    private void searchButton(ActionEvent event) {
        List<Book> books = searchBooks(fldtitle.getText(), fldauthor.getText(), fldyear.getText());

        ObservableList<Book> observablebooks = FXCollections.observableList(books);
        searchlistview.setItems(observablebooks);
        searchlistview.setCellFactory(BookListView -> new BookListCell());


    }



}
