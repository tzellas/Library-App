package com.example.app;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import static com.example.app.PrimaryController.curuser;
import static com.example.app.UserController.selectedBook;

public class BookController {

    @FXML
    public Label label1;

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
    private Label label9;
    @FXML
    private Label borrowedornot;
    @FXML
    private Button claimcheck;
    @FXML
    private ListView<Comment> commentsList;



    public void initialize() {
        ObservableList<Comment> obscomment = FXCollections.observableList(selectedBook.commentList);
        commentsList.setItems(obscomment);
        commentsList.setCellFactory(CommentListView -> new CommentListCell());
        if (borrowed()) {
            borrowedornot.setText("Borrowed");
        } else {
            borrowedornot.setText("Not Borrowed");
        }
        setBookDetails();
    }
    public  boolean borrowed() {
        for (Borrowed book : curuser.borrowedList) {
            if (book.getBook().getIsbn().equals(selectedBook.getIsbn())) {
                return true;
            }
        }return false;
    }
    // Method to set the book details
    private void setBookDetails() {
        if (selectedBook != null) {
            label1.setText(selectedBook.getTitle());
            label2.setText(selectedBook.getAuthor());
            label3.setText(selectedBook.getIsbn());
            label4.setText(String.valueOf(selectedBook.rating));
            label5.setText(String.valueOf(selectedBook.getCopies()));
            label6.setText(String.valueOf(selectedBook.ratingList.size()));
            label7.setText(selectedBook.getYear());
            label8.setText(selectedBook.getIsbn());
            curuser.checkBorrowingStatus();
        }



        }
    @FXML
    void claimButton(ActionEvent event) {

        if (curuser.borrowedList.size() + curuser.claimedBooks.size() < 2 &&
                selectedBook.copies > 0 &&
                !borrowed() &&
                !curuser.claimedBooks.contains(selectedBook)
        )
        {
            curuser.claimedBooks.add(selectedBook);
            selectedBook.copies -= 1;
            label9.setText("Claimed");
        } else {
            label9.setText("Cant Claim ");
        }
    }



}
