package comp3350.recimeal.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBPersistence {

    protected static String dbPath;
    protected static String dbUsername;
    protected static String dbPassword;

    public DBPersistence(final String newDbPath) {
        this.dbPath = newDbPath;
        this.dbUsername = "SA";
        this.dbPassword = "";

    }

    protected Connection connectDB() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + this.dbPath + ";shutdown=true", this.dbUsername , this.dbPassword);
    }
}
