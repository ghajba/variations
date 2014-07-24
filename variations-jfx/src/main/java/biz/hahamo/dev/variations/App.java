package biz.hahamo.dev.variations;

import java.text.ParseException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import biz.hahamo.dev.variations.view.ViewService;

/**
 * Simple application for http://github.com/ghajba/variations
 */
public class App extends Application {

    private ClassPathXmlApplicationContext ctx;

    public static void main(String... args) throws ParseException {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) {

        System.out.println("Starting application...");
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        final ViewService viewService = ctx.getBean(ViewService.class);

        mainStage = new Stage(StageStyle.DECORATED);
        mainStage.setTitle("Shipping Note Management Application");
        mainStage.setResizable(false);
        mainStage.setScene(viewService.getMainScene());
        mainStage.show();
    }

    @Override
    public void stop() {
        if(ctx != null) 
            ctx.close();
        System.out.println("Terminated");
    }
}
