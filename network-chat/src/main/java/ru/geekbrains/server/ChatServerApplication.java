package ru.geekbrains.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.geekbrains.server.auth.AuthService;
import ru.geekbrains.server.auth.AuthServiceJdbcImpl;
import ru.geekbrains.server.persistance.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChatServerApplication {

    public static void main(String[] args) {
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/network_chat",
//                "root", "root");
//        UserRepository userRepository = new UserRepository(conn);
//        if (userRepository.getAllUsers().size() == 0) {
//            userRepository.insert(new User(-1, "ivan", "123"));
//            userRepository.insert(new User(-1, "petr", "345"));
//            userRepository.insert(new User(-1, "julia", "789"));
//        }
//        AuthService authService = new AuthServiceJdbcImpl(userRepository);
//
//        ChatServer chatServer = new ChatServer(authService);

        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);
        ChatServer chatServer = context.getBean("chatServer", ChatServer.class);
        chatServer.start(7777);
    }
}
