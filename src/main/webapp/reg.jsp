<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <style>
        body {
            margin-top: 0;
            margin-left: 10%;
            margin-right: 10%;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }
        input[type=submit]{
            width:100%;
        }
        form {
            margin-bottom: 0;
        }
        div.form{
            display: flex;
            flex-direction: column;
        }
        button{
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="form">
        <form method="post">
            <p><input name="username" type="text" placeholder="Логин"></p>
            <p><input name="password" type="password" placeholder="Пароль"><br></p>
            <p><input type="submit" value="Зарегистрироваться"></p>
        </form>
        <a href="/login"><button>Войти</button></a></div>
    </body>
</html>
