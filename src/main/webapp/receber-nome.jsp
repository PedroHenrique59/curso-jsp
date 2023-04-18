<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String nome = request.getParameter("nome");
    out.print(nome);
%>

</body>
</html>
