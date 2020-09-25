package tech.costa.luiz.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<MessageUI.StageReadyEvent> {

    @Value("classpath:/chart.fxml")
    private Resource charResource;
    private final String applicationTitle;
    private final ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui-title}") String title, ApplicationContext applicationContext) {
        this.applicationTitle = title;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(MessageUI.StageReadyEvent event) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(charResource.getURL());
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            Parent parent = fxmlLoader.load();
            final Stage stage = event.getStage();
            stage.setScene(new Scene(parent, 800, 600));
            stage.setTitle(applicationTitle);
            stage.show();
        } catch (IOException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
