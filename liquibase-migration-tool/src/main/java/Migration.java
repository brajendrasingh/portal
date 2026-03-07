
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.database.jvm.JdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Migration {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/liquibase-db", "root", "root");

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new Liquibase("db/changelog/db.changelog-master.xml", new ClassLoaderResourceAccessor(), database);
            // Run migrations
            liquibase.update();
            // Create tag
            liquibase.tag("v1.0");
            //Rollback by Tag
            //liquibase.rollback("v1.0", new Contexts());
            // rollback to date
            //liquibase.rollback("2026-03-07", new Contexts());
            // rollback by count
            //liquibase.rollback(1, new Contexts(), new LabelExpression());

            System.out.println("Migration completed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}