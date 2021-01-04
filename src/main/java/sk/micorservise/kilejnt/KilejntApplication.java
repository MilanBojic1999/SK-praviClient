package sk.micorservise.kilejnt;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import sk.micorservise.kilejnt.client.ViewManager;

@SpringBootApplication
public class KilejntApplication extends Application {

    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(KilejntApplication.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("User init");
        ViewManager manager = context.getBean(ViewManager.class);
        stage.setScene(manager.createLogInScene());
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
        Platform.exit();
    }
}
