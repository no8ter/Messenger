<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="top.jsp" />
<body>
    <div class="main">
        <div class="form">
            <form method="post">
                <p><label>
                    <input name="username" type="text" placeholder="Логин">
                </label></p>
                <p><label>
                    <input name="password" type="password" placeholder="Пароль">
                </label><br></p>
                <p><input type="submit" value="Войти"></p>
            </form>
            <a href="/register"><button>Зарегистрироваться</button></a>
        </div>
    </div>
</body>
</html>
