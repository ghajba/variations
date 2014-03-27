package biz.hahamo.dev.variations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import liquibase.Liquibase;
import liquibase.database.core.H2Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import biz.hahamo.dev.variations.controller.ApplicationService;

/**
 * Simple app for http://github.com/ghajba/variations
 */
public class App
{
    // FIXME: add Liquibase with Spring instead of programmatic Java code
    private static final String url = "jdbc:h2:file:../db/testdb;MVCC=TRUE";
    private static final String username = "sa";
    private static final String password = "";
    private static final String changelog = "dbchange/master.xml";
    
    public static void main(String[] args)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, username, password);
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            H2Database databaseConnection = new H2Database();
            databaseConnection.setConnection(jdbcConnection);
            Liquibase liquibase = new Liquibase(changelog, new ClassLoaderResourceAccessor(), databaseConnection);
            liquibase.update(null);
        }
        catch (LiquibaseException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        System.out.println("Loading data from the Database.");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationService service = ctx.getBean(ApplicationService.class);
        service.loadData();
        ctx.close();
        System.out.println("Terminated");
    }
}
