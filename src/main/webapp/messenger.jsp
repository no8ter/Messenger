<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.sbt.Servlets.Utils.UserTools" %>
<%@ page import="java.util.List" %>
<jsp:include page="top.jsp" />
<body>

    <div class="main">
        <%
            for(List<String> l : UserTools.getChatStory()){
                String message = "<div class=\"message\">"+
                                    "<p>"+l.get(0)+": "+l.get(1)+"</p>"+
                                 "</div>";
                out.println(message);
            }
        %>
    </div>
</body>
</html>
