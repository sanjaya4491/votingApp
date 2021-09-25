package Model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final HikariDataSource ds;
    private static final HikariConfig config = new HikariConfig(DataSource.class.getClassLoader().getResource("datasource.properties").getPath());

    static {
        ds = new HikariDataSource(config);
    }

    public DataSource() throws SQLException {
//        Connection c = ds.getConnection();
//        c.close();
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
