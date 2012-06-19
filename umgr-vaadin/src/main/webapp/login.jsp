<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.core.AuthenticationException"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<html>
<head>
<title>Login</title>
</head>

<body>
    <div align="center">
        <h1>Login</h1>
        

<%--        <c:forEach var="actParam" items="${param}"> --%>
<%--            ${actParam.key} --> ${actParam.value}<br> --%>
<%--         </c:forEach> --%>

        <c:if test="${not empty param.login_error}">
            <font color="red"> Login fehlerhaft. Retry.<br /> <br /> Fehler: <c:out
                    value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />. </font>
        </c:if>
        
        <c:if test="${not empty param.session_timeout}">
            <font color="red">Die Session ist abgelaufen. Bitte neu anmelden.<br /> <br /> Fehler: <c:out
                    value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />. </font>
        </c:if>

        <form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
         
            <table>
                <tr>
                    <td>Benutzername:</td>
                    <td><input type='text' name='j_username'
                        value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' />
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='j_password'></td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="_spring_security_remember_me"></td>
                    <td>Zwei Wochen nicht mehr nach meinem Passwort fragen.</td>
                </tr>

                <tr>
                    <td colspan='2'><input name="Anmelden" type="submit"></td>
                </tr>
                <tr>
                    <td colspan='2'><input name="Zurücksetzen" type="reset"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>

