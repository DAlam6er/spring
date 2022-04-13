<%@ page language="java"
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
%>
<%-- Библиотека тегов помогает выводить список циклом for --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Для использования в аутентификации/авторизации --%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="header_courses" /></title>
    </head>
    <body>
        <%--<h1>Курсы</h1>--%>
        <h1><spring:message code="header_courses" /></h1>

        <%-- Пример локализации картинок--%>
        <%--<img src= <spring:message code="image_path"/>>--%>

        <%--
        Фрагмент будет показана только для аутентифицированных пользователей!
        --%>
        <s:authorize access="isAuthenticated()">
            <div id="userinfo">
                <%--
                Обращение к объекту (principal), содержащему информацию
                об аутентифицированном пользователе
                --%>
                Привет, <s:authentication property="principal.username" />!
                <br/>
                <%-- /logout - генерирует Spring Security--%>
                <%-- logout через кнопку--%>

                <form id="logout"
                      name="logoutForm"
                      action="${pageContext.request.contextPath}/logout"
                      method="POST">
                    <input type="submit" name="submit" value="Выйти">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>

                <%-- logout по ссылке ??? нужно что-то делать с CSRF --%>
                <%-- <a href="<c:url value="/logout" />">Click here to logout</a>--%>
                <%--<a href="../logout">Выход</a>--%>
            </div>
        </s:authorize>

        <%--
        Форма будет показана только для анонимных пользователей!
        --%>
        <s:authorize access="isAnonymous()">
            <%--
            Методом POST отправляются 2 параметра: j_username, j_password
            j_spring_security_check — Стандартная ссылка Spring
            в ней компонента Spring будет пытаться с помощью
            сконфигурированного authentification-manager (security-context.xml)
            аутентифицировать пользователя, после чего произойдёт переход
            --%>
            <form id="login"
                  name="loginForm"
                  action="${pageContext.request.contextPath}/login"
                  method="POST">

                <label for="login-input">Логин: </label>&nbsp;
                <input id="login-input" type="text" name="username">
                <label for="password-input">Пароль: </label>&nbsp;
                <input id="password-input" type="password" name="password">
                <input type="submit" name="submit" value="Войти">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="submit" name="submit" value="Регистрация">
                <%-- <a href="../security/registration">Регистрация...</a>--%>
            </form>
        </s:authorize>

        <%-- Если атрибут courses не пустой--%>
        <c:if test="${not empty courses}">
            <table border="1">
                <tr>
                    <td>Название</td>
                    <td>Часы</td>
                    <td>Описание</td>
                    <%--<s:authorize access="hasRole('ROLE_USER')">--%>
                    <td></td>
                    <td></td>
                    <%--</s:authorize>--%>
                </tr>
                <%-- var - переменная цикла, courses - источник--%>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td>${course.title}</td>
                        <td>${course.length}</td>
                        <td>${course.description}</td>
                        <%--<s:authorize access="hasRole('ROLE_USER')">--%>
                        <td><a href="update/${course.id}">Изменить</a></td>
                        <td><a href="delete/${course.id}">Удалить</a></td>
                        <%--</s:authorize>--%>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <%--<s:authorize access="hasRole('ROLE_USER')">--%>
        <a href="update/0">Добавить...</a>
        <%--</s:authorize>--%>

</body>
</html>