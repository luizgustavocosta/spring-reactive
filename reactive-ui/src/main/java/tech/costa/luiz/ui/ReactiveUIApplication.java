package tech.costa.luiz.ui;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveUIApplication {

    public static void main(String[] args) {
        Application.launch(MessageUI.class, args);
    }
}
