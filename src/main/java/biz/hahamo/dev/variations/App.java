package biz.hahamo.dev.variations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import biz.hahamo.dev.variations.controller.ApplicationService;
import biz.hahamo.dev.variations.model.Driver;

/**
 * Simple app for http://github.com/ghajba/variations
 */
public class App {

    public static void main(String[] args) throws ParseException {
        System.out.println("Loading data from the Database.");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationService service = ctx.getBean(ApplicationService.class);
        service.loadData();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Driver driver = new Driver("Gabor Hajba", "12345", "B", dateFormat.parse("2016-05-22"));
        service.saveData(driver);
        System.out.println(driver.getId());

        ctx.close();
        System.out.println("Terminated");
    }
}
