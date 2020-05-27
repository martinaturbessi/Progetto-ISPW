package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private String DRIVER_CLASS_NAME;
    private String DB_URL;
    private String USER;
    private String PASS;

    public DataSource() throws IOException {
        Properties props = new Properties();
        /*
        Apro il file properties creato per una gestione più efficiente e aggiornabile del driver/url/username/password
         */
        FileInputStream in = new FileInputStream("/Users/martinaturbessi/Desktop/University/EsameISPW/ProgettoFilters/src/logging.properties");
        props.load(in);
        in.close();
        DRIVER_CLASS_NAME = props.getProperty("driver");
        DB_URL = props.getProperty("url");
        USER = props.getProperty("username");
        PASS = props.getProperty("password");
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_CLASS_NAME);
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        return connection;
    }

}
