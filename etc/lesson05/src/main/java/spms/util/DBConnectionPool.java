package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 8. 1..
 */
public class DBConnectionPool {
    String url;
    String username;
    String password;
    ArrayList<Connection> connList = new ArrayList<>();

    public DBConnectionPool(String driver, String url, String username, String password) throws Exception {
        this.url = url;
        this.username = username;
        this.password = password;

        Class.forName(driver);
    }

    public Connection getConnection() throws Exception {
        if (connList.size() > 0) {
            Connection connection = connList.get(0);
            if (connection.isValid(10)) {
                return connection;
            }
        }
        return DriverManager.getConnection(url, username, password);
    }

    public void returnConnection(Connection connection) throws Exception {
        connList.add(connection);
    }

    public void closeAll() {
        for (Connection connection : connList) {
            try {connection.close();} catch (Exception e) {}
        }
    }
}
