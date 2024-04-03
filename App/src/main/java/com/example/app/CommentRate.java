package com.example.app;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.app.PrimaryController.curuser;
import static com.example.app.UserController.commentrateBook;

public class CommentRate {
    @FXML
    private TextField comment;

    @FXML
    private Button btrating;
    @FXML
    private Button btcomment;
    @FXML
    private Label label1;

    @FXML
    private TextField rate;

    public void comments(ActionEvent event) throws IOException {
        String newcomment = comment.getText();
        Comment newcommentc = new Comment(newcomment, curuser);
        commentrateBook.getBook().commentList.add(newcommentc);

    }
    public boolean israted;
    public void rating(ActionEvent event) {
        for (Rating rating : commentrateBook.getBook().ratingList) {
            if (rating.getUser() == curuser) {
                label1.setText("You have already rated this book");
                israted = true;
            } else {
                israted = false;
            }
        }
            if (!israted) {
                String newrate = rate.getText();
                int intrate = Integer.parseInt(newrate);
                Rating newrating = new Rating(curuser, intrate);
                commentrateBook.getBook().ratingList.add(newrating);


            }
        }


    }
