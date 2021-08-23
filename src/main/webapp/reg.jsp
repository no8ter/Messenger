<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="top.jsp" />
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
