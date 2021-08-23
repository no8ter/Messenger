package ru.sbt.Servlets.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Connect {

    private final static String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\main\\resources\\db\\db.db";
    private static final Logger logger = LoggerFactory.getLogger(Connect.class);
    private static Connection connect;
    private static Statement statement;

    public static synchronized Connection connect() {
        if (connect != null) {
            return connect;
        } else {
            try {
                if (!Files.exists(Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\db"))) {
                    Files.createDirectory(Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\db"));
                }
                connect = DriverManager.getConnection(url);
                statement = connect.createStatement();
                createDB();
                return connect;
            } catch (SQLException | IOException e) {
                logger.error(e.toString());
                return null;
            }
        }
    }

    public static void exec(String sql) {
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            logger.error(e.toString());
        }
    }

    public static ResultSet exeq(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            logger.error(e.toString());
            return null;
        }
    }

    public static void warmup() {
        logger.info("warmup");
        connect();
        exec("SELECT * FROM 'chat' LIMIT 100");
    }

    private static void createDB() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS 'users'(" +
                "'username' text PRIMARY KEY," +
                "'password' text);");
        statement.execute("CREATE TABLE IF NOT EXISTS 'chat'(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'user_id' INTEGER, " +
                "'message' text);");

    }
}
