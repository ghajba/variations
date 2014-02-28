package biz.hahamo.dev.variations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import liquibase.Liquibase;
import liquibase.database.core.H2Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

/**
 * Hello world!
 */
public class App
{
    private static final String url = "jdbc:h2:file:../db/testdb";
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

        System.out.println("Hello World!");
    }
}
