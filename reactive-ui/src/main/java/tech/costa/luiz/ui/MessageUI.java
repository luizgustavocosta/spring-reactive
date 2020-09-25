package tech.costa.luiz.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class MessageUI extends Application {

    private ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public void start(Stage stage) {
         configurableApplicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() {
        configurableApplicationContext = new SpringApplicationBuilder(ReactiveUIApplication.class).run();
    }

    @Override
    public void stop() {
        configurableApplicationContext.close();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {

        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
    }
}
