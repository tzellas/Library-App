package com.example.app;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.app.Book.*;
import static com.example.app.User.userList;
import static com.example.app.UserController.selectedBook;

public class AdminController implements Initializable {
    @FXML
    private Button btnchoose;
    @FXML
    private Button btnsearch;
    @FXML
    private Button btnshowuser;

    @FXML
    private ListView<Book> categorylist;

    @FXML
    private TextField fldcategory;
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
    private Label label6;

    @FXML
    private Label label7;
    @FXML
    private Label label8;

    @FXML
    private TextField fldtitle;
    @FXML
    private TextField fld1;

    @FXML
    private TextField fld2;

    @FXML
    private TextField fld3;

    @FXML
    private TextField fld4;

    @FXML
    private TextField fld5;

    @FXML
    private TextField fld6;

    @FXML
    private TextField fld7;
    @FXML
    private Label removedlabel;
    @FXML
    private ListView<User> userslist;

    public static  User selecteduser ;
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<User> obsuser = FXCollections.observableList(userList);
        userslist.setItems(obsuser);
        userslist.setCellFactory(UserListView -> new UserListCell());
        userslist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selecteduser= newValue; });
        ObservableList<Book> observablebooks = FXCollections.observableList(bookList);
        categorylist.setItems(observablebooks);
        categorylist.setCellFactory(BookListView -> new BookListCell());

    }

    @FXML
    private void searchbooksbycategory(ActionEvent event){
        if(fldcategory.getText().isEmpty()) {
            ObservableList<Book> observablebooks = FXCollections.observableList(bookList);
            categorylist.setItems(observablebooks);
            categorylist.setCellFactory(BookListView -> new BookListCell());
        }else {
            List<Book> books = getBooksByCategory(bookList, fldcategory.getText());
            ObservableList<Book> observablebooks = FXCollections.observableList(books);

            categorylist.setItems(observablebooks);
            categorylist.setCellFactory(BookListView -> new BookListCell());
        }
    }
    public Book book;
    @FXML
    private void showbook(ActionEvent event){
        removedlabel.setText("");
        String title = fldtitle.getText();
        book = searchBookByTitle(bookList,title);
        if(book !=null) {
            label1.setText(book.getTitle());
            label2.setText(book.getAuthor());
            label3.setText(book.getPublisher());
            label4.setText(book.getIsbn());
            label5.setText(book.getYear());
            label6.setText(book.category);
            label7.setText(String.valueOf(book.copies));
            label8.setText("");
        }else{
                label8.setText("There is no such book");
            }

    }

    @FXML
    private void modifybook(ActionEvent event){
        if(!fld1.getText().isEmpty()){
            book.setTitle(fld1.getText());
            label1.setText(book.getTitle());
        }
        if(!fld2.getText().isEmpty()){
            book.setAuthor(fld2.getText());
            label2.setText(book.getAuthor());
        }
        if(!fld3.getText().isEmpty()){
            book.setPublisher(fld3.getText());
            label3.setText(book.getPublisher());
        }
        if(!fld4.getText().isEmpty()){
            book.setIsbn(fld4.getText());
            label4.setText(book.getIsbn());
        }
        if(!fld5.getText().isEmpty()){
            book.setYear(fld5.getText());
            label5.setText(book.getYear());
        }
        if(!fld6.getText().isEmpty()){
            book.setCategory(fld6.getText());
            label6.setText(book.getCategory());
        }
        if(!fld7.getText().isEmpty()){
            book.setCopies(Integer.parseInt(fld7.getText()));
            label7.setText(String.valueOf(book.copies));
        }

    }
    @FXML
    private void deletebook(ActionEvent event){
        bookList.remove(book);
        Borrowed x = null;
        for(User user : userList){
            for(Borrowed bbook: user.borrowedList) {
                if (bbook.getBook().getIsbn().equals(book.getIsbn())) {
                    x = bbook;
                }
                if (x != null) {
                    user.borrowedList.remove(x);
                }
            }
        }

        removedlabel.setText("Book removed succesfully");
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");

    }
    @FXML
    private void addbook(){
        Book newbook = new Book(fld1.getText(),fld2.getText(),fld3.getText(),fld4.getText(),fld5.getText(),fld6.getText(),Integer.parseInt(fld7.getText()));
        bookAdder(newbook);
        removedlabel.setText("Book added succesfully");
    }
    @FXML
    private Label label11;

    @FXML
    private Label label12;
    @FXML
    private Label label13;

    @FXML
    private Label label14;

    @FXML
    private Label label15;
    @FXML
    private TextField fld11;

    @FXML
    private TextField fld12;

    @FXML
    private TextField fld13;

    @FXML
    private TextField fld14;

    @FXML
    private TextField fld15;
    @FXML
    private void selectedUser(ActionEvent event) throws IOException{

        label11.setText(selecteduser.getUsername());
        label12.setText(selecteduser.getOnoma());
        label13.setText(selecteduser.getEponimo());
        label14.setText(selecteduser.getAdt());
        label15.setText(selecteduser.getEmail());

    }
    @FXML
    private void savenewinfo(ActionEvent event) throws IOException {
        if(!fld11.getText().isEmpty()){
            selecteduser.setUsername(fld11.getText());
            label11.setText(selecteduser.getUsername());
        }
        if(!fld12.getText().isEmpty()){
            selecteduser.setOnoma(fld12.getText());
            label12.setText(selecteduser.getOnoma());
        }
        if(!fld13.getText().isEmpty()){
            selecteduser.setEponimo(fld13.getText());
            label13.setText(selecteduser.getEponimo());
        }
        if(!fld14.getText().isEmpty()){
            selecteduser.setAdt(fld14.getText());
            label14.setText(selecteduser.getAdt());
        }
        if(!fld15.getText().isEmpty()){
            selecteduser.setEmail(fld15.getText());
            label15.setText(selecteduser.getEmail());
        }
        label11.setText(selecteduser.getUsername());
        label12.setText(selecteduser.getOnoma());
        label13.setText(selecteduser.getEponimo());
        label14.setText(selecteduser.getAdt());
        label15.setText(selecteduser.getEmail());


    }



}
