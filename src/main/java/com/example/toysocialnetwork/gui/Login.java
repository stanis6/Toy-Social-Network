package com.example.toysocialnetwork.gui;

import com.example.toysocialnetwork.domain.User;
import com.example.toysocialnetwork.service.FriendshipService;
import com.example.toysocialnetwork.service.MessageService;
import com.example.toysocialnetwork.service.UserService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    private FriendshipService friendshipService;
    private UserService userService;
    private MessageService messageService;

    public void setService(UserService userService1, FriendshipService friendshipService1, MessageService messageService1) {
        this.userService = userService1;
        this.friendshipService = friendshipService1;
        this.messageService = messageService1;
    }

    public void setInitMsg(String initMsg) {
        addedText.setText(initMsg);
    }

    @FXML
    private TextField emailText;
    @FXML
    private TextField passwordText;
    @FXML
    private Label addedText;

    @FXML
    public void onLoginButtonClick() throws IOException {
        String email = emailText.getText();
        String password = passwordText.getText();
        User user = userService.findByEmailPassword(email, password);
        if (user == null) {
            addedText.setText("Invalid credentials! Please try again!");
            passwordText.setText("");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/toysocialnetwork/main-window.fxml"));
            AnchorPane root = fxmlLoader.load();
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setAll(userService, friendshipService, messageService, user);

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 800, 600));

            stage.show();

            closeWindow();
        }
    }

    public void registerAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/toysocialnetwork/register-window.fxml"));
        AnchorPane root = fxmlLoader.load();
        RegisterWindow registerWindow = fxmlLoader.getController();
        registerWindow.setUserService(userService);
        registerWindow.setFriendshipService(friendshipService);
        registerWindow.setMessageService(messageService);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.show();

        closeWindow();
    }

    private void closeWindow() {
        Stage thisStage = (Stage) addedText.getScene().getWindow();
        thisStage.close();
    }
}
