package biz.hahamo.dev.variations;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import biz.hahamo.dev.variations.controller.ApplicationService;

/**
 * Simple app for http://github.com/ghajba/variations
 */
public class App
{
    
    public static void main(String[] args)
    {
        System.out.println("Loading data from the Database.");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationService service = ctx.getBean(ApplicationService.class);
        service.loadData();
        ctx.close();
        System.out.println("Terminated");
    }
}
