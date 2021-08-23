<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.sbt.Servlets.Utils.UserTools" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<jsp:include page="top.jsp" />
<body>

        <div class="menu">
            <h3>@<%= request.getSession().getAttribute("username") %></h3>
            <a href="/logout"><button>Выйти</button></a>
        </div>
        <div class="messenger">
            <%
                for(List<String> l : UserTools.getChatStory()){
                    String message = "<div class=\"message\"><p>";
                    if (request.getSession().getAttribute("username").equals(l.get(0))) {
                        message+="<font color=\"lightgreen\">"+l.get(0)+"</font>";
                    } else {
                        message+=l.get(0);
                    }
                    message += ": "+l.get(1)+"</p></div>";
                    out.println(message);
                }
            %>
            <div class="send_message_form">
                <form method="post" id="send_message">
                    <input type="text" name="message" placeholder="Сообщение" id="message_field">
                    <input type="submit" value="Отправить">
                </form>
            </div>
        </div>

    <script type="text/javascript">

    setTimeout(function(){
       if (document.getElementById("message_field").value == "") window.location.reload(1);
    }, 5000);

    </script>

</body>
</html>
