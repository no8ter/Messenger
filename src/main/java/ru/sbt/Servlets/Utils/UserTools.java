package ru.sbt.Servlets.Utils;

import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static ru.sbt.Servlets.Utils.Connect.exec;
import static ru.sbt.Servlets.Utils.Connect.exeq;

public class UserTools {
    public static boolean checkLoginAndPass(String login, String pass) {

        if (login == null || pass == null) {
            return false;
        }
        try {

            String sql = "SELECT rowid FROM 'users' WHERE username LIKE '" + login + "' AND password LIKE '" + pass + "';";
            ResultSet results = exeq(sql);

            assert results != null;
            return results.next();
        } catch (SQLException e) {
            LoggerFactory.getLogger(UserTools.class).error(e.toString());
            return false;
        }
    }

    public static List<List<String>> getChatStory() {
        List<List<String>> ret = new ArrayList<>();

        try {
            String sql = "SELECT username, message FROM 'chat' join 'users' on 'chat'.'user_id'='users'.'rowid' LIMIT 100;";
            ResultSet results = exeq(sql);
            assert results != null;
            while (results.next()) {
                ret.add(asList(results.getString("username"), results.getString("message")));
            }

        } catch (SQLException e) {
            LoggerFactory.getLogger(UserTools.class).error(e.toString());
        }

        return ret;
    }

    public static void registerNewUser(String username, String password) {
        exec("INSERT INTO 'users'('username', 'password') VALUES ('" + username + "','" + password + "');");
    }

    public static void sendUserMessage(String username, String message) {
        int senderId = 0;
        String sql = "SELECT rowid FROM 'users' WHERE 'users'.'username' = '" + username + "';";
        ResultSet results = exeq(sql);

        try {
            assert results != null;
            if (results.next()) {
                senderId = results.getInt("rowid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        exec("INSERT INTO 'chat'('user_id', 'message') VALUES (" + senderId + ",'" + message + "');");
    }
}
