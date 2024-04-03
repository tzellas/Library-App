package com.example.app;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;

    private String onoma;
    private String eponimo;
    private String adt;
    private String email;

    public  List<Borrowed> borrowedList ;
    public List<Book> claimedBooks;
    public void checkBorrowingStatus() {
        for (Borrowed book : borrowedList) {
            if (LocalDateTime.now().isAfter(book.timestamp.plusDays(5))) {
                borrowedList.remove(book);
            }
        }
    }
    public void borrowedAdder(Book book){
        Borrowed newb = new Borrowed(book);
        borrowedList.add(newb);
    }

    public static List<User> userList = new ArrayList<>();
    public User(String username, String password , String onoma, String eponimo, String adt, String email) {

        this.username = username;
        this.password = password;
        this.onoma = onoma;
        this.eponimo = eponimo;
        this.adt = adt;
        this.email = email;
        borrowedList = new ArrayList<>();
        claimedBooks = new ArrayList<>();

    }
    public static void adder(User e){
        userList.add(e);
    }

    public String getUsername() {
        return username;
    }
    public String getOnoma() {
        return onoma;
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }
    public String getEponimo() {
        return eponimo;
    }
    public String getAdt() {
        return adt;
    }
    public String getEmail() {
        return email;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setOnoma(String onoma) {
        this.onoma = onoma;
    }

    public void setEponimo(String eponimo) {
        this.eponimo = eponimo;
    }

    public void setAdt(String adt) {
        this.adt = adt;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public static User finduser(String username, String password,List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public static void serializeUsers(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(userList);
            System.out.println("List serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<User> deserializeUsers(String filename) {
        List<User> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            list = (List<User>) ois.readObject();
            System.out.println("List deserialized successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


}
