package ru.sbt.Servlets.Utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static java.util.Arrays.asList;


public class UserToolsTests {

    @Test
    public void testChatListGetting() throws SQLException {
        Connect.connect();
        List<List<String>> testlist = UserTools.getChatStory();

        testlist.forEach(System.out::println);

        Assertions.assertEquals(asList(asList("no8ter", "Hello, World!")), testlist);
    }

}
