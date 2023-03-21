package com.example.toysocialnetwork;

import com.example.toysocialnetwork.domain.Friendship;
import com.example.toysocialnetwork.domain.Message;
import com.example.toysocialnetwork.domain.User;
import com.example.toysocialnetwork.domain.validators.FriendshipValidator;
import com.example.toysocialnetwork.domain.validators.MessageValidator;
import com.example.toysocialnetwork.domain.validators.UserValidator;
import com.example.toysocialnetwork.domain.validators.Validator;
import com.example.toysocialnetwork.gui.Login;
import com.example.toysocialnetwork.repository.db.DBFriendshipRepository;
import com.example.toysocialnetwork.repository.db.DBMessageRepository;
import com.example.toysocialnetwork.repository.db.DBUserRepository;
import com.example.toysocialnetwork.service.FriendshipService;
import com.example.toysocialnetwork.service.MessageService;
import com.example.toysocialnetwork.service.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Validator<User> userValidator = new UserValidator();
        Validator<Friendship> friendshipValidator = new FriendshipValidator();
        Validator<Message> messageValidator = new MessageValidator();
        DBUserRepository userRepository = new DBUserRepository(userValidator,
                "jdbc:postgresql://localhost:5432/map","postgres","postgres");
        DBFriendshipRepository friendshipRepository = new DBFriendshipRepository(friendshipValidator,
                "jdbc:postgresql://localhost:5432/map","postgres","postgres");
        DBMessageRepository messageRepository = new DBMessageRepository(messageValidator,
                "jdbc:postgresql://localhost:5432/map","postgres","postgres");
        UserService userService = new UserService(userRepository);
        FriendshipService friendshipService = new FriendshipService(friendshipRepository);
        MessageService messageService = new MessageService(messageRepository);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Toy Social Network");
        Login login = fxmlLoader.getController();
        login.setService(userService, friendshipService, messageService);
        System.out.println("Service set");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}