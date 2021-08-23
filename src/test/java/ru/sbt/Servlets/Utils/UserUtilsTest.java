package ru.sbt.Servlets.Utils;

import org.junit.jupiter.api.*;

@DisplayName("UserUtils tests")
class UserUtilsTest {

    @Test
    void testUserUtilsValidLogin() {

        String login = "guest";
        String pass = "guest";

        boolean answer = UserUtils.checkLoginAndPass(login, pass);

        Assertions.assertTrue(answer);
    }

    @Test
    void testUserUtilsInvalidLogin() {

        String login = "guest";
        String pass = "12321231123";

        boolean answer = UserUtils.checkLoginAndPass(login, pass);

        Assertions.assertFalse(answer);
    }
}