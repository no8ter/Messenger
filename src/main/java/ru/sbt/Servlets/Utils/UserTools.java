package ru.sbt.Servlets.Utils;

import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.sbt.Servlets.Utils.Connect.exeq;

public class UserTools {
    public static boolean checkLoginAndPass(String login, String pass) {

        if (login == null || pass == null) {
            return false;
        }
        try {

            String sql = "SELECT rowid FROM 'users' WHERE username LIKE '" + login + "' AND password LIKE '" + pass + "';";
            System.out.println(sql);
            ResultSet results = exeq(sql);

            assert results != null;
            return results.next();
        } catch (SQLException e) {
            LoggerFactory.getLogger(UserTools.class).error(e.toString());
            return false;
        }

    }
}
